/*=================================================
	#36. RegionUpdateController.java
  		- 사용자 정의 컨트롤러 클래스
  		- 지역 데이터 수정 액션 수행 맻 해당 액션 수행 이후
  		  『regionlist.action』을 요청할 수 있도록 처리.
  		- DAO 객체에 대한 의존성 주입(DI)를 위한 준비.
  		  → 인터페이스 자료형, setter 메소드 정의.
===================================================*/
package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class RegionUpdateController implements Controller
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

		// 데이터 수신(RegionUpdateForm.jsp 로부터... region 을 구성하는 속성들 수신
		String regionId = request.getParameter("regionId");
		String regionName = request.getParameter("regionName");
		
		try
		{
			Region region = new Region();
			region.setRegionId(regionId);
			region.setRegionName(regionName);
			
			dao.modify(region);
			
			mav.setViewName("redirect:regionlist.action");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
				
		
		return mav;
	}
		
}
