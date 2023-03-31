package springboot.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //http security
        http.authorizeHttpRequests()
                .antMatchers("/", "/add", "/login").permitAll()
                .anyRequest().authenticated();
        http.formLogin()
                .loginPage("/login") //사용자 정의 로그인 페이지
                .defaultSuccessUrl("/") //로그인 성공 후 이동 페이지
                .failureUrl("/login") //실패후 이동 페이지
                .usernameParameter("id") //아이디 파라미터명 설정
                .passwordParameter("password") //비밀번호 파라미터명 설정
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/");
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        //web security
        //우선 순위가 높음
        return (web) -> web.ignoring()
                .antMatchers("/css/**", "/*.ico", "/error");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
