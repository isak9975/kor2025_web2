//[1] 마이페이지 에서 (로그인된) 내정보 불러오기
const getMyInfo=()=>{

    // 1. fetch 이용한 내정보 요청과 응답받기
    fetch("/member/myinfo.do")
    .then(r=>r.json())
    .then(d=>{
                  if(d != ''){//응답 결과가 존재한다면.
                  // 응답 결과를 각 input value 에 각 정보들을 대입하기.(보여주기)
                    document.querySelector(".midInput").value = d.mid;
                    document.querySelector(".mnameInput").value = d.mname;
                    document.querySelector(".memailInput").value = d.memail;
                  }
                    })
    .catch(e=>{console.log(e);})
}// f end
getMyInfo(); //info.html 이 열릴때 내 정보 불러오기 함수 실행

//[2] 마이페이지에서 (로그인된) 회원 탈퇴 요청하기
const onDelete=()=>{
    // *예/아니오 형식으로 탈퇴 여부를 묻고 아니요 이면 탈퇴를 중지한다.
    let result = confirm("정말 탈퇴 하실건가요?");
    if(result == false){return ;}
    //1. fetch 이용한 회원 탈퇴 요청과 응답 받기
    fetch('/member/delete.do',{method:"DELETE"})
    .then(response=>response.json())
    .then(date=>{
                if(date==true){alert('탈퇴성공'); location.href='/';}
                else{alert('탈퇴실패');}
                })
    .catch(e=>{console.log(e);})


}