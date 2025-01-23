//[2] 업로드 회원가입 함수
const onSignUp=()=>{
    //1. document.quertSelect 로 하나씩 가져오는 방식이 아님
    // - 입력된 값을 하나씩 가져오는 방식이 아닌 from 전체를 한번에 가져오기(multipart/from-data
    //[1] 전송할 from dom 객체를 가져온다.
    const signupForm = document.quertSelector('#signupForm');
        console.log(signupForm);

        // ** 폼 전체를 전송할때는 controller 에 dto 멤버변수와 form 안에 있는inputdml ㅜ믇 thrtjdauddl ehddlfgodigksek.
        //<input name="mid">   MemberDto(private String mid;)


    //[2] form dom 객체를 바이트로 받환한다. new FormData(dom객체) : 지정한 dom 객체를 바이트로 반환
        //HTTP 대용량 자료들은 바이너리(바이트) 단위로 전송하므로 일반 JSON 으로 전송이 불가능.
    const signupFormData = new FormData(signupForm);
        console.log(signupFormData); //'application/json' 형식이 아닌 'multipart.from-data' 형식으로

    //[3] application/json 이 아닌 mulipart/form-data 형식의 fetch 설정하는 방법
    const option = {
    method = "Post",
    //constant-type 생략하면 자동으로 multipart/from-data 설정된다.
    body:signupFormData
    //JSON.stringfiy() 안하는 이유 : 폼 전송 하므로 생략한다.
    }
    //[4] fetch 사용한다
    fetch("/member/signup.do".option)
    .then(r => r.json()) //.then() 통신 요청 보내고 응답 객체를 반환 받고 .json()를 이요한 응답객체를 json타입으로 변환
        .then(d=>{  //.then() json 으로 변환된 자료를 실행문 처리
                if(d==true){
                    alert('가입등록완료'); location.href='/login'; // 만일 응답 자료가 true이면 성공, 로그인페이지로 이동
                }
                else{
                    alert('가입실패') // 만일 응답 자료가 false 이면 실패 안내
                }
            })
            .catch(e=>{alert('가입오류 : 관리자에게 문의')}) //통신 오류가 발생하며 오류 메세지 안내
    //





}


//[1] 업로드 전 회원가입 코드
//const onSignUp=()=>{
//
//console.log("onSignUp 실행");
//
////(1) INPUT dOM 가져온다
//let midInput = document.querySelector('.midInput');
//let mpwdInput = document.querySelector('.mpwdInput');
//let mnameInput = document.querySelector('.mnameInput');
//let memailnput = document.querySelector('.memailInput');
////(2) Dom 의 value(입력받은값) 반환 받는다.
//let mid = document.querySelector('.midInput').value; console.log(mid);
//let mpwd = document.querySelector('.mpwdInput').value; console.log(mpwd);
//let mname = document.querySelector('.mnameInput').value; console.log(mname);
//let memail = document.querySelector('.memailInput').value; console.log(memail);
//
////(생략)다양한 유효성 검사 코드 생략
//
////(3)입력 받은 값들을 서버에게 보낼 객체 만들기
//let dataObj = {mid:mid,mpwd:mpwd,mname:mname,memail:memail};
//
////(4) fetch 옵션
//const option = {method : 'POST', // Http 통신 요청 보낼때 사용할 메소드 선택
//                headers : {'Content-Type' :'application/json'}, // HTTP 통신 요청 보낼때 header body(본문) 타입설정
//                    body : JSON.stringify(dataObj)}
//                    //JSON.stringify(객체) : 객첸타입 --> 문자열타입 변환 , HTTP 는 문자열타입만 전송이 가능하다
//
////(5) fetch 통신
//fetch('/member/signup.do',option) // fetch('통신할url',옵션)
//    .then(r => r.json()) //.then() 통신 요청 보내고 응답 객체를 반환 받고 .json()를 이요한 응답객체를 json타입으로 변환
//    .then(d=>{  //.then() json 으로 변환된 자료를 실행문 처리
//            if(d==true){
//                alert('가입등록완료'); location.href='/login'; // 만일 응답 자료가 true이면 성공, 로그인페이지로 이동
//            }
//            else{
//                alert('가입실패') // 만일 응답 자료가 false 이면 실패 안내
//            }
//        })
//        .catch(e=>{alert('가입오류 : 관리자에게 문의')}) //통신 오류가 발생하며 오류 메세지 안내
//
////(6) fetch 응답에 따른 화면 구성
//
////(7)
//
//
//}
