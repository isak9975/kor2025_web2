console.log("api2.js open")
//Geolocation API : 접속한 클라이언트의 IP로 접속 위치 확인
//navigator.geolocation.getCurrentPosition((position) => {
//  doSomething(position.coords.latitude, position.coords.longitude);
//});

//[5] 마커 클러스터 사용하기 + 공공데이터 API + 부트스트랩
// + 클러스터 사용시 html js 앱키 뒤에 &&libraries=clusterer 필요

//(1) 지도의 중심좌표 와 지도확대 레벨 초기 설정하고 지도를 표시함 div(dom)
 var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
        center : new kakao.maps.LatLng(36.2683, 127.6358), // 지도의 중심좌표
        level : 14 // 지도의 확대 레벨
    });

//(2 마커 클러스터 생성 : 클러스터란? 마커 주변에 근처에 마커들이 있을때 집합 아이콘
    // 마커 클러스터러를 생성합니다
    var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 10 // 클러스터 할 최소 지도 레벨
    });

//(3) 공식 문서에는 JQery의 Ajex를 사용하지만, 강의에서는 fetch를 이용한 자료 가오기
    // 데이터를 가져오기 위해 jQuery를 사용합니다
    const url = 'https://api.odcloud.kr/api/15051492/v1/uddi:852bbc11-63ed-493e-ab09-caaaf54fd144?page=1&perPage=35&serviceKey=FSxW1Y51O6jBlQvmAH%2FRch6vIMJALqYJ4NiNmmNiE0Mt%2BK%2F02JIsU7STJOfFGun2tk3WcBvfIlJ%2Fd9ouqhXoVA%3D%3D'


    fetch( url ).then( r => r.json() ).then( responseData => { console.log( responseData )
            // responseData = { data : [ {약국정보},{약국정보},{약국정보},{약국정보} ]  }
            // (4) 응답 받은 자료로 마커 만들기
            // 반복문 : 1.for문 2.forEach 3. map
            // 3. map
            let markers = responseData.data.map( data => {
                // 1. 마커 생성한다.
                let marker = new kakao.maps.Marker({position : new kakao.maps.LatLng( data.위도, data.경도 )}); // 마커 생성
                // * 각 마커에 클릭 이벤트 등록한다.
                // kakao.maps.event.addListener( marker , 'click' , function(){ })
                kakao.maps.event.addListener( marker , 'click' , () => {
//                    alert( `${ data.약국명 } 클릭 했군. `);
                    // + 부트스트랩의 '오프캔버스' : https://getbootstrap.kr/docs/5.3/components/offcanvas/

                    //클릭한 마커의 약국 정보를 사이드바(div) html에 값 대입하기
                    document.querySelector('.약국명').innerHTML = data.약국명;
                    document.querySelector('.전화번호').innerHTML = data.전화번호;
                    document.querySelector('.주소').innerHTML = data.소재지도로명주소;
                    //+ 부트스트랩의 '오프캔버스' 실행 버튼 클릭 이벤트
                    document.querySelector('.사이드바버튼').click();// js에서 특정한 버튼 강제로 클릭하기



                })
                // 2. forEach 와 다르게 map은 return를 사용 할 수 있다. return 값은 새로운배열에 대입된다.
                // 반복문에서 return된 marker 는 markers 배열에 대입된다. 즉 push 생략
                return marker;
            })
            // 3. markers 를 클러스터에 대입한다.
            clusterer.addMarkers( markers );

        })
        .catch( e => { console.log(e); })



//            let markers = [];
//            rd.data.forEach(data=>{
//                //마커 생성
//                let marker = new kakao.maps.Marker({position : new kakao.maps.LatLng( data.위도, data.경도 )}); // 마커 생성
//                //생성한 마커를 마커목록에 넣어준다
//                markers.push(marker);
//            })
//            //5. 마커목록/리스트 를 클러스터에 추가한다.
//            clusterer.addMarkers(markers);







////[1] 간단한 지도 띄우기
//var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
//var options = { //지도를 생성할 때 필요한 기본 옵션
//	center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
//	level: 3 //지도의 레벨(확대, 축소 정도)
//};
//
//var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴



//[2] 클릭한 위치에 마커 표시하기
    //추후에 사용자가 클릭한 위치의 취도와 경도를 DB에 저장해서 활용가능.
