// * js가 열렸는지 확인
console.log('board.js open')

// * 현재 URL 의 쿼리스트링 매개변수 가져오기
console.log(new URL(location.href)) // 현제 페이지의 URL 정보가 담긴 객체 생성
console.log(new URL(location.href).searchParams) // 현재 페이지의 URL 쿼리스트링 정보 속성 반환
console.log(new URL(location.href).searchParams.get('cno')) // 주소창의 cno를 가져온다 - 딱번호만

//[1] 게시물 전체 조회 요청 함수
const findAll = () =>{ console.log('findAll 실행')
    //1. 현재 페이지 URL에서 매개변수 cno 값 구하기
    const cno = new URL(location.href).searchParams.get('cno');

    //1-1. 현재 페이지 URL 에서 매개변수 page(페이지번호) 값 구하기
    let page = new URL(location.href).searchParams.get('page');
    if(page==null)page=1;//만약에 page 가 없으면 1 페이지로 설정

    //2. fetch option
    const option = {method:"GET"}
    //3. fetch + 페이징 번호
    fetch(`/board/findall.do?cno=${cno}&page=${page}`,option) //cno와 page를 @RequestParam으로 매개변수 전달
        .then(response => response.json())
        .then(data => {
            //4. 요청 결과 응답 자료 확인
            console.log(data);
            //5. html 을 출력할 구역 dom 가져오기
            const tbody = document.querySelector('tbody'); //tbody 전부를 가져옴 (not class)
            //6. 출력할 html 을 저장하는 변수 선언
            let html = ``
            //7. 응답 자료를 반복문 이용하여 하나씩 순회해서 html 누적으로 더해주기
            data.forEach(board=>{

                if(board.cno == cno){console.log('가능')
                    console.log(board.cno);
                    console.log(cno);

                html += `<tr>
                            <td>${board.bno}</td>
                            <td><a href='/board/view?bno=${board.bno}'>${board.btitle}</a></td>
                            <td>${board.mid}</td>
                            <td>${board.bview}</td>
                            <td>${board.cdate}</td>
                        <tr>`
                        }
            })
            //8. 반복문 종료후 html 변수에 누적된 <tr> 출력하기
            tbody.innerHTML = html;
        })
        .catch(e=>{console.log(e);})



}//f end
findAll(); // JS가 실행 될때 함수 실행