package com.wt.orange.security.handler;

import com.wt.orange.constant.Constant;
import com.wt.orange.vo.ResponseResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p> 已认证用户访问需要权限的资源异常处理 </p>
 *
 * @author Wang Tao
 * @date 2021-02-03 17:46:01
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(ResponseResult.error(Constant.ResultEnum.ACCESS_DENIED).toString());
        writer.flush();
        writer.close();
    }
}
