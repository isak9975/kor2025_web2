//[1] 수정 페이지 들어왓을때 수정 하기전 기존 정보(데이터) 보여주기
const getMyInfo=()=>{console.log('getMyInfo 실행')
    fetch('/member/myinfo.do')
    .then(r=>r.json())
    .then(d=>{
                document.querySelector('.midInput').value=d.mid;
                document.querySelector('.mnameInput').value=d.mname;
                document.querySelector('.memailInput').value=d.memail;

                })
    .catch(e=>console.log(e))
}
getMyInfo();


//[2] 수정 버튼 클릭했을때 수정처리
const onUpdate=()=>{
    //1. 입력받은 input value 값 가져오기
    let mname = document.querySelector('.mnameInput').value;
    let memail = document.querySelector('.memailInput').value;
    //2. 객체화
    let dataObj = {mname : mname, memail : memail}
    //3. fetch
    const option = {
        method : "PUT", headers : {'Content-Type':'application/json',
        body:JSON.stringify(dataObj)}
        }//end option
    fetch('/member/update.do',option)
    .then(response=>response.json())
    .then(date => {
        if(date){alert('수정 성공'); location.href='/';}
        else{alert('수정 실패');}
    })
    .catch(e=>{console.log(e);})
}//end f