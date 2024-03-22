package com.sist.dao;

import javax.lang.model.element.Element;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MainClass_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			MainDAO dao=new MainDAO();
			for(int i=1;i<=5;i++)
			{	
				Document doc=Jsoup.connect("https://www.yes24.com/Product/Category/BestSeller?categoryNumber=001&pageNumber="+i+"&pageSize=24").get();
				Elements link=doc.select("div.itemUnit span.img_grp a");
				Elements poster=doc.select("em.img_bdr img");

				for(int j=0;j<link.size();j++)
				{
					try
					{
						//이미지
						String image=poster.get(j).attr("data-original");
						String subLink="https://www.yes24.com/"+link.get(j).attr("href");

						Document doc2=Jsoup.connect(subLink).get();
					
						//제목
						Elements title=doc2.select("div.gd_titArea h2.gd_name");
					
						//작가
						Elements author=doc2.select("span.gd_pubArea span.gd_auth");
					
						//출판사
						Elements publ=doc2.select("span.gd_pubArea span.gd_pub");
					
						//발행일
						Elements date=doc2.select("span.gd_pubArea span.gd_date");
						
						//평점
						Elements score=doc2.select("span.gd_ratingArea em.yes_b");
					
						//정가
						Elements price=doc2.select("div.gd_infoTb tr:first-child td em.yes_m");

						//판매가
						Elements saleprice=doc2.select("div.gd_infoTb tr:nth-child(2) td");

						//상세이미지
						Elements dePoster=doc2.select("div.infoSetCont_wrap div.infoWrap_txt img");
						
						//저자소개
						Elements intro=doc2.select("div.authorCont div.row_basicInfo span.info_crop");


						String msg=	
								title.text()+"|"
								+author.text()+"|"
								+image + "|"
								+publ.text()+"|"
								+date.text()+"|"
								+score.text()+"|"
								+price.text() +"|"
								+saleprice.text() +"|"
								+dePoster.attr("data-original") +"|"
								+intro.text()+"|"+"\r\n";
						msg=msg.substring(0,msg.lastIndexOf("|"));
						msg+="\r\n";
				
						System.out.println(msg);
						
						MainVO vo=new MainVO();
						
						vo.setName(title.text());
						vo.setAuthor(author.text());
						vo.setPoster(image);
						vo.setPubl(publ.text());
						vo.setRegdate(date.text());
						vo.setScore(score.text());
						vo.setPrice(price.text());
						vo.setSaleprice(saleprice.text());
						vo.setDetailposter(dePoster.attr("data-original"));
						vo.setIntro(intro.text());
						
						dao.musicInsert(vo);
					}catch(Exception ex) {}
				}
				
			}
			
			System.out.println("저장 완료!!");
		}catch(Exception ex) {}

	}

}
