/*===============================================
  #18. AjaxController.java
  - 사용자 정의 컨트롤러 클래스.
  - 직위에 따른 최소 기본급 반환 액션.
  - DAO 객체에 대한 의존성 주입(DI)을 위한 준비.
     → 인터페이스 자료형, setter 메소드 정의  
=================================================*/
package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class AjaxController implements Controller
{
	private IEmployeeDAO dao;
	
	public void setDao(IEmployeeDAO dao)
	{
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		
		// 데이터 수신 (EmployeeInsertForm.jsp 로부터 positionId 수신)  
		String positionId = request.getParameter("positionId");
		
		int result = 0;
		
		try
		{
			result = dao.getMinBacisPay(positionId);
			
			mav.addObject("result",result);
			
			//! 여기서 직접 EmployeeInsertForm.jsp 한테 주는거 아니다.
			//mav.setViewName("/WEB-INF/views/Ajax.jsp");
			mav.setViewName("Ajax");
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;
	}
	
}
