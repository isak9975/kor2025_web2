const onSignUp=()=>{

console.log("onSignUp 실행");

//(1) INPUT dOM 가져온다
let midInput = document.querySelector('.midInput');
let mpwdInput = document.querySelector('.mpwdInput');
let mnameInput = document.querySelector('.mnameInput');
let memailnput = document.querySelector('.memailInput');
//(2) Dom 의 value(입력받은값) 반환 받는다.
let mid = document.querySelector('.midInput').value; console.log(mid);
let mpwd = document.querySelector('.mpwdInput').value; console.log(mpwd);
let mname = document.querySelector('.mnameInput').value; console.log(mname);
let memail = document.querySelector('.memailInput').value; console.log(memail);

//(생략)다양한 유효성 검사 코드 생략

//(3)입력 받은 값들을 서버에게 보낼 객체 만들기
let dataObj = {mid:mid,mpwd:mpwd,mname:mname,memail:memail};

//(4) fetch 옵션
const option = {method : 'POST', // Http 통신 요청 보낼때 사용할 메소드 선택
                headers : {'Content-Type' :'application/json'}, // HTTP 통신 요청 보낼때 header body(본문) 타입설정
                    body : JSON.stringify(dataObj)}
                    //JSON.stringify(객체) : 객첸타입 --> 문자열타입 변환 , HTTP 는 문자열타입만 전송이 가능하다

//(5) fetch 통신
fetch('/member/signup.do',option) // fetch('통신할url',옵션)
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

//(6) fetch 응답에 따른 화면 구성

//(7)


}
