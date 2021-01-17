package com.wronggo.config;



import com.wronggo.service.impl.HrService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    HrService hrService;

    @Bean
    PasswordEncoder passwordEncoder() {
               return new BCryptPasswordEncoder();
         }
    @Override
     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
              auth.userDetailsService(hrService);
          }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //设置账号密码
//          auth.inMemoryAuthentication()
//                  .withUser("admin1").password("123").authorities("shoplogin");
//    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        //访问静态
        web.ignoring().antMatchers("/css/**","/fonts/**","/js/**","/img/**","/plugins/**", "/webjars/**", "**/favicon.ico");
    }
    @Override
    protected  void configure(HttpSecurity http) throws Exception{

        //允许嵌入页面
        http.headers().frameOptions().disable()
                .and()
                .authorizeRequests() // 设置哪些页面可以直接访问，哪些需要验证
                .antMatchers("/shoplogin.html","/register","/add").permitAll() // 放过
                .anyRequest().authenticated() // 剩下的所有的地址都是需要在认证状态下才可以访问
                .and()
                .formLogin()
                .loginPage("/shoplogin.html") // 指定指定要的登录页面
                .loginProcessingUrl("/shoplogin") // 处理认证路径的请求
                //认证成功后的跳转页面 默认是get方式提交 自定义成功页面post方式提交
                //在 controller中处理时要注意
                .defaultSuccessUrl("/index")
                .failureForwardUrl("/error")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/shoplogin")
                .and().csrf().disable()
                .sessionManagement()
                .maximumSessions(1);

   }




}