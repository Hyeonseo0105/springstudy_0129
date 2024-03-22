<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<%--
    Front : 유지보수(***jquery,ajax)
            vuejs , ***reactjs => nuxtjs , moment , nodejs
            => TypeScript
    Back : 유지보수(***SpringFramework)
           ***Spring-Boot , MSA
           ***MyBatis / JAP
           MySQL / MariaDB
    =================================> DevOps (CI/CD) => 도커 , 젠킨스 (***GIT)
--%>
<body>
  <div class="wrapper row3 row1" id="mypageApp">
    <main class="container clear">
      <h2 class="sectiontitle">예약 목록</h2>
      <table class="table">
        <tr>
          <th>번호</th>
          <th></th>
          <th>업체명</th>
          <th>예약일</th>
          <th>예약시간</th>
          <th>인원</th>
          <th></th>
        </tr>
        <tr v-for="vo in reserve_list">
          <td>{{vo.rno}}</td>
          <td>
            <img :src="'https://www.menupan.com'+vo.fvo.poster" style="width:30px;height:30px">
          </td>
          <td>{{vo.fvo.name}}</td>
          <td>{{vo.rdate}}</td>
          <td>{{vo.rtime}}</td>
          <td>{{vo.rinwon}}</td>
          <td>
            <span :class="vo.reserve_ok==1?'btn btn-xs btn-danger':'btn btn-xs btn-default'" v-text="vo.reserve_ok==0?'예약대기':'예약완료'"></span>
            &nbsp;<span class="btn btn-xs btn-primary" @click="reserveCancel(vo.rno)">취소</span>
          </td>
        </tr>
      </table>
    </main>
  </div>
<script>
  let mypageApp=Vue.createApp({
	  data(){
		  return{
			  reserve_list:[]
		  }
	  },
	  mounted(){
		  axios.get('../reserve/mypage_list_vue.do')
		  .then(response=>{
			  console.log(response.data)
			  this.reserve_list=response.data
		  })
	  },
	  methods:{
		  reserveCancel(rno){
			  axios.get('../reserve/reserve_cancel_vue.do',{
				  params:{
					  rno:rno
				  }
			  }).then(response=>{
				  if(response.data=='yes')
				  {
					  axios.get('../reserve/mypage_list_vue.do')
					  .then(response=>{
						  console.log(response.data)
						  this.reserve_list=response.data
					  })
				  }
				  else
				  {
					  alert("예약 취소를 실패 하셨습니다\n다시 취소 바랍니다")
				  }
			  })
		  }
	  }
  }).mount('#mypageApp')
</script>
</body>
</html>