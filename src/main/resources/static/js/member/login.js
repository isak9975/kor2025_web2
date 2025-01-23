const onLogin=()=>{console.log("onLogin 실행");

//(1) Input Dom 가져온다
let midInput = document.querySelector('.midInput');
let mpwdInput = document.querySelector('.mpwdInput');

//(2) Dom의 value(입력받은값) 반환 받는다
let mid = document.querySelector('.midInput').value;
let mpwd = document.querySelector('.mpwdInput').value;

//(3)입력 받은 값들을 서버에게 보낼 객체 만들기
let dataObj = {mid:mid,mpwd:mpwd};

//(4)fetch옵션
const option = {method : 'POST', // Http 통신 요청 보낼때 사용할 메소드 선택
    headers : {'Content-Type' :'application/json'}, // HTTP 통신 요청 보낼때 header body(본문) 타입설정
        body : JSON.stringify(dataObj)}
        //JSON.stringify(객체) : 객첸타입 --> 문자열타입 변환 , HTTP 는 문자열타입만 전송이 가능하다

//(5) fetch 함수 실행
fetch("/member/login.do",option)
.then(r=>r.json())
.then(d=>{
        if(d==true){

            alert("로그인 성공");

            location.href="/";
        }else{alert("회원정보가 일치하지 않습니다.")}
    })
.catch(e=>{console.log("관리자에게 문의하세요");console.log(e);})
 
//(6) 결과에 따른 함수 제어



}