<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<style type="text/css">
.rounded {
      -moz-border-radius:20px 20px 20px 20px; 
      border-radius:20px 20px 20px 20px;
      border:solid 1px #ffffff;
      background-color:#2b6bd1;
      padding:10px;
      color:#ffffff;
    }
td.link:hover,tr.tr_link:hover{
    cursor: pointer;
  }
</style>
</head>
<body>
<div style="height: 100px"></div>
<div class="container-xxl bg-white p-0" id="reserveApp">
<table class="table" style="width:1320px;height: 660px;border: 1">
  <tr>
    <td width=15% colspan="2" style="border-bottom-width:0px;"><img :src="reserve_detail.sposter" style="height: 500px"></td>
    <td width=45% style="border-bottom-width:0px;margin-left: 5%">
    <div class="calendar">
				      <h2 class="text-center">
				        <a href="#" v-on:click="onClickPrev(currentMonth)">◀</a>
				        {{currentYear}}년 {{currentMonth}}월
				        <a href="#" v-on:click="onClickNext(currentMonth)">▶</a>
				      </h2>
				      <table class="table table-hover">
				          <thead>
				            <tr>
				              <td v-for="(weekName, index) in weekNames" v-bind:key="index">
				                {{weekName}}
				              </td>
				            </tr>
				          </thead>
				          <tbody>
				            <tr v-for="(row, index) in currentCalendarMatrix" :key="index">
				              <td v-for="(day, index2) in row" :key="index2" style="padding:20px;" :class="day>=realDay?'link':''">
				                <span v-if="day>=realDay" @click="change(day)" style="color:black">
					                <span v-if="day===currentDay" class="rounded">
					                  {{day}}
					                </span>
					                <span v-else>
					                  {{day}}
					                </span>
				                </span>
				                <span v-else style="color:gray">
				                   {{day}}
				                </span>
				              </td>
				            </tr>
				          </tbody>
				      </table>    
				  </div>
    </td>
    <td width=40% style="border-bottom-width:0px;">{{ssdate}}</td>
  </tr>
  <tr>
    <td style="border-bottom-width:0px;">공연명</td>
    <td style="border-bottom-width:0px;">{{reserve_detail.stitle}}</td>
    <td rowspan="4" style="border-bottom-width:0px;">시간</td>
  </tr>
  <tr>
    <td style="border-bottom-width:0px;">공연장소</td>
    <td style="border-bottom-width:0px;">{{reserve_detail.sloc}}</td>
  </tr>
  <tr>
    <td style="border-bottom-width:0px;">등급</td>
    <td style="border-bottom-width:0px;">{{reserve_detail.sgrade}}</td>
  </tr>
  <tr>
    <td style="border-bottom-width:0px;">관람시간</td>
    <td style="border-bottom-width:0px;">{{reserve_detail.shour}}</td>
  </tr>
</table>
</div>
<script>
let reserveApp=Vue.createApp({
	  data(){
		  return {
			  reserve_detail:{},
			  sno:${sno},
			  weekNames: ["월요일", "화요일", "수요일","목요일", "금요일", "토요일", "일요일"],
		      rootYear: 1904,
		      rootDayOfWeekIndex: 4, // 2000년 1월 1일은 토요일
		      currentYear: new Date().getFullYear(),
		      currentMonth: new Date().getMonth()+1,
		      currentDay: new Date().getDate(),
		      currentMonthStartWeekIndex: null,
		      currentCalendarMatrix: [],
		      endOfDay: null,
		      memoDatas: [],
		      realDay:new Date().getDate(),
		      ssdate:'',
		      esdate:''
		  }
	  },
	  mounted(){
		  this.init()
		  axios.get('../show/reserve_vue.do',{
			  params:{
				  sno:this.sno
			  }
		  }).then(response=>{
			  console.log(response.data)
			  this.reserve_detail=response.data
			  sssdate=response.data.sdate.substring(0,response.data.sdate.indexOf("~"));
			  this.ssdate=sssdate.substring(8);
			  eesdate=response.data.sdate.substring(response.data.sdate.indexOf("~")+2);
			  this.esdate=eesdate.substring(8)
			  console.log(this.ssdate)
			  console.log(this.esdate)
			  
		  })
	  },
	  methods:{
		  init(){
		        this.currentMonthStartWeekIndex = this.getStartWeek(this.currentYear, this.currentMonth);
		        this.endOfDay = this.getEndOfDay(this.currentYear, this.currentMonth);
		        this.initCalendar();
		      },
		      initCalendar(){
		        this.currentCalendarMatrix = [];
		        let day=1;
		        for(let i=0; i<6; i++){
		          let calendarRow = [];
		          for(let j=0; j<7; j++){
		            if(i==0 && j<this.currentMonthStartWeekIndex){
		              calendarRow.push("");
		            }
		            else if(day<=this.endOfDay){
		              calendarRow.push(day);
		              day++;
		            }
		            else{
		              calendarRow.push("");
		            }
		          }
		          this.currentCalendarMatrix.push(calendarRow);
		        }
		      },
		      getEndOfDay(year, month){
		          switch(month){
		              case 1:
		              case 3:
		              case 5:
		              case 7:
		              case 8:
		              case 10:
		              case 12:
		                return 31;
		                break;
		              case 4:
		              case 6:
		              case 9:
		              case 11:
		                return 30;
		                break;
		              case 2:
		                if( (year%4 == 0) && (year%100 != 0) || (year%400 == 0) ){
		                return 29;   
		                }
		                else{
		                    return 28;
		                }
		                break;
		              default:
		                console.log("unknown month " + month);
		                return 0;
		                break;
		          }
		      },
		      getStartWeek(targetYear, targetMonth){
		        let year = this.rootYear;
		        let month = 1;
		        let sumOfDay = this.rootDayOfWeekIndex;
		        while(true){
		          if(targetYear > year){
		            for(let i=0; i<12; i++){
		              sumOfDay += this.getEndOfDay(year, month+i);
		            }
		            year++;
		          }
		          else if(targetYear == year){
		            if(targetMonth > month){
		              sumOfDay += this.getEndOfDay(year, month);
		              month++;
		            }
		            else if(targetMonth == month){
		              return (sumOfDay)%7;
		            }
		          }
		        }
		      },
		      onClickPrev(month){
		        month--;
		        if(month<=0){
		          this.currentMonth = 12;
		          this.currentYear -= 1;
		        }
		        else{
		          this.currentMonth -= 1;
		        }
		        this.init();
		      },
		      onClickNext(month){
		        month++;
		        if(month>12){
		          this.currentMonth = 1;
		          this.currentYear += 1;
		        }
		        else{
		          this.currentMonth += 1;
		        }
		        this.init();
		      },
		      isToday: function(year, month, day){
		        let date = new Date();
		        return year == date.getFullYear() && month == date.getMonth()+1 && day == day; 
		      },
		      change(day){
		    	 this.currentDay=day;
		    	 this.tShow=true
		      }
	  }
}).mount('#reserveApp')
</script>
</body>
</html>