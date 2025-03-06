package korweb.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import korweb.model.dto.MemberDto;
import korweb.model.dto.PointDto;
import korweb.model.entity.MemberEntity;
import korweb.model.entity.PointEntity;
import korweb.model.repository.MemberRepository;
import korweb.model.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService implements UserDetailsService {

    static boolean loginstatus = false;

    @Autowired private MemberRepository memberRepository;

    @Autowired private FileService fileService;

    //회원가입
    public boolean regist(MemberDto memberDto){
        // day70 : - 프로필사진 첨부파일 존재하면 업로드 진행
        // (1) 만약에 업로드 파일이 비어 있으면 'default.jpg' 임시용 프로필 사진 등록한다.
        if( memberDto.getUploadfile().isEmpty() ){
            memberDto.setMimg( "default.jpg");
        }
        else { // (2) 아니고 업로드 파일이 존재하면 , 파일 서비스 객체내 업로드 함수를 호출한다.
            String fileName = fileService.fileUpload( memberDto.getUploadfile() ); // 업로드 함수에 multipart 객체를 대입해준다.
            // (3) 만약에 업로드 후 반환된 값이 null 이면 업로드 실패 , null 아니면 업로드 성공
            if( fileName == null ){ return false; } // 업로드 실패 했으면 회원가입 실패
            else{
                memberDto.setMimg( fileName ); // 업로드 성공한 uuid+파일명 을 dto에 대입한다.
            }
        }

        // 1.  저장할 dto를 entity 로 변환한다.
        MemberEntity memberEntity = memberDto.toEntity();
        // 2. 변환된 entity를 save한다.
        // 3. save(영속성/연결된) 한 엔티티를 반환 받는다.
        MemberEntity saveEntity = memberRepository.save( memberEntity );
        // 4. 만약에 영속된 엔티티의 회원번호가 0보다 크면 회원가입 성공
        if( saveEntity.getMno() > 0 ){
            // day69실습 : 포인트 지급
            PointDto pointDto = PointDto.builder()
                    .pcontent("회원가입축하")
                    .pcount( 100 )
                    .build();
            pointPayment( pointDto , memberEntity );
            return true;
        }
        else{ return  false;}
    }

////    로그인 //시큐리티 이후에 사용하지 않는다
//    public boolean login(MemberDto memberDto){
////
////        //[ 방법1 ]
////        //(1) 모든 회원 엔티티를 조회한다.
////         List<MemberEntity> memberEntityAll = memberRepository.findAll();
////         //(2) 모든 회원 엔티티를 하나씩 조회한다.
////         for(int index=0;index >= memberEntityAll.size()-1;index++){
////             //==> forEach()문법 익히면 코드가 더욱 간단해 질듯
////             if(memberEntityAll.get(index).getMid().equals(memberDto.getMid())){ //아이디 판독
////                 if(memberEntityAll.get(index).getMpwd().equals(memberDto.getMpwd())){ //비밀번호 판도
////                     System.out.println("로그인 성공");
////                     return true;
////                 }
////             }
////         }
////        System.out.println("아이디 또는 비밀번호가 다릅니다");
////        return false;
//
//        //[ 방법 2]
//        boolean result = memberRepository.existsByMidAndMpwd(memberDto.getMid(),memberDto.getMpwd());
//
//        if(result == true){
//            System.out.println("로그인성공");
//            setSession(memberDto.getMid()); // 로그인 성공시 세션에 아이디 저장
//            return true;
//        }
//        else{
//            System.out.println("로그인실패");
//            return false;
//        }
//
//
//
//    }

    //[2] : 시큐리티 에서의 로그인 함수
        //(1) 해당 서비스 클래스명 뒤에 implements UserDetailsService
        //(2) loadUserByUsername 메소드를 오버라이딩 한다


    @Override
    public UserDetails loadUserByUsername(String mid) throws UsernameNotFoundException {
        System.out.println("mid  = " + mid); // 로그인이 입력받은 mid
        System.out.println("MemberService.loadUserByUsername");

        //(3) mid 이용하여 데이터베이스의 저장된 암호화패스워드를 가져오기
            //-입력받은 아이디로 회원 엔티티 찾기
        MemberEntity memberEntity = memberRepository.findByMid(mid);
            //- 입력받은 아이디의 엔티티가 없으면
        if(memberEntity == null){
            throw new UsernameNotFoundException("없는 아이디 입니다.");
            //throw : 던지다 뜻 // new UsernameNotFoundException : 예외 클래스 강제 실행
                //리턴과 비슷 개념이지만 throw는 함수를 비정상적으로 끝내는것이다
        }
        // - 입력받은 아이디의 엔티티가 존재하면 암호화된 패스워드 확인
        String password = memberEntity.getMpwd();
        System.out.println("password = " + password);

        //(4) 입력받은 mid 와 입력받은 mid의 암호화된 패스워드를 리턴. <UserDetails>
            //UserDetails : 인터페이스명, 시큐리티에서 사용하는 유저 정보를 조작하는 인터페이스
            //User : 클래스 , UserDetails 를 구현하는 구현(객)체
                //--> 시큐리티는  UserDetails 반환하면 자동으로 로그인 처리를 해준다.
                // 단]입력 받은 id와 입력받은 id의 암호화된 password 를 넣어줘야한다
        UserDetails user = User.builder().username(mid).password(password).build();

        //(5) UserDetails 반환.
        return user;
    }

    public boolean login(MemberDto memberDto){

    return false;
    }

    //===================================세션 관련 함수==================================================//


    @Autowired private HttpServletRequest request;

//    //[3] 세션객체내 정보 추가 :  세선객체에 로그인된 회원아이디를 추가하는 함수 ( 로그인)
    //시큐리티 이후에는 사용하지 않는다. // 시큐리티가 자동으로 세션 추가해준다
//    public boolean setSession(String mid){
//        //(2) 요청 객체를 이용한 톰캣내 세션 객체를 반환한다.
//        HttpSession httpSession = request.getSession();
//        //(3) 세션 객체에 속성(새로운 값) 추가한다.
//        httpSession.setAttribute("loginId",mid);
//        return true;
//    }


    //[4] 세션객체내 정보 반환 : 세선에 로그인된 회원아이디를 조회
    //시큐리티 이후에 커스텀 // 코드 수정
    public String getSession(){
        //(1) 시큐리티에서 자동으로 생선한 세션 껒내기
            //SecurityContextHolder : 시큐리티에 관련된 정보 저장소
                //.getContext() : 저장소 반환
                    //.getAuthentication() : 저장소에서 인증서 반환
                        //.getPrincipal(); : 중요한 인증 정보
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //(2)
        if(object.equals("anonymousUser")){
            return null; // 비로그인 상태이면 null 반환
        }
        //(3) 로그인 상태이면 로그인 구현할때 'loadUserByUserName' 메소드에서 반환한 UserDetails로 타입변환
        UserDetails userDetails =(UserDetails)object;
        //(4) 로그인된 정보에서 mid를 가져온다.
        String loginMid = userDetails.getUsername(); // Username == mid
        //(5) 로그인된 mid를 반환한다
        return loginMid;

    }
//    public String getSession(){
//        HttpSession httpSession = request.getSession();
//        //(4) 세션 객체에 속성명의 값 반환한다.
//        Object object = httpSession.getAttribute("loginId");
//
//                if(object != null){
//                    String mid = (String)object; //형변환
//                    return mid;
//                }
//
//                return null;
//    }

//    //[5] 세션객체내 정보 초기화 : 로그아웃
    //시큐리티 이후에 자동이므로 사용하지 않는다
    public boolean deleteSession(){

        HttpSession httpSession = request.getSession();

        httpSession.removeAttribute("loginId");

        return true;
    }

    //[6] 현재 로그인된 회원의 정보 조회
    public MemberDto getMyInfo(){
        System.out.println("getMyInfo실행");
        //1. 현재 세션에 저장된 회원 아이디 조회
        String mid = getSession(); // 현재 로그인된 아이디 조회
        //2. 만약에 로그인상태이면
        if(mid != null){
            //3. 회원아이디로 엔티티 조회 => 원래는 아이디로 조회 못하고 회원번호로만 조회가 가능했음(기본기능)
            MemberEntity memberEntity = memberRepository.findByMid(mid);
            //4. entity --> dto 변환
            MemberDto memberDto = memberEntity.toDto();
            //5. 반환
            return memberDto;
        }
        //비로그인 상태이면
        return null;
    }

    //[7] 현재 로그인된 회원 탈퇴
    public boolean myDelete(){
        System.out.println("myDelete실행");
        String mid = getSession(); // 1. 현재 세션에 저장된 회원 아이디 조회

        if(mid != null){//2. 만약에 로그인 상태이면
            //3. 현재 로그인된 아이디로 엔티티 조회
            MemberEntity memberEntity = memberRepository.findByMid(mid);
            //4. 탈퇴/삭제 실행
            memberRepository.delete(memberEntity);
            //5. 반환
            deleteSession(); //**로그인 정보 지우기 : 로그아웃
            return true;
        }
        //비로그인 상태이면
        return false;

    }

    //[8] 현재 로그인된 회원 정보 수정 , mname 닉네임, memail 이메일
    @Transactional //회원정보 수정일경우 꼭 써야함.
    public boolean myUpdate(MemberDto memberDto){
        System.out.println("update실행");
        String mid = getSession();

        if(mid != null){//로그인 상태이면
            MemberEntity memberEntity = memberRepository.findByMid(mid);

           memberEntity.setMname(memberDto.getMname());
           memberEntity.setMemail(memberDto.getMemail());
           return true;
        }
        //비로그인 상태이면
        return false;
    }


    @Autowired private PointRepository pointRepository;

    // [9] (부가서비스) 포인트 지급 함수 . 지급내용 pcontent / 지급수량 pcount , 지급받는회원엔티티
    @Transactional
    public boolean pointPayment( PointDto pointDto , MemberEntity memberEntity ){

        PointEntity pointEntity = pointDto.toEntity();
        pointEntity.setMemberEntity( memberEntity ); // 지급받는 회원엔티티 대입

        PointEntity saveEntity = pointRepository.save( pointEntity  );
        if( saveEntity.getPno() > 0 ){return true; }
        else{ return false; }
    } // f end

    // [10] 내 포인트 지급 전제 내역 조회
    public List<PointDto> pointList(){
        // 1. (로그인된) 내정보 가져오기
        String mid = getSession();
        MemberEntity memberEntity = memberRepository.findByMid( mid );
        // 2. 내 포인트 조회하기
        List<PointEntity> pointEntityList = pointRepository.findByMemberEntity( memberEntity );
        // 3. 조회된 포인트 엔티티를 dto로 변환
        List<PointDto> pointDtoList = new ArrayList<>();
        pointEntityList.forEach( (entity) ->{
            pointDtoList.add( entity.toDto() );
        });
        return  pointDtoList;
    }

    // [11] 현재 내 포인트 조회
    public int pointInfo(){
        // 1. (로그인된) 내정보 가져오기
        String mid = getSession();
        MemberEntity memberEntity = memberRepository.findByMid( mid );
        // 2. 내 포인트 조회하기
        List<PointEntity> pointEntityList = pointRepository.findByMemberEntity( memberEntity );
        // 3. 조회된 포인트 엔티티의 합계 구하기.
        int myPoint = 0;
        for( int index = 0 ; index<=pointEntityList.size()-1 ; index++ ){
            myPoint += pointEntityList.get(index).getPcount();
        }
        return  myPoint;
    }





}
