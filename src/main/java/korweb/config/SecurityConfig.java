package korweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration // 스프링 컨테이너의 빈 등록
public class SecurityConfig {

    //[1] 시큐리티의 필터 정의하기
    @Bean // 필드 또는 메소드에 빈 등록하는 방법
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{

        //[3] HTTP 요청에 따른 부여된 권한/상태 확인후 자원 허가 제한
        http.authorizeHttpRequests((httpReq)->{
                    //3-1 : 모든 HTTP 요청에 '/**'(모든 url 뜻).antMatcher('http 경로').permitAll() : 지정한 경로에는 누구나 접속 할 수 있다.
                    //3-2 : 글쓰기 페이지(board/write) 에는 로그인된 회원만 접근 할 수 있다.
                    //3-3 : 채팅페이지(chat)에는 로고인회원이면서 Role 이 USER  회원만 접글 할 수 있딷.
                    //3-4 : 관리자페이지(admin) 로그인회원 Role 이 admin 이거나 team1 회원만 접근 할수있다
                    httpReq.requestMatchers(AntPathRequestMatcher.antMatcher("/board/write")).authenticated()
                            .requestMatchers(AntPathRequestMatcher.antMatcher("/chat")).hasRole("USER")
                            .requestMatchers(AntPathRequestMatcher.antMatcher("/admin")).hasAnyRole("admin","team1")
                            .requestMatchers(AntPathRequestMatcher.antMatcher("/**")).permitAll();
        });
        //[4] CSRF : post/put(BODY) 요청을 금지, 특정한 URL 만 post/put 가능하도록 수동 허용
            //개발 : CSRF 사용안함, 개발 환경에서는 끄고 사용하는 경우가 많다.
            http.csrf(AbstractHttpConfigurer::disable); // csrf 끄기. --> post.put 사용할 수 있다.
            //배포/운영 : 특정한 URL 수동으로 허용 , 운영 환경에서는 안전하게 몇몇개의 REST 만 허용한다.
            //http.csrf(csrf->csrf.ignoringRequestMatchers(("csrf 예외할 URL")));
//            http.csrf(csrf->csrf.ignoringRequestMatchers(("/member/signup.do"))); // 회원가입 POST 예외

        //[2] http 객체를 빌드/실행하여 보안 필터 체인을 생성
        return http.build();
    }//end method
}//end class
