///[1] 비동기 함수란 ??
const test1 = ( ) =>{
console.log('test1');   //(1) 첫번째 콘솔
const response = axios.get('/students') //(2) 두번째 콘솔
console.log(response.data) // (3) 세번째 콘솔
//실행순서 : 첫번째 -> 두번째(undefined)-> 세번째
//즉 } JS 흐름이 AXIOS 를 이용하여 요청을 보내고 응답을 기다리지 않고 두번째 콘솔을 출력햇으므로 undefined
console.log('test2')
}
test1()

//[2] 동기 함수란? 1. 함수내 매개변수() 앞에 'aync' 키워드 붙인다
//2. axios/fetch 앞에 'await' 키워드 붙인다
const test2 = async ( )=>{
    console.log('(2)test1'); //첫번째 콜솔
    const response = await axios.get('students')
    console.log(response.data) // 두번째 콘솔
    console.log('(2)test2') //세번째 콘솔
    //실행순선 : 첫번째 --> 두번째 --> 세번째
    //즉] JS 흐름이 AXIOS 를 이용하여 요청을 보내고 응답을 올때 까지 기다린다. 응답이 오면 다음코드를 실행한다.
}
test2()

//[4] 학생점수 전체 조회
const onFindAll = async ( ) => {
    //fetch 대신에 axios 사용예정
    //(1) AXIOS 이용한 SPRING controller 매핑
    const response = await axios.get('/students')
    //(2) 응답 본문 꺼내기
     const data = response.data;
     //(3)반복문 이용하여 HTML 출력한다
        //어디에
        const tbody = document.querySelector('tbody')
        //무엇을
        let html='';
        data.forEach(student) => {
            html += `
                    <tr>
                    <td>${student.sno}</td>
                    <td>${student.name}</td>
                    <td>${student.kor}</td>
                    <td>${student.math}</td>
            `
        }

        //츨력
        tbody.innerHTML = html;
}




//[3] 학생점수등록 버튼 클릭했을때.
const onSave = async ( ) => {
    const name = document.querySelector('.name').value;
    const kor = document.querySelector('.kor').value;
    const math = document.querySelector('.math').value;

    //2. 객체화
//    const obj = {name : name, kor : kor, math : math};
    //JS 에서는 객체 속성명과 대입변수명이 같다면 생략가능.
    const obj = {name : kor, math };

    //[선택1] fetch
//    const option = {
//    method : 'post',
//    headers : {'Content-Type':'application/json'},
//    body : JSON.stringify(obj)
//    }
//
//    fetch('/students',obj)
//        .then(response => response.json())
//        .then(data => {console.log(data)})
//        .catch(error => {console.log(error)})

    //[선택2] axios + 동기
    //'application/json'  언급하지 않아도 기본적으로 'application/json' 사용한다
    const response = await axios.post('/students',obj)
    console.log(response.data); //response 응답(정보) 객체, response.data 응답 본문 내용
    if(response.data==1){//글쓰기 성공
        alert('글쓰기 성공'); onFindAll();//새로고침
    }else{alert('글쓰기 실패')}

}