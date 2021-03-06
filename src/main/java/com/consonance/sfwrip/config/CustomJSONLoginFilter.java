package com.consonance.sfwrip.config;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.consonance.sfwrip.domain.User;
import com.consonance.sfwrip.service.UserService;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CustomJSONLoginFilter extends AbstractAuthenticationProcessingFilter {
    private final UserService userService;

    CustomJSONLoginFilter(String defaultFilterProcessesUrl, UserService userService) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl, HttpMethod.POST.name()));
        this.userService = userService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        JSONObject requestBody = getRequestBody(httpServletRequest);
        String username = requestBody.getString("userid");
        String password = requestBody.getString("password");
        validateUsernameAndPassword(username, password);
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();
        simpleGrantedAuthorityList.add(new SimpleGrantedAuthority("USER"));

        return new UsernamePasswordAuthenticationToken(username, password, simpleGrantedAuthorityList);
    }

    // 获取请求体
    private JSONObject getRequestBody(HttpServletRequest request) throws AuthenticationException {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            InputStream inputStream = request.getInputStream();
            byte[] bs = new byte[StreamUtils.BUFFER_SIZE];
            int len;
            while((len = inputStream.read(bs)) != -1) {
                stringBuilder.append(new String(bs, 0 , len));
            }
            return JSON.parseObject(stringBuilder.toString());
        } catch (IOException e) {
            logger.error("get request body error.");
        }
        throw new AuthenticationServiceException("invalid request body");
    }

    // 验证密码
    private void validateUsernameAndPassword(String userid, String password) throws AuthenticationException {
        User user = userService.findByUserId(userid);
        if(user == null){
            throw new UsernameNotFoundException("user not exist");
        }
        if(!user.getPassword().equals(password)) {
            throw new AuthenticationServiceException("error username or password");
        }
    }
}
