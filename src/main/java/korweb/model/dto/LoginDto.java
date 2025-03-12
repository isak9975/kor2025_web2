package korweb.model.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Setter@Getter@Builder
@AllArgsConstructor@NoArgsConstructor
//시큐리티의 일반회원과 oauth2 회원 정보를 통합하는 Dto
public class LoginDto implements UserDetails, OAuth2User {

    // + 필수
    private String mid; // 로그인한 회원의 아이디.
    private String mpwd; // 로그인"할" 회원의 비밀번호(oauth2 회원은 사용하지않는다.)
    private List<GrantedAuthority> mrolList; // 로그인한 회원의 권한/등급/레벨 목록

    //UserDetails : 시큐리티에서 일반회원들을 정보를 조작하는 인터페이스(미리 만들어짐-라이브러리)
    //OAuth2User : 시큐리티에서 oauth 회원들의 정보를 조작하는 인터페이스(미리 만들어짐-라이브러리)
        //-> 두 인터페이스를 LoginDto(내가만든클래스) 에서 구현(implements) 한다.
        //즉] LoginDto 에서 두 인터페이스를 모두 포함하므로 LoginDto 타입으로 두 타입들을 조작할 수 있다
    //오른쪽 클릭 -> Generate(생성) -> Implement Interfaces

    @Override //[1] 재정의 로그인한 회원의 아이디를 반환할 메소드
    public String getName() {
        return this.mid;
    }

//===================================================================================
    @Override//[4] 재정의 : 권한목록을 반환하는 메소드
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.mrolList;
    }

    @Override
    public String getPassword() {//[3] 재정의 : 일반 회원이 로그인 비밀번호를 반환하는 메소드
        return this.mpwd;
    }

    @Override//[2] 재정의 : 일반 회원이 로그인 아이디를 반환하는 메소드
    public String getUsername() { //Username <---> 로그인할때 사용하는 아이디
        return this.mid;
    }


                        @Override
                        public boolean isAccountNonExpired() {//계정의 만료 여부, 기본값 true (건들지않음)
                                                            //true = 만료되지 않았다.
                            return UserDetails.super.isAccountNonExpired();
                        }

                        @Override
                        public boolean isAccountNonLocked() {//계정 잠금 여부,기본값 true(건들지않음)
                                                            //true = 잠금이 아니다 false:잠금
                            return UserDetails.super.isAccountNonLocked();
                        }

                        @Override
                        public boolean isCredentialsNonExpired() {//비밀번호 만료 여부 확인, 기본값 true(건들지않음)
                            return UserDetails.super.isCredentialsNonExpired();
                        }

                        @Override
                        public boolean isEnabled() { //계정 활성화 여부, 기본값 : true, 활용 : 계정 잠금 기능(건들지 않음)
                            return UserDetails.super.isEnabled();
                        }

//================================================================================

                        @Override//oauth2 회원에서 사용하는 oauth2 속성 객체 반환할 메소드1(건들지않음)
                        public <A> A getAttribute(String name) {
                            return OAuth2User.super.getAttribute(name);
                        }

                        @Override// oauth2 회원에서 사용하는 oauth2 속성 객체 반환할 메소드2(건들지않음)
                        public Map<String, Object> getAttributes() {
                            return Map.of();
                        }
}
