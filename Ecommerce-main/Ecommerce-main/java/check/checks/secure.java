package check.checks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;

@Configuration
public class secure {
    @Autowired
    jwtauth jwtauth;

    @Bean
    public SecurityFilterChain df(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable).
                authorizeHttpRequests(check -> check
                        //  .requestMatchers("/mehendi/signup","okay","/resetpassword").permitAll()
                        .requestMatchers("/admin/**").hasAuthority("admin")
                        .requestMatchers("/resetpassword", "/**", "/register", "/userregis").permitAll()
                )

                .addFilterBefore(jwtauth, UsernamePasswordAuthenticationFilter.class)

                //       .requestMatchers("/mehendi/admin/**").hasAuthority("admin")
                //             .requestMatchers("/mehendi/user/**").hasAuthority("user"))
                //  .requestMatchers("/oauth").authenticated())
                //  .oauth2Login(oauth-> oauth.loginPage("/signin"))
                // .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //   .addFilterBefore(jwtauth, UsernamePasswordAuthenticationFilter.class)
                .formLogin(form -> form.loginPage("/secured")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/verification").permitAll())
                .logout(log->log.logoutUrl("/logout").logoutSuccessUrl("/")
                ).oauth2Login(as->as.loginPage("/secured").loginPage("/login").defaultSuccessUrl("/"))


                //.formLogin(news->news.loginPage())
                //            .logout(logout-> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll())
                .build();
        //    .httpBasic(Customizer.withDefaults());
        //   .httpBasic(Customizer.withDefaults());
    }


    @Bean
    public PasswordEncoder okay() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }



        @Bean
        public SpringSecurityDialect springSecurityDialect(){
            return new SpringSecurityDialect();
        }


}
