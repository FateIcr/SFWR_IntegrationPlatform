package com.consonance.sfwrip.config;


import com.alibaba.fastjson.JSON;
import com.consonance.sfwrip.bean.WebResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

// 认证入口
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        WebResponse response = new WebResponse();
        response.setCode(String.valueOf(HttpServletResponse.SC_UNAUTHORIZED));
        response.setMessage("authenticate fail");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        httpServletResponse.getWriter().write(JSON.toJSONString(response));
    }
}
