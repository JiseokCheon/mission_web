package cheon.mission.auth.config;

import cheon.mission.auth.service.CustomOAuth2UserService;
import cheon.mission.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @EnableWebSecurity : spring Security 설정 활성화
 * .csrf().disable() / .headers().frameOptions().disable() : h2 사용하기 위해 옵션들을 disable
 * authorizeRequests : url별 권한 관리 시작을 위해 (antMatchers 사용가능)
 * antMatchers : 권한 관리 대상 지정, (url, http 메소드 별로 관리 가능)
 * anyRequest : 설정된 값 이외의 나머지 url
 * authenticated : 인증된 사용자들만 허용
 * logout().logoutSuccessUrl("/") : 로그아웃 시 " / " 주소로 이동
 * oauth2Login() : OAuth2 로그인 기능
 * userInfoEndpoint : OAuth2 로그인 이후 사용자 정보들 가져오는 설정 담당
 * userService : 로그인 성공 후 진행할 인터페이스 구현체 등록
 */


@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/static/**", "/assets/**","/error/**", "/login_page/**", "/h2-console/**", "/static/js/**", "/charts").permitAll()
                .antMatchers("/api/v1/**", "/register/**").hasRole(Role.USER.name())
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);

    }
}
