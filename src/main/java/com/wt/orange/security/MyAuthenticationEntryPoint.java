package com.wt.orange.security;

import com.wt.orange.constant.Constant;
import com.wt.orange.vo.ResponseResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p> 匿名用户访问需要权限的资源异常处理 </p>
 *
 * @author Wang Tao
 * @date 2021-02-03 17:58:48
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(ResponseResult.error(Constant.ResultEnum.ACCESS_DENIED).toString());
        writer.flush();
        writer.close();
    }
}