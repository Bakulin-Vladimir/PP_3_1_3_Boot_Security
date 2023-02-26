package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.kata.spring.boot_security.demo.service.UserServiceDetail;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SuccessUserHandler successUserHandler;
    private UserServiceDetail userDetailsService;

    public WebSecurityConfig(SuccessUserHandler successUserHandler, UserServiceDetail userDetailsService) {
        this.successUserHandler = successUserHandler;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // делаем страницу регистрации недоступной для авторизированных пользователей
                .authorizeRequests()
                //страницы аутентификации доступна всем
                .antMatchers("/login").anonymous()
                // защищенные URL
                .antMatchers("/user/**").access("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')").anyRequest().authenticated();
        http.formLogin()
                // указываем страницу с формой логина
//                .loginPage("/login")
                //указываем логику обработки при логине
                .successHandler(successUserHandler)
                // указываем action с формы логина
//                .loginProcessingUrl("/login")
                // Указываем параметры логина и пароля с формы логина
//                .usernameParameter("username")
//                .passwordParameter("password")
                // даем доступ к форме логина всем
                .permitAll();
        http.logout()
                // разрешаем делать логаут всем
                .permitAll();
                // указываем URL логаута
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                // указываем URL при удачном логауте
//                .logoutSuccessUrl("/login?logout");

    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) {
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }
//    @Autowired
//    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    // аутентификация inMemory
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("user")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

}