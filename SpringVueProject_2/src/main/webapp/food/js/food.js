let app=Vue.createApp({
	   data(){
		   return {
			   food_list:[],   // ArrayList [{},{}...]
			   food_detail:{}, // VO
			   fno:0,
			   isShow:false
		   }
	   },
	   // 브라우저 실행 처리 => window.onload / $(function(){})
	   mounted(){
		   // 서버에서 값을 가져온다          ====response의 값을 가져옴
		   axios.get('http://localhost:8080/web/food/list_vue.do').then(response=>{
			   console.log(response.data)
			   this.food_list=response.data
		   })
	   },
	   methods:{
		   detail(fno){
			   this.isShow=true
			   this.fno=fno;
			   let _this=this
			   axios.get('http://localhost:8080/web/food/detail_vue.do',{
				   params:{
					   fno:_this.fno
				   }
			   }).then(function(response){
				   console.log(response.data)
				   _this.food_detail=response.data;
			   })
		   }
	   }
   }).mount('#app')