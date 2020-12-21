/*=================================================
  #11. RegionDAO.java
  		- 데이터베이스 액션 처리 클래스
  	    - 지역 데이터 입출력 및 수정 삭제.
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


public class RegionDAO implements IRegionDAO
{
	// 인터페이스형 변수(의존객체 주입 준비)
	private DataSource dataSource;

	// setter  메소드 
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	// 지역 전체 출력 메소드
	@Override
	public ArrayList<Region> list() throws SQLException
	{
		ArrayList<Region> result = new ArrayList<Region>();
		Connection conn = dataSource.getConnection();
		
		String sql = "SELECT REGIONID, REGIONNAME, DELCHECK FROM REGIONVIEW ORDER BY REGIONID";
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

	// 지역 정보 입력 메소드
	@Override
	public int add(Region region) throws SQLException
	{
		int result = 0;
		Connection conn = dataSource.getConnection();
		
		String sql = "INSERT INTO REGION(REGIONID, REGIONNAME) VALUES(REGIONSEQ.NEXTVAL, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, region.getRegionName());
		result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();

		return result;
	}

	// 지역 정보 삭제 메소드 
	@Override
	public int remove(String regionId) throws SQLException
	{
		int result = 0;
		Connection conn = dataSource.getConnection();
		
		String sql = "DELETE FROM REGION WHERE REGIONID = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, Integer.parseInt(regionId));
		result = pstmt.executeUpdate();
	
		pstmt.close();
		conn.close();
		
		return result;
	}

	// 지역 정보 수정 메소드
	@Override
	public int modify(Region region) throws SQLException
	{
		int result = 0;
		Connection conn = dataSource.getConnection();
		
		String sql = "UPDATE REGION SET REGIONNAME=? WHERE REGIONID= ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, region.getRegionName());
		pstmt.setInt(2, Integer.parseInt(region.getRegionId()));
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return result;
	}
	
	
	//----- 추가
	// 지역 검색 메소드
	public Region searchId(String regionId) throws SQLException
	{
		Region result = new Region();
		
		Connection conn = dataSource.getConnection();
		String sql = "SELECT REGIONID, REGIONNAME, DELCHECK"
				+ " FROM REGIONVIEW WHERE REGIONID = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, Integer.parseInt(regionId));
		
		ResultSet rs = pstmt.executeQuery();
		while(rs.next())
		{
			result.setRegionId(rs.getString("REGIONID"));  
			result.setRegionName(rs.getString("REGIONNAME")); 
			
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return result;
		
	}// end searchId
	
}
