package com.ups.UPSTrailerTracker.security

import com.ups.UPSTrailerTracker.user.User
import com.ups.UPSTrailerTracker.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {
    @Autowired
    lateinit var userService: UserService

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers("/css/**", "/images/**", "/js/**").permitAll()
                .antMatchers("/console/**").permitAll() // temporary for h2 db
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/trailers")
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout=true")
                .permitAll()
        http.csrf().disable() // temporary for h2 db
        http.headers().frameOptions().disable() // temporary for h2 db
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        userService.addUser(User("user", passwordEncoder().encode("password"), arrayListOf("USER")))
        userService.addUser(User("admin", passwordEncoder().encode("password"), arrayListOf("ADMIN")))
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder())
        /*
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("$2a$10\$kzQbYX8PyXvK1wL.g.7hlew4Xbbdy6lpJjy56fYObdq4IA5affmvS")
                .roles("USER")
        */
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}