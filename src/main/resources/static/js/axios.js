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