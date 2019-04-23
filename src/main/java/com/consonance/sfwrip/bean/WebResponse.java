package com.consonance.sfwrip.bean;


import com.consonance.sfwrip.constant.CommonResponse;
import com.consonance.sfwrip.constant.HttpResponseStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebResponse {
    private String code; //响应吗
    private String message; //提示信息
    private Object result; //结果

    public WebResponse(HttpResponseStatusEnum httpResponseStatusEnum) {
        this.code = httpResponseStatusEnum.getCode();
        this.message = httpResponseStatusEnum.getMessage();
    }

    public WebResponse(CommonResponse commonResponse){
        this.code = commonResponse.getCode();
        this.message = commonResponse.getMessage();
    }

    public static WebResponse success() {
        return new WebResponse(HttpResponseStatusEnum.SUCCESS.getCode(), HttpResponseStatusEnum.SUCCESS.getMessage(), null);
    }

    public static WebResponse success(Object result) {
        return new WebResponse(HttpResponseStatusEnum.SUCCESS.getCode(), HttpResponseStatusEnum.SUCCESS.getMessage(), result);
    }

    public static WebResponse forbidden() {
        return new WebResponse(HttpResponseStatusEnum.FORBIDDEN_OPERATION.getCode(), HttpResponseStatusEnum.FORBIDDEN_OPERATION.getMessage(), null);
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
