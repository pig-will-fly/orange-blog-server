package com.wt.orange.security.handler;

import com.wt.orange.constant.Constant;
import com.wt.orange.entity.AuthUser;
import com.wt.orange.security.properties.JwtProperties;
import com.wt.orange.security.util.JwtUtil;
import com.wt.orange.vo.ResponseResult;
import com.wt.orange.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> 登录成功处理器 </p>
 *
 * @author Wang Tao
 * @date 2021-02-02 01:46:04
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        response.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = response.getWriter();

        // 返回凭证信息
        Map<String, Object> data = new HashMap<>();

        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        data.put("user", new UserVo(authUser.getNickname(), authUser.getAvatar()));

        try {
            data.put("token", createToken(authUser));
            writer.write(ResponseResult.success(data).toString());
        } catch (Exception e) {
            writer.write(ResponseResult.error(Constant.ResultEnum.UNKNOWN_ERROR).toString());
        } finally {
            writer.flush();
            writer.close();
        }

    }

    /**
     * <p>创建token返回结果</p>
     *
     * @param authUser 认证用户信息
     * @return Map
     * @author Wang Tao
     * @date 2021-02-07 19:07:14
     */
    private Map<String, Object> createToken(AuthUser authUser) {
        Map<String, Object> data = new HashMap<>();
        data.put("header", jwtProperties.getTokenHeader());
        data.put("value", jwtProperties.getTokenPrefix() + jwtUtil.generateToken(authUser));
        return data;
    }
}
