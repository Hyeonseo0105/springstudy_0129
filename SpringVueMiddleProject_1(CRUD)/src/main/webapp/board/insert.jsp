<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
   margin-top: 50px;
}
.row{
   margin: 0px auto;
   width: 700px;
}
</style>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
  <div class="container">
    <h3 class="text-center">글쓰기</h3>
    <div class="row">
    <%--
    	 form => 기본 기능 (서버로 데이터 전송)
    	      => 화면 reset
    	      => 기능을 동작하지 못하게 만든다 @submit.prevent
    --%>
     <form @submit.prevent="submitForm">
      <table class="table">
        <tr>
          <th width=15% class="text-center">이름</th>
          <td width=85%>
            <input type=text size=15 class="input-sm" v-model="name" ref="name">
          </td>
        </tr>
        <tr>
          <th width=15% class="text-center">제목</th>
          <td width=85%>
            <input type=text size=50 class="input-sm" v-model="subject" ref="subject">
          </td>
        </tr>
        <tr>
          <th width=15% class="text-center">내용</th>
          <td width=85%>
            <textarea rows="10" cols="52" v-model="content" ref="content"></textarea>
          </td>
        </tr>
        <tr>
          <th width=15% class="text-center">비밀번호</th>
          <td width=85%>
            <input type=password size=15 class="input-sm" v-model="pwd" ref="pwd">
          </td>
        </tr>
        <tr>
          <td colspan="2" class="text-center">
            <input type="submit" value="글쓰기" class="btn-sm btn-success">
            <input type="button" value="취소" onclick="javascript:history.back()" class="btn-sm btn-info">
          </td>
        </tr>
      </table>
     </form>
    </div>
  </div>
<script>
  let app=Vue.createApp({
	  data(){
		  return {
			  name:'',
			  subject:'',
			  content:'',
			  pwd:''
		  }
	  },
	  methods:{
		  submitForm(){
			  if(this.name==='')
			  {
				  this.$refs.name.focus()
				  return
			  }
			  if(this.subject==='')
			  {
				  this.$refs.subject.focus()
				  return
			  }
			  if(this.content==='')
			  {
				  this.$refs.content.focus()
				  return
			  }
			  if(this.pwd==='')
			  {
				  this.$refs.pwd.focus()
				  return
			  }
			  // form은 포스트 방식
			  /*
			  let form=new FormData()
			  form.append("name",this.name)
			  form.append("subject",this.subject)
			  form.append("content",this.content)
			  form.append("pwd",this.pwd)
			  */
			  //form안쓰면 {params:{name:this.name}}
			  // 스프링에서 post방식 : 두번째 인자가 3번째부터 변환됨
			  // 두번째에 null값을 줘야함
			  //axios.get('../board/insert_ok.do',null,{
			  axios.get('../board/insert_ok.do',{
				  params:{
					  name:this.name,
				  	  subject:this.subject,
				  	  content:this.content,
				  	  pwd:this.pwd
				  }
				 // try
			  }).then(response=>{
			            	 location.href="../board/list.do"
			             })
			             // catch
			             .catch(error=>{
			            	 console.log(error.response)
			             })
		  }
	  }
  }).mount('.container')
</script>
</body>
</html>