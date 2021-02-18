package com.wt.orange.security.util;

import com.wt.orange.constant.Constant;
import com.wt.orange.exception.BusinessException;
import com.wt.orange.security.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> jwt工具类 </p>
 *
 * @author Wang Tao
 * @date 2021-02-07 10:41:49
 */
@Component
public class JwtUtil {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * <p>根据用户信息生成token</p>
     *
     * @param userDetails 认证用户
     * @return String 返回token
     * @author Wang Tao
     * @date 2021-02-07 13:44:23
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(Claims.SUBJECT, userDetails.getUsername());
        return generateToken(claims);
    }

    /**
     * <p>刷新token</p>
     *
     * @param token 待刷新token
     * @return String 返回新的token
     * @author Wang Tao
     * @date 2021-02-07 13:58:48
     */
    public String refreshToken(String token) {
        if (isTokenExpired(token)) {
            throw new BusinessException(Constant.ResultEnum.TOKEN_ERROR);
        }
        Claims claims = getClaimsFromToken(token);
        return generateToken(claims);
    }

    /**
     * <p>校验token合法性</p>
     *
     * @param token    待校验token
     * @param userDetails 认证用户信息
     * @return Boolean 返回校验结果
     * @author Wang Tao
     * @date 2021-02-07 14:00:08
     */
    public Boolean verifyToken(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        return StringUtils.hasText(username) && username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * <p>判断token是否过期</p>
     *
     * @param token 待校验token
     * @return Boolean 返回校验结果
     * @author Wang Tao
     * @date 2021-02-07 14:02:00
     */
    public Boolean isTokenExpired(String token) {
        Date expiration = getExpirationFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * <p>从token中获取用户名</p>
     *
     * @param token token
     * @return String 返回用户名
     * @author Wang Tao
     * @date 2021-02-07 14:02:41
     */
    public String getUserNameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * <p>获取token过期时间</p>
     *
     * @param token token
     * @return Date 返回过期时间
     * @author Wang Tao
     * @date 2021-02-07 14:03:39
     */
    private Date getExpirationFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * <p>从token中获取荷载内容</p>
     *
     * @param token token
     * @return Claims 返回荷载
     * @author Wang Tao
     * @date 2021-02-07 14:05:36
     */
    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * <p>根据荷载生成token</p>
     *
     * @param claims 荷载
     * @return String 返回token
     * @author Wang Tao
     * @date 2021-02-07 14:06:14
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration()))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }
}
