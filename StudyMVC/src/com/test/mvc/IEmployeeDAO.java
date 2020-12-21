/*========================
  #05. IEmployeeDAO.java
  		- 인터페이스
==========================*/
package com.test.mvc;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IEmployeeDAO
{
	// 추후 EmployeeDAO 에서 정의할 것으로 예상되는 메소드에 대한 선언
	
	public ArrayList<Employee> list() throws SQLException;
	
	//! 아래 메소드를 사용하려면 Region, Department, Position.java 를 주입해야하니까 
	//  편의상 여기에 작성한 것 →  직원 정보 입력폼에서 셀렉트박스로 보여주려고
	public ArrayList<Region> regionList() throws SQLException;
	public ArrayList<Department>departmentList() throws SQLException;
	public ArrayList<Position>positionList() throws SQLException;
	
	public int getMinBacisPay(String positionId) throws SQLException;
	public int employeeAdd(Employee employee) throws SQLException;
	public int remove(String employeeId) throws SQLException;
	public int modify(Employee emloyee) throws SQLException;
	public Employee searchId(String employeeId) throws SQLException;//! 직원 수정, 삭제 기능을 위해서

	// 로그인 기능과 관련한 메소드 추가~!!! --------------------------------------------------	
	public String login(String id, String pw) throws SQLException;
	public String loginAdmin(String id, String pw) throws SQLException;
	
	// 일반 직원이 조회하는 직원 전체 리스트 출력(추가)
	public ArrayList<Employee> empList() throws SQLException;
	
}
