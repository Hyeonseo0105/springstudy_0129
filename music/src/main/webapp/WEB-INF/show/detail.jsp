<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="text/javascript">
$(function() {
	  $("#review").click(function(event) {
		    event.preventDefault();
		    $("#info.active").removeClass("active"); // 이전에 클릭한 링크에서 clicked 클래스 제거
		    $(this).addClass("active"); // 현재 클릭한 링크에 clicked 클래스 추가
		  });
});
</script>
<style type="text/css">
  #info,#review{
    cursor:pointer;
  	font-size: large;
  	margin-left: 0.5%;
  	font-weight: bold;
  }
  #info:hover,#review:hover{
  	color: #26c49f;
  }
</style>
</head>
<body>
<div style="height: 100px"></div>
<div class="container-xxl bg-white p-0" id="showDetailApp" style="padding: 4% !important">
  <div v-if="show_detail.scate===1" style="margin-left: 2%">콘서트</div>
  <div v-if="show_detail.scate===2" style="margin-left: 2%">뮤지컬</div>
  <div v-if="show_detail.scate===3" style="margin-left: 2%">클래식</div>
<table class="table">
 <tr>
  <td>
    <h1 id="title" style="margin-left: 1%">{{show_detail.stitle}}</h1>
    <span style="margin-left: 1.4%">{{show_detail.sdate}}&nbsp;&nbsp;|&nbsp;&nbsp;{{show_detail.sloc}}</span>
  </td>
</tr>
</table>
<table class="table">
  <tr>
    <td width="35%" align="center" rowspan="4">
      <img :src="show_detail.sposter">
    </td>
  </tr>
  <tr>
    <td width="60%" style="margin-left: 5%">
      <a style="color:black;font-weight: bold">등급</a>
      <span id="price2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{show_detail.sgrade}}</span>
<div style="height: 30px"></div>
      <a style="color:black;font-weight: bold">관람시간</a>
      <span id="price2">&nbsp;&nbsp;&nbsp;{{show_detail.shour}}</span>
<div style="height: 30px"></div>
      <a style="color:black;font-weight: bold">출연</a>
      <span id="price2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{show_detail.sperformer}}</span>
<div style="height: 30px"></div>
      <a style="color:black;font-weight: bold">가격</a>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="../images/seat.png">
<div style="height: 30px"></div>
      <div style="margin-top: 0px"><a class="btn btn-primary py-1 px-3 mt-1 wow fadeInUp" :href="'../show/reserve.do?sno='+show_detail.sno" data-wow-delay="0.1s" style="margin-left: 85%;">예매하기</a></div>
    </td>
  </tr>
  <tr>
    <td width="65%">
      <a style="color:black;font-weight: bold">공연시간 안내</a><br>
      <span id="price2">{{show_detail.stime}}</span>
<div style="height: 30px"></div>
      <a style="color:black;font-weight: bold">배송정보</a><br>
      <span id="price2">{{show_detail.sdelivery}}</span>
    </td>
  </tr>
  </table>
  <table class="table">
  <tr>
    <td>좋아요</td>
  </tr>
  </table>
<a @click="showInfo" id="info" style="font-size: x-large;margin-left: 41%;">상세정보&nbsp;|&nbsp;</a>
<a @click="showReview" id="review" style="font-size: x-large;">리뷰</a>
<div style="height: 10px;"></div>
  <table class="table text-center" v-show="isShowInfo">
    <tr v-for="dp in sdeposter">
      <td style="padding: 0%;border-bottom-width: 0px">
        <img :src="dp" style="width: 80%;height: 80%">
      </td>
    </tr>
  </table>
<div style="height: 50px"></div>
  <table class="table" v-show="isShowloc1">
    <tr>
      <td>
        <h3 id="sloc" class="text-center"><i class="fa fa-map-marker" aria-hidden="true" style="color:#666565;margin-right: 5px"></i>{{show_detail.sloc}}</h3>
          <h6 class="text-center" style="color: #666565;">{{show_detail.saddress}}&nbsp;&nbsp;|&nbsp;&nbsp;{{show_detail.sphone}}</h6>
      </td>
    </tr>
  </table>
  <div id="map"style="height:500px;width: 920.317px;margin-left: 10.7%" v-show="isShowloc2"></div>
  <table class="table" v-show="isShowReview">
    <s_review  v-bind:show_review="show_review"></s_review>
  </table>
</div>
<script>
  const reviewComponent={
		  props:['show_review'],
		  template:`<tr>
                      <td>
                      <h3>리뷰</h3>
                      </td>
  					</tr>
  					`
  }
  let showDetailApp=Vue.createApp({
	  data(){
		  return {
			  show_detail:{},
			  sno:${sno},
			  sdeposter:[],
			  isShowInfo:true,
			  isShowReview:false,
			  isShowloc1:true,
			  isShowloc2:true
		  }
	  },
	  mounted(){
		  axios.get('../show/detail_vue.do',{
			  params:{
				  sno:this.sno
			  }
		  }).then(response=>{
			  console.log(response.data)
			  this.show_detail=response.data
			  this.sdeposter=response.data.sdeposter.split(",")
			  if(window.kakao && window.kakao.maps)
			  {
				  this.initMap()
			  }
			  else
			  {
				  this.addScript()
			  }
		  })
	  },
	  methods:{
		  showReview(){
			  this.isShowInfo=false
			  this.isShowReview=true
			  this.isShowloc1=false
			  this.isShowloc2=false
		  },
		  showInfo(){
			  this.isShowInfo=true
			  this.isShowloc1=true
			  this.isShowloc2=true
			  this.isShowReview=false
		  },
		  addScript(){
			  const script=document.createElement("script")
			  
			  /* global kakao */
			  script.onload=()=>kakao.maps.load(this.initMap)
			  script.src="http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=d2619d279eb9fac7ba95f6d01b516779&libraries=services"
			  document.head.appendChild(script)
		  },
		  initMap(){
			  var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			    mapOption = {
			        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
			        level: 3 // 지도의 확대 레벨
			    };  

			// 지도를 생성합니다    
			var map = new kakao.maps.Map(mapContainer, mapOption); 

			// 주소-좌표 변환 객체를 생성합니다
			var geocoder = new kakao.maps.services.Geocoder();

			// 주소로 좌표를 검색합니다
			geocoder.addressSearch(this.show_detail.saddress, function(result, status) {

			    // 정상적으로 검색이 완료됐으면 
			     if (status === kakao.maps.services.Status.OK) {

			        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

			        // 결과값으로 받은 위치를 마커로 표시합니다
			        var marker = new kakao.maps.Marker({
			            map: map,
			            position: coords
			        });

			        // 인포윈도우로 장소에 대한 설명을 표시합니다
			        var infowindow = new kakao.maps.InfoWindow({
			            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+$("#sloc").text()+'</div>'
			        });
			        infowindow.open(map, marker);

			        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
			        map.setCenter(coords);
			    } 
			});
		  }
	  },
	  components:{
		  's_review':reviewComponent
	  }
  }).mount('#showDetailApp')
</script>
</body>
</html>