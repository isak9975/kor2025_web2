//[1] 로그인 정보 요청 함수 정의
const getLoginMid = () =>{
    //1. fetch 함수 활용하여 현재 로그인 상태 체크
        //1-2 fetch option
        const option = {method : "GET"}

    //2. fetch
    fetch("/member/login/id.do",option)
//    .then(r=>r.json()) SyntaxError: Failed to execute 'json' on 'Response': Unexpected end of JSON input
    .then(r=>r.text()) //String controller 에서 String 타입으로 반환할 경우에는 text() 함수로 변환해야한다.
    .then(d=>{

                //로그인 상태에 따라 버튼 활성화 여부 다르게 표현
                    //1. 출력할 위치 DOM 가져오기
                let memberBox = document.querySelector('.memberBox');
                    //2. html 준비
                let html ='';

               if(d==''){//응답 결과가 비어있으면
               console.log("비로그인상태")
               console.log(memberBox)
               
                    //3-1.회원가입 버튼, 로그인 버튼 활성화
                    html += `
                    <li class="nav-item"><a class="nav-link" href="/login">로그인</a></li>
                    <li class="nav-item"><a class="nav-link" href="/signup">회원가입</a></li> `;}

                    else{//응답 결과가 비어있지 않으면
                    //3-2. 로그아웃 버튼, 마이페이지 버튼, 로그인된 아이디 활성화
                        html += `
                    <li class="nav-item"><a class="nav-link" href="#">${d}님</a></li>
                    <li class="nav-item"><a class="nav-link" href="/myinfo">마이페이지</a></li>
                    <li class="nav-item"><a class="nav-link" href="/signup">내정보수정</a></li>
                    <li class="nav-item"><a class="nav-link" onclick="logOut()">로그아웃</a></li>
                    <li class="nav-item"><a class="nav-link" href="/signup">탈퇴하기</a></li>`;
                    
                    console.log("로그인 상태"); }

                            //4. 출력하기
                            memberBox.innerHTML=html;
                        } )
    .catch(e=>console.log(e))
}
getLoginMid(); //JS 실행될때. 로그인 정보 요청 함수 호출(바로)

//[2] 
const logOut=()=>{

    const option = {method : "GET"}

    fetch("/member/logout.do",option)
    .then(r=>r.json())
    .then(d=>{
            if(d==true){alert('로그아웃 했습니다'); location.href='/login';}
    })
    .catch(e=>console.log(e))


}