//var mapContainer = document.getElementById('map'), // 지도를 표시할 div
//    mapOption = {
//        center: new kakao.maps.LatLng( 37.489572382, 126.723325411), // 지도의 중심좌표
//        level: 3 // 지도의 확대 레벨
//    };
//
//var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
//
//// 지도를 클릭한 위치에 표출할 마커입니다
//var marker = new kakao.maps.Marker({
//    // 지도 중심좌표에 마커를 생성합니다
//    position: map.getCenter()
//});
//// 지도에 마커를 표시합니다
//marker.setMap(map);
//
//// 지도에 클릭 이벤트를 등록합니다
//// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
//kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
//
//    // 클릭한 위도, 경도 정보를 가져옵니다
//    var latlng = mouseEvent.latLng;
//
//    // 마커 위치를 클릭한 위치로 옮깁니다
//    marker.setPosition(latlng);
//
//    var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
//    message += '경도는 ' + latlng.getLng() + ' 입니다';
//
//// innetHTML이 없어서 여러번 클릭 안됨
////    var resultDiv = document.getElementById('clickLatlng');
////    resultDiv.innerHTML = message;
//
//});

//[3] 마커에 이벤트 생성하기
//var mapContainer = document.getElementById('map'), // 지도를 표시할 div
//    mapOption = {
//        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
//        level: 3 // 지도의 확대 레벨
//    };
//
//var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
//
//// 마커를 표시할 위치입니다
//var position =  new kakao.maps.LatLng(33.450701, 126.570667);
//
//// 마커를 생성합니다
//var marker = new kakao.maps.Marker({
//  position: position,
//  clickable: true // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
//});
//
//// 아래 코드는 위의 마커를 생성하는 코드에서 clickable: true 와 같이
//// 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
//// marker.setClickable(true);
//
//// 마커를 지도에 표시합니다.
//marker.setMap(map);
//
//// 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성합니다
//var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
//    iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다
//
//// 인포윈도우를 생성합니다
//var infowindow = new kakao.maps.InfoWindow({
//    content : iwContent,
//    removable : iwRemoveable
//});
//
////*********** 마커에 클릭이벤트를 등록합니다
//kakao.maps.event.addListener(marker, 'click', function() {
//alert("마커 클릭 하셨군요?")
//      // 마커 위에 인포윈도우를 표시합니다
////      infowindow.open(map, marker);

//});


////[4] 여러개 마커 표시하기
//    //(1) 지도를 출력할 DOM 객체 가져온다
//var mapContainer = document.getElementById('map'), // 지도를 표시할 div
//    //(2) 지도를 출력하기 전에 옵션[ 지도의 중심좌표 - 위도/경도] 정보 설정
//    mapOption = {
//        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
//        level: 3 // 지도의 확대 레벨 0~14
//    };
//    //(3) 지도 객체를 생성합니다
//var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
//
//// 마커를 표시할 위치와 title 객체 배열입니다
//    //(4) 지도객체에 출력할 마크업들의 정보(마크업 제목, 마크업 위치 : latlang( 위도, 경도)
//    // + 데이터베이스에 저장된 위도/경도 여러개를 여러마커로 표시할 수 있다.
//var positions = [
//    {
//        title: '카카오',
//        latlng: new kakao.maps.LatLng(33.450705, 126.570677)
//    },
//    {
//        title: '생태연못',
//        latlng: new kakao.maps.LatLng(33.450936, 126.569477)
//    },
//    {
//        title: '텃밭',
//        latlng: new kakao.maps.LatLng(33.450879, 126.569940)
//    },
//    {
//        title: '근린공원',
//        latlng: new kakao.maps.LatLng(33.451393, 126.570738)
//    }
//];
//
//// 마커 이미지의 이미지 주소입니다
//    //(5) 마커의 이미지는 베포된 HTTP 경로 이미지
//var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
//    //http:///localhost:8080/img/'이미지이름'.jpg
//
//for (var i = 0; i < positions.length; i ++) {
//
//    // 마커 이미지의 이미지 크기 입니다
//    var imageSize = new kakao.maps.Size(24, 35);
//
//    // 마커 이미지를 생성합니다
//    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
//
//    // 마커를 생성합니다
//    var marker = new kakao.maps.Marker({
//        map: map, // 마커를 표시할 지도
//        position: positions[i].latlng, // 마커를 표시할 위치
//        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
//        image : markerImage // 마커 이미지
//    });
//}