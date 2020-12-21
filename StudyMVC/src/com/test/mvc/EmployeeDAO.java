/*=================================================
  #09. EmployeeDAO.java
  		- 데이터베이스 액션 처리 클래스
  	    - 직원 데이터 입출력 및 수정 삭제.
  	    - Connection 객체에 대한 의존성 주입 준비.
  	      (인터페이스 자료형 / setter 구성)
===================================================*/
package com.test.mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class EmployeeDAO implements IEmployeeDAO
{
	// 인터페이스 자료형 구성
	private DataSource dataSource;
	
	// setter 구성
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	// 인터페이스에서 선언된 메소드 재정의---------------------------------------------

	// 직원 전체 리스트 출력 
	@Override
	public ArrayList<Employee> list() throws SQLException
	{
		ArrayList<Employee> result = new ArrayList<Employee>();
		Connection conn = dataSource.getConnection();
		
		String sql = "SELECT EMPLOYEEID, NAME, SSN, BIRTHDAY, LUNAR, LUNARNAME"
				+ ", TELEPHONE, DEPARTMENTID, DEPARTMENTNAME, POSITIONID, POSITIONNAME"
				+ ", REGIONID, REGIONNAME,BASICPAY, EXTRAPAY,PAY,GRADE FROM EMPLOYEEVIEW"
				+ " ORDER BY EMPLOYEEID";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next())
		{
			Employee employee = new Employee();
			employee.setEmployeeId(rs.getString("EMPLOYEEID"));
			employee.setName(rs.getString("NAME"));
			employee.setSsn(rs.getString("SSN"));
			employee.setBirthday(rs.getString("BIRTHDAY"));
			employee.setLunar(rs.getInt("LUNAR"));
			employee.setLunarName(rs.getString("LUNARNAME"));
			employee.setTelephone(rs.getString("TELEPHONE"));
			employee.setDepartmentId(rs.getString("DEPARTMENTID"));
			employee.setDepartmentName(rs.getString("DEPARTMENTNAME"));
			employee.setPositionId(rs.getString("POSITIONID"));
			employee.setPositionName(rs.getString("POSITIONNAME"));
			employee.setRegionId(rs.getString("REGIONID"));
			employee.setRegionName(rs.getString("REGIONNAME"));
			employee.setBasicPay(rs.getInt("BASICPAY"));
			employee.setExtraPay(rs.getInt("EXTRAPAY"));
			employee.setPay(rs.getInt("PAY"));
			employee.setGrade(rs.getInt("GRADE"));
			
			result.add(employee);
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return result;
	}

	// 지역 전체 리스트 출력
	@Override
	public ArrayList<Region> regionList() throws SQLException
	{
		ArrayList<Region> result = new ArrayList<Region>();
		
		Connection conn = dataSource.getConnection();
		String sql = "SELECT REGIONID, REGIONNAME, DELCHECK FROM REGIONVIEW "
				+ " ORDER BY REGIONID";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next())
		{
			Region region = new Region();
			region.setRegionId(rs.getString("REGIONID"));
			region.setRegionName(rs.getString("REGIONNAME"));
			region.setDelCheck(rs.getInt("DELCHECK"));
			
			result.add(region);
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return result;
	}

	// 부서 전체 리스트 출력
	@Override
	public ArrayList<Department> departmentList() throws SQLException
	{
		ArrayList<Department> result = new ArrayList<Department>();
		
		Connection conn = dataSource.getConnection();
		
		String sql = "SELECT DEPARTMENTID, DEPARTMENTNAME, DELCHECK FROM DEPARTMENTVIEW"
				+ " ORDER BY DEPARTMENTID";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			Department department = new Department();
			department.setDepartmentId(rs.getString("DEPARTMENTID"));
			department.setDepartmentName(rs.getString("DEPARTMENTNAME"));
			department.setDelCheck(rs.getInt("DELCHECK"));
			
			result.add(department);
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		return result;
	}

	// 직위 전체 리스트 출력 
	@Override
	public ArrayList<Position> positionList() throws SQLException
	{
		ArrayList<Position> result = new ArrayList<Position>();
		
		Connection conn = dataSource.getConnection();
		
		String sql = "SELECT POSITIONID, POSITIONNAME, MINBASICPAY, DELCHECK FROM POSITIONVIEW"
				+ " ORDER BY POSITIONID";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next())
		{
			Position position = new Position();
			position.setPositionId(rs.getString("POSITIONID"));
			position.setPositionName(rs.getString("POSITIONNAME"));
			position.setMinBasicPay(rs.getInt("MINBASICPAY"));
			position.setDelCheck(rs.getInt("DELCHECK"));
			
			result.add(position);
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		return result;
	}

	// 직위별 최소 금액 반환
	@Override
	public int getMinBacisPay(String positionId) throws SQLException
	{
		int result = 0;
		Connection conn = dataSource.getConnection();
		
		String sql = "SELECT MINBASICPAY FROM POSITION WHERE POSITIONID =?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		//pstmt.setString(1, positionId);
		pstmt.setInt(1,Integer.parseInt(positionId));
		ResultSet rs = pstmt.executeQuery();
		if(rs.next())
		{
			result = rs.getInt("MINBASICPAY");
		}
		
		return result;
	}
	
	// 직원 데이터 입력 메소드
	@Override
	public int employeeAdd(Employee employee) throws SQLException
	{
		int result = 0;
		
		Connection conn = dataSource.getConnection();
		String sql ="INSERT INTO EMPLOYEE(EMPLOYEEID, NAME, SSN1, SSN2, BIRTHDAY, LUNAR"
				+ ", TELEPHONE, DEPARTMENTID, POSITIONID, REGIONID, BASICPAY, EXTRAPAY)"
				+ " VALUES(EMPLOYEESEQ.NEXTVAL, ?, ?, CRYPTPACK.ENCRYPT(?, ?)"
				+ ", TO_DATE(?, 'YYYY-MM-DD'),?, ?, ?, ?, ?, ?, ?)"; 
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, employee.getName());
		pstmt.setString(2, employee.getSsn1());
		pstmt.setString(3, employee.getSsn2());
		pstmt.setString(4, employee.getSsn2());
		pstmt.setString(5, employee.getBirthday());
		pstmt.setInt(6, employee.getLunar());
		pstmt.setString(7, employee.getTelephone());
		pstmt.setString(8, employee.getDepartmentId());
		pstmt.setString(9, employee.getPositionId());
		pstmt.setString(10, employee.getRegionId());
		pstmt.setInt(11, employee.getBasicPay());
		pstmt.setInt(12, employee.getExtraPay());
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return result;
	}

	// 직원 데이터 삭제 메소드
	@Override
	public int remove(String employeeId) throws SQLException
	{
		int result = 0;
		
		Connection conn = dataSource.getConnection();
		
		String sql = "DELETE FROM EMPLOYEE WHERE EMPLOYEEID = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, employeeId);
		result = pstmt.executeUpdate();
		
		return result;
	}

	// 직원 데이터 수정(변경) 메소드
	@Override
	public int modify(Employee employee) throws SQLException
	{
		int result = 0;
		
		Connection conn = dataSource.getConnection();
		String sql = "UPDATE EMPLOYEE"
	            + " SET NAME=?, SSN1=?, SSN2=CRYPTPACK.ENCRYPT(?, ?)"
	            + ", BIRTHDAY=TO_DATE(?, 'YYYY-MM-DD'), LUNAR=?"
	            + ", TELEPHONE=?, DEPARTMENTID=?, POSITIONID=?, REGIONID=?, BASICPAY=?, EXTRAPAY=? WHERE EMPLOYEEID=?";
	      
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, employee.getName());
	    pstmt.setString(2, employee.getSsn1());
	    pstmt.setString(3, employee.getSsn2());
	    pstmt.setString(4, employee.getSsn2());
	    pstmt.setString(5, employee.getBirthday());
	    pstmt.setInt(6, employee.getLunar());
	    pstmt.setString(7, employee.getTelephone());
	    pstmt.setString(8, employee.getDepartmentId());
	    pstmt.setString(9, employee.getPositionId());
	    pstmt.setString(10, employee.getRegionId());
	    pstmt.setInt(11, employee.getBasicPay());
	    pstmt.setInt(12, employee.getExtraPay());
	    pstmt.setString(13, employee.getEmployeeId());
		   
	    result = pstmt.executeUpdate();
	    
		pstmt.close();
		conn.close();
		
		return result;
	}

	// 직원 검색 메소드
	@Override
	public Employee searchId(String employId) throws SQLException
	{
		Employee result = new Employee();
		
		Connection conn = dataSource.getConnection();
		String sql = "SELECT EMPLOYEEID, NAME, SSN, BIRTHDAY, LUNAR, TELEPHONE, DEPARTMENTID, POSITIONID"
				+ ", REGIONID, BASICPAY, EXTRAPAY"
				+ " FROM EMPLOYEEVIEW WHERE EMPLOYEEID = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, Integer.parseInt(employId));
		
		ResultSet rs = pstmt.executeQuery();
		while(rs.next())
		{
			result.setEmployeeId(rs.getString("EMPLOYEEID"));
			result.setName(rs.getString("NAME"));
			result.setSsn1(rs.getString("SSN"));
			result.setBirthday(rs.getString("BIRTHDAY"));
			result.setLunar(rs.getInt("LUNAR"));
			result.setTelephone(rs.getString("TELEPHONE"));
			result.setDepartmentId(rs.getString("DEPARTMENTID"));
			result.setPositionId(rs.getString("POSITIONID"));
			result.setRegionId(rs.getString("REGIONID"));
			result.setBasicPay(rs.getInt("BASICPAY"));
			result.setExtraPay(rs.getInt("EXTRAPAY"));
			
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return result;
		
	}// end searchId

	
	
	// 로그인 기능과 관련한 메소드 추가~!!! --------------------------------------------------
	
	// 일반 직원 로그인
	@Override
	public String login(String id, String pw) throws SQLException
	{
		String result = null;
		
		Connection conn = dataSource.getConnection();
		String sql = "SELECT NAME"
				+ " FROM EMPLOYEE WHERE EMPLOYEEID=?"
				+ " AND SSN2 = CRYPTPACK.ENCRYPT(?, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, Integer.parseInt(id));
		pstmt.setString(2, pw);
		pstmt.setString(3, pw);
		
		ResultSet rs = pstmt.executeQuery();
		while(rs.next())
		{
			result = rs.getString("NAME");
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return result;
	}
	
	// 관리자 로그인
	@Override
	public String loginAdmin(String id, String pw) throws SQLException
	{
		String result = null;
		
		Connection conn = dataSource.getConnection();
		String sql = "SELECT NAME"
				+ " FROM EMPLOYEE"
				+ " WHERE EMPLOYEEID=?"
				+ " AND SSN2 = CRYPTPACK.ENCRYPT(?,?)"
				+ " AND GRADE=0";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, Integer.parseInt(id));
		pstmt.setString(2, pw);
		pstmt.setString(3, pw);
		
		ResultSet rs = pstmt.executeQuery();
		while(rs.next())
		{
			result = rs.getString("NAME");
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return result;
	}
	
	// ---------------------------------------------------로그인 기능과 관련한 메소드 추가~!!!
	
	
	
	// 일반직원이 조회하는 직원 전체 리스트 출력 (추가)
	@Override
	public ArrayList<Employee> empList() throws SQLException
	{
		ArrayList<Employee> result = new ArrayList<Employee>();
		Connection conn = dataSource.getConnection();
		
		String sql = "SELECT EMPLOYEEID, NAME, SSN, BIRTHDAY"
				+ ", LUNARNAME, TELEPHONE"
				+ ", DEPARTMENTNAME, POSITIONNAME, REGIONNAME"
				+ " FROM EMPLOYEEVIEW"
				+ " ORDER BY EMPLOYEEID";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			Employee employee = new Employee();
			employee.setEmployeeId(rs.getString("EMPLOYEEID"));
			employee.setName(rs.getString("NAME"));
			employee.setSsn(rs.getString("SSN"));
			employee.setBirthday(rs.getString("BIRTHDAY"));
			employee.setLunarName(rs.getString("LUNARNAME"));
			employee.setTelephone(rs.getString("TELEPHONE"));
			employee.setDepartmentName(rs.getString("DEPARTMENTNAME"));
			employee.setPositionName(rs.getString("POSITIONNAME"));
			employee.setRegionName(rs.getString("REGIONNAME"));
			
			result.add(employee);
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return result;
	}
	
	
	
	
}//end EmployeeDAO


