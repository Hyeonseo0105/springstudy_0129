package com.sist.vo;
/*
 *   RNO        NOT NULL NUMBER        
FNO                 NUMBER        
RDATE      NOT NULL VARCHAR2(100) 
RTIME      NOT NULL VARCHAR2(20)  
RINWON     NOT NULL VARCHAR2(20)  
REGDATE             DATE          
RESERVE_OK          NUMBER   
 */
import java.util.*;
import lombok.Data;

@Data
public class ReserveVO {
   private int srno,sno,reserve_ok;
   //private String userId;
   private String rDate,rTime,rInwon,rSeat,dbday;
   private Date regdate;
   private ShowVO svo=new ShowVO();
}