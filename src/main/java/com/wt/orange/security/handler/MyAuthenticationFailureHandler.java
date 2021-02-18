package com.wt.orange.security.handler;

import com.wt.orange.constant.Constant;
import com.wt.orange.vo.ResponseResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p> 登录认证失败处理器 </p>
 *
 * @author Wang Tao
 * @date 2021-02-02 03:16:48
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        Integer code = Constant.ResultEnum.ERROR.getCode();
        String msg = e.getLocalizedMessage();
        if (e.getCause() != null && !(e.getCause() instanceof AuthenticationException)) {
            code = Constant.ResultEnum.UNKNOWN_ERROR.getCode();
            msg = Constant.ResultEnum.UNKNOWN_ERROR.getValue();
        }

        response.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(ResponseResult.error(Constant.ResultEnum.ERROR).setCode(code).setMessage(msg).toString());
        writer.flush();
        writer.close();
    }
}
