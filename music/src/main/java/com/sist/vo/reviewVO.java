package com.sist.vo;

import java.util.*;
import lombok.Data;

@Data
public class reviewVO {
	private int no,typeno,fno,page,count;
	private double score;
	private String userId,userName,msg,dbday;
	private Date regdate;
}
