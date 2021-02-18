package com.wt.orange.security.configs;

import com.wt.orange.security.MyAuthenticationEntryPoint;
import com.wt.orange.security.filter.JwtAuthenticationFilter;
import com.wt.orange.security.filter.MyFilterSecurityInterceptor;
import com.wt.orange.security.handler.MyAccessDeniedHandler;
import com.wt.orange.security.handler.MyAuthenticationFailureHandler;
import com.wt.orange.security.handler.MyAuthenticationSuccessHandler;
import com.wt.orange.security.properties.IgnoreResourcesProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * <p> Security配置类 </p>
 *
 * @author Wang Tao
 * @date 2021-01-31 16:02:02
 */
@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 免登录资源配置
     */
    @Autowired
    private IgnoreResourcesProperties properties;

    /**
     * 自定义认证成功处理
     */
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    /**
     * 自定义认证失败处理
     */
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    /**
     * 认证用户访问处理
     */
    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    /**
     * 匿名用户访问结果处理
     */
    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    /**
     * jwt认证过滤器
     */
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * 决策管理
     */
    @Autowired
    private AccessDecisionManager myAccessDecisionManager;

    /**
     * 自定义元数据加载
     */
    @Autowired
    private FilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 关闭跨站请求保护
        http.csrf().disable()
                // 关闭session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 免登陆资源
        http.authorizeRequests().antMatchers(this.properties.getResources()).permitAll()
                // 其余所有请求认证之后可访问
                .anyRequest().authenticated();

        // 登录URL
        http.formLogin().loginProcessingUrl("/api/login")
                // 自定义认证成功处理
                .successHandler(myAuthenticationSuccessHandler)
                // 自定义认证失败处理
                .failureHandler(myAuthenticationFailureHandler)
                // 注销URL
                .and().logout().logoutUrl("/api/logout");

        http.exceptionHandling()
                // 认证用户访问处理
                .accessDeniedHandler(myAccessDeniedHandler)
                // 匿名用户访问结果处理
                .authenticationEntryPoint(myAuthenticationEntryPoint);

        // 自定义权限认证过滤器
        http.addFilterBefore(new MyFilterSecurityInterceptor(myFilterInvocationSecurityMetadataSource, myAccessDecisionManager), FilterSecurityInterceptor.class)
                // jwt认证过滤器
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }

    /**
     * 密码解析器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
