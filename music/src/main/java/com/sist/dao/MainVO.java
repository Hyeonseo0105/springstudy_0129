package com.sist.dao;

import lombok.Data;
/*
이미지
제목
작가
출판사
발행일
평점
정가
판매가
상세이미지
저자소개
*/
@Data
public class MainVO {
	private int no;
	private String name;
	private String author;
	private String poster;
	private String publ,regdate;
	private String score;
	private String price,saleprice,detailposter,intro;
}
