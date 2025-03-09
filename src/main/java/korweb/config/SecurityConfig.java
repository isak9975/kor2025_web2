package korweb.config;

import korweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

        //[5] 시큐리티 로그인 : 시큐리티에 로그인[커스텀] 기능을 제공한다, JSON 형식이 아닌 form 형식으로 지원한다.

        http.formLogin( loginForm -> loginForm.loginPage("/member/login") // 로그인 할 view page url 정의
                .loginProcessingUrl( "/member/login.do")                //로그인을 처리 요청 URL 정의 //POST 방식
                .usernameParameter("mid")                      //로그인에 사용할 id 변수명
                .passwordParameter("mpwd")                      //로그인에 사용한 password 변수명
//                .defaultSuccessUrl("/")                      //만약에 로그인 성공시 이동할  page url 정의
//                .failureUrl("/member/login")        //만약에 로그인 실패시 이동할 page url 정의


                //--> Fetch/Axios(json) 에서는 아래와 같은 방법으로 사용한다
                    //json 사용하기에 주소값으로 반환하면 안됌. 그래서 url 대신 handler 사용
                        .successHandler((request,response,exception)->{
                            System.out.println("로그인 성공!!!");
                            response.setContentType("application/json");// 응답방식을 JSon으로 변경
                            response.getWriter().println("true"); //JSON 형식의 true 응답하기
                        })
                        .failureHandler((request, response, exception) -> {
                            System.out.println("로그인 실패!!!");
                            response.setContentType("application/json"); //응답방식을 JSON 변경
                            response.getWriter().println("false"); //JSON 형식의 false 응답하기.
                        })

        );

        //[6] 시큐리티 로그아웃, 시큐리티에서 로그아웃 기능[커스텀]을 제공한다
        http.logout(logout -> logout
                        .logoutUrl("/member/logout.do")   //로그아웃을 요청할 url  // GET방식 지원
                        .logoutSuccessUrl( "/")         // 만약에 로그아웃 성공시 이동할 page url 정의
                        .invalidateHttpSession(true)    //만약에 로그아웃 성공시 (로그인)세션 초기화
                );

        //[7] 로그인을 처리할 서비스 객체 정의
        http.userDetailsService(memberService);


        //[2] http 객체를 빌드/실행하여 보안 필터 체인을 생성
        return http.build();
    }//end method
    @Autowired private MemberService memberService;

    //[2] 암호와 : 시큐리티가 회원의 패스원드 검증[로그인] 할때 사용할 암호화 객체. Bcrypt
        //--> 개발자가 직접 암호화를 복호화 하지 않고, 시큐리티가 자동으로 암호화를 비교한다.(로그인처리 자동)
    @Bean   //스프링 컨테이너에 메소드 등록 // PasswordEncoder : 시큐리티가 로그인 할때 사용할 인코딩 객체
    public PasswordEncoder encoder(){return new BCryptPasswordEncoder();}



}//end class
