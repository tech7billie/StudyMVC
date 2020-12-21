/*===============================================
  #62. RegionAjaxController.java
  - 사용자 정의 컨트롤러 클래스.
  - 지역 이름 중복 여부 반환 액션.
  - DAO 객체에 대한 의존성 주입(DI)을 위한 준비.
     → 인터페이스 자료형, setter 메소드 정의  
=================================================*/
package com.test.mvc;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class RegionAjaxController implements Controller
{
	private IRegionDAO dao;
	
	public void setDao(IRegionDAO dao)
	{
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		
		// 데이터 수신 (regionUpdateForm.jsp 로부터 regionName 수신)  
		String regionName = request.getParameter("regionName");
		
		int result = 0;
		
		try
		{
			ArrayList<Region> regionList = dao.list();
			
			for (Region region : regionList)
			{
				if(region.getRegionName().equals(regionName)) // 입력 받은 이름이 데이터베이스에 있으면
				{
					result = 1;
					mav.addObject("result", result);
					mav.setViewName("RegionAjax");
					
					return mav;
				}
				
			}
			
			// 입력받은 이름이 데이터베이스에 없으면 
			// result=0;
			
			mav.addObject("result", result);
			mav.setViewName("RegionAjax");
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;
	}
	
}
