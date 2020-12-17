//package com.ecommerce.ggshop.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
////    @Override
////    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication()
////                .withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
////                .and()
////                .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
////                .and()
////                .withUser("admin").password(passwordEncoder().encode("adminPassword")).roles("ADMIN");
////    }
//
//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/dashboards/admin").hasRole("ADMIN")
//                //.antMatchers("/dashboards/admin").hasRole("RECEPTIONIST")
//                .antMatchers("/anonymous*").anonymous()
//                .antMatchers("/").permitAll()
////                .antMatchers("/customer/create").permitAll()
//                .antMatchers("/customer/register").permitAll()
//                .antMatchers("/customer/feedback").permitAll()
//
//                .antMatchers("/testCode").permitAll()
//                .antMatchers("/category/create").permitAll()
//                .antMatchers("/categories").permitAll()
//                .antMatchers("/**").permitAll()
//                .antMatchers("/products").permitAll()
//
//                .antMatchers("/layout").permitAll()
//                .antMatchers("/login*").permitAll()
//                .antMatchers("/static/*").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                //.loginProcessingUrl("/perform_login")
//                .defaultSuccessUrl("/", true)
//                //.failureUrl("/login.html?error=true")
//                //.failureHandler(authenticationFailureHandler())
//                .and()
//                .logout()
//                //.logoutUrl("/perform_logout")
//                .deleteCookies("JSESSIONID");
//        //.logoutSuccessHandler(logoutSuccessHandler());
//    }
//
////    @Override
////    public void configure(WebSecurity web) throws Exception {
////        web
////                .ignoring()
////                .antMatchers("/static/**", "/layout", "/fa/**", "/css/**", "/js/**", "/images/**", "/jquery/**");
////    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
