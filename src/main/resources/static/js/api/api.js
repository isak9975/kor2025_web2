console.log('api.js open');
//[4] char.js 샘플 코드
    //1. 차트가 표현될 DOM 객체
//[5] 공공데이터의 자료를 요청하여 chart.js API로 정보 시각화 하기
cosnt api4 = () => {
    //1. 부평구 인구현황 api rul
    const url = 'https://api.odcloud.kr/api/3044322/v1/uddi:466eee86-a8be-447b-9c8e-802bdbe897d7?page=1&perPage=22&serviceKey=nwPZ%2F9Z3sVtcxGNXxOZfOXwnivybRXYmyoIDyvU%2BVDssxywHNMU2tA55Xa8zvHWK0bninVkiuZAA4550BDqIbQ%3D%3D';
    //2.
    fetch(url)
        .then(r=>r.json())
        .then(rd=>{console.log(rd);
            //데이터 준비
            let 동별리스트 = []
            let 남자인구수 = []
            let 여자인구수 = []

            rd.data.forEach(obj => {
                동별리스트.push(obj.['동별'])  // 동별리스트에 모든 '동별'을 대입해준다
                남자인구수.push(obj.['인구수(남)']) // 남자인구수에 모든 '남자인구수'를 대입한다.
                여자인구수.push(obj.['인구수(여)']) // 여자인구수에 모든 '여자인구수'를 대입한다.
            })

            //char.js 활용
                //(1) 차트를 표현할 위치의 DOM 가져온다.
            const myChart2 = document.querySelector('#myChart2');
                //(2) 차트 객체를 생성한다.
            new Chart(myChart2,{
                type : 'bar',
                data:{
                    labels : 동별리스트, //동별
                    datasets : [//자료/값
                                { label : '남자인구수',data : 남자인구수 },
                                { label : '여자인구수',data : 여자인구수 }
                    ]
                },//data end
            })//char end
        })
        .catch(e => console.log(e))
}
api4();



          const ctx = document.getElementById('myChart');
//          const ctx = document.querySelector('#myChart')같은표현

          new Chart(ctx, { //차트 객체 생성, new Chart(ctx, {차트 옵션}
            type: 'line', // type : 차트모양 , bar(막대차트) , line(선차트) 등등 공식 홈페이지 찾아서 사용한다.
            //https://www.chartjs.org/docs/latest/
            data: { // data : 차트에 들어갈 자료들
              labels: ['1월', '2월', '3월', '4월', '5월', '6월'],
              datasets: [{ // [] 안에 {}1개당 항목1개 [{},{},{}]
                label: '사이다판매량', // 항목명
                data: [12, 19, 3, 5, 2, 3], // 항목의 (세로축) 값
                borderWidth: 1 // 선 굵기
              },{
              label: '콜라판매량', // 항목명
                  data: [12, 19, 3, 5, 2, 3], // 항목의 (세로축) 값
                  borderWidth: 1 // 선 굵기
              }]
            },
            options: {
              scales: {
                y: {
                  beginAtZero: true
                }
              }
            }
          });






//[3] 부평구 맛집 조회
const api3 = () => {
    //1. url 등록
    const url = "https://api.odcloud.kr/api/15103411/v1/uddi:0875260e-d807-49b7-85fe-adb00bfa76ce?page=1&perPage=23&serviceKey=";
    const serviceKey = "FSxW1Y51O6jBlQvmAH%2FRch6vIMJALqYJ4NiNmmNiE0Mt%2BK%2F02JIsU7STJOfFGun2tk3WcBvfIlJ%2Fd9ouqhXoVA%3D%3D";
    //2. fetch
    fetch(url + serviceKey)
        .then(r=>r.json())
        .then(rd=>{console.log(rd);
            const tbody = document.querySelector("#맛집출력");
            let html=``;

            rd.data.forEach((obj)=>{
            html+=`<tr>
                    <td>${obj['지정메뉴']} </td>
                    <td>${obj['업 소 명']} </td>
                    <td>${obj['업태']} </td>
                    <td>${obj['소재지']} </td>

            </tr>`;
            })
            tbody.innerHTML = html;

        })
        .catch(e=>console.log(e))
}


//[2] 사업자 상태 조회 요청 함수
const api2 = () => {
    //1. 입력받은 데이터(사업자번호) 가져오기
    const 사업자번호입력상자 = document.querySelector('#사업자번호입력상자')
    const 사업자번호 = 사업자번호입력상자.value;

    //2.요청 자료 만들기
    const data = { "b_no" : [사업자번호.replaceAll('-','')] } // 입력받은 사업자번호를 api 요청 형식에 맞게 구성

    //3. url
    const url = "https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=";
    //4. 서비스 키
    const serviceKey = "nwPZ%2F9Z3sVtcxGNXxOZfOXwnivybRXYmyoIDyvU%2BVDssxywHNMU2tA55Xa8zvHWK0bninVkiuZAA4550BDqIbQ%3D%3D";
    //5. fetch
    const option = {method:"POST",
                    headers : {'Content-Type':'application/json'},
                    body : JSON.stringify(data)
                    }
    fetch(url + serviceKey,option)
        .then(r=>r.json())
        .then(rd=>{console.log(rd);
            const 결과구역 = document.querySelector('#결과구역');
            let html = "입력한 데이터" + rd.data[0].b_no + "<br/>";
            html += rd.data[0].tax_type;
            결과구역.innerHTML = html;

        })
        .catch(e=>console.log(e))


}

//[1] 부평구 인구 현황 요청 함수
const api1 = () => {
    //1. 요청할 API URL
    const url = 'https://api.odcloud.kr/api/3044322/v1/uddi:466eee86-a8be-447b-9c8e-802bdbe897d7?page=1&perPage=23&serviceKey=';
    //2. 요청할 API 인증
    const serviceKey = 'FSxW1Y51O6jBlQvmAH%2FRch6vIMJALqYJ4NiNmmNiE0Mt%2BK%2F02JIsU7STJOfFGun2tk3WcBvfIlJ%2Fd9ouqhXoVA%3D%3D';
    //3.
    fetch(url + serviceKey) // url 과 serviceKey
        .then(r => r.json())
        .then(rd => {console.log(rd);
            //(1) 출력할 DOM (HTML) 객체 표현 가져온다
            const boardTable = document.querySelector('#boardTable')
            //(2) 출력할 내용을 저장할 변수 선언한다.
            let html = ``;
            //(3) 출력할 자료를 반복문 이용하여 여러개 자료를 html 문법 표현한다.
            rd.data.forEach((obj)=>{
                //객체변수명.속성명 vs 객체변수명['속성명']
                    //=> 속성명에 특수문자가 있으면  객체변수명.속성명 안먹힘
                    // 객체내 속성값 호출시 주의할점 : 속성명에 특수문자가 있을경우에는 ['속성명']
                html += `<tr>
                            <td> ${obj['동별']} </td>
                            <td> ${obj['세대수']} </td>
                            <td> ${obj['인구수(계)']} </td>
                            <td> ${obj['인구수(남)']} </td>
                            <td> ${obj['인구수(여)']} </td>
                </tr>`
                //.toLocaleString => 1000단위 쉼표

            })
            boardTable.innerHTML = html;
        })
        .catch(e=>console.log(e))

}
api1()