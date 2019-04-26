package com.biz.bbs.service;

import java.util.ArrayList;
import java.util.List;

import com.biz.bbs.dao.BBsMainDao;
import com.biz.bbs.dao.BBsMainDaoImp;
import com.biz.bbs.vo.BBsMainVO;

public class BBsMainService {
	
	List<BBsMainVO> bbsMainList ;
	BBsMainDao mainDao ;
	
	public BBsMainService() {
		
		bbsMainList = new ArrayList();
		mainDao = new BBsMainDaoImp();
	}
	
	public void viewBBsList() {
		bbsMainList = mainDao.selectAll();
		System.out.println("====================================================================");
		System.out.println("나의 게시판 v1.0");
		System.out.println("====================================================================");
		System.out.println("NO\t날짜\t작성자\t제목\t\t내용");
		System.out.println("--------------------------------------------------------------------");
		for(BBsMainVO v : bbsMainList) {
			System.out.print(v.getB_id()+"  ");
			System.out.print(v.getB_date()+"\t");
			System.out.print(v.getB_auth()+"\t");
			System.out.print(v.getB_subject()+"\t\t");
			System.out.println(v.getB_text());
		}
	}

}
