// * 썸모 노트 실행
$(document).ready(function() {
  $('#summernote').summernote();
    height : 500, // 썸모 노트 게시물 높이 수정
    lang : 'ko-KR', // 썸머노트 설명 한글화
    placeholder : '게시물 내용 입력해주세요' // 입력전 가이드라인들 정의

});

//[1] 게시물 등록 요청 함수
const onWrite=()=>{ console.log('onWrite() 실행')
    //[1] 현재 html 의 DOM 객체 가져오기
    const cno = document.querySelector('.cno').value;
    const btitle = document.querySelector('.btitle').value;
    const bcontent = document.querySelector('.bcontent').value;

    //[2] 입력받은 값들을 JSON 보내기 위해서 입력 받은 값으로 객체체 제작
    const obj = {cno:cno,btitle:btitle,bcontent:bcontent};
    //option
    const option = {method:"Post",
                    headers : {'Content-Type':'application/json'},
                    body : JSON.Stringify(obj)
                    };

    //fetch
    fetch("/board/write.do",option)
        .then(r=>r.json())
        .then(d=>{
            if(d==true){console.log(' onWrite() fetch 실행')
            alert('글쓰기 성공')
            location.href=`/board?cno${cno}`}
            else{
            alert('글쓰기 실패 : 로그인후 가능합니다')}
        })
        .catch(e=>{console.log(e);})



}


