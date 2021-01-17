package com.wronggo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //设置账号密码
          auth.inMemoryAuthentication()
                  .withUser("admin1").password("123").authorities("login");
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        //访问静态
        web.ignoring().antMatchers("/css/**","/fonts/**","/js/**","/img/**", "/webjars/**", "**/favicon.ico");
    }
    @Override
    protected  void configure(HttpSecurity http) throws Exception{

        //允许嵌入页面
        http.headers().frameOptions().disable()
                .and()
                .authorizeRequests() // 设置哪些页面可以直接访问，哪些需要验证
                .antMatchers("/login.html","/error").permitAll() // 放过
                .anyRequest().authenticated() // 剩下的所有的地址都是需要在认证状态下才可以访问
                .and()
                .formLogin()
                .loginPage("/login.html") // 指定指定要的登录页面
                .loginProcessingUrl("/login") // 处理认证路径的请求
                //认证成功后的跳转页面 默认是get方式提交 自定义成功页面post方式提交
                //在 controller中处理时要注意
                .defaultSuccessUrl("/index")
                .failureForwardUrl("/error")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and().csrf().disable()
                .sessionManagement()
                .maximumSessions(1);

   }




}