package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.sist.db.ConnectionProvider;
import com.sist.vo.ProductVO;

public class ProductDAO {
	public static ProductDAO dao = null;
	private ProductDAO() { }
	public static ProductDAO getInstance() {
		if( dao == null ) {
			dao = new ProductDAO();
		}
		return dao;
	}
	
	// 상품 삭제시 자식 테이블에 연결되어있는 레코드도 삭제시키는 트리거
//	create or replace trigger users_delete_trigger
//	before delete on users for each row
//	begin
//	delete from review where uno = :OLD.uno;
//	delete from wishlist where uno = :OLD.uno;
//	delete from qna where uno = :OLD.uno;
//	update reservation set uno = 0 where uno=:OLD.uno;
//	end ;
//	/
	
	// 검색은 상품명으로 하지만 상품번호를 받아와서 삭제 -- 트리거 설정
	public int deleteProduct(int pno) {
		int re = -1;
		String sql  = "delete product where pno = "+pno; 
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pno);
			re = pstmt.executeUpdate();
			ConnectionProvider.close(conn, pstmt);
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return re;
	}
	
	// 리뷰를 전체 조회해서 평균평점 계산
//	public int avgRating() {
//		 
//	}
	
	// 상품번호에 따른 연락처, 최대 인원수, 가격 변경
	public int updateProduct(ProductVO p) {
		int re = -1;
		String sql = "update Product set p_phone = ?, occ_max = ?, price = ? where pno = ?";
		try {		
			Connection conn =ConnectionProvider.getConnection();	
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getP_phone());
			pstmt.setInt(2, p.getOcc_max());
			pstmt.setInt(3, p.getPrice());
			pstmt.setInt(4, p.getPno());
			
			re = pstmt.executeUpdate();
			ConnectionProvider.close(conn, pstmt);		
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}		
		return re;
	}
	
	// 상품등록
	public int insertProduct(ProductVO p) {
		String sql = "insert into Product values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int re = -1;
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, p.getPno());
			pstmt.setString(2, p.getP_phone());
			pstmt.setString(3, p.getP_name());
			pstmt.setString(4, p.getAddr());
			pstmt.setInt(5, p.getOcc_max());
			pstmt.setInt(6, p.getPrice());
			pstmt.setInt(7, p.getRating());
			pstmt.setDouble(8, p.getLat());
			pstmt.setDouble(9, p.getLng());
			pstmt.setInt(10, p.getA_code());
			pstmt.setInt(11, p.getP_code());
			
			re = pstmt.executeUpdate();
			ConnectionProvider.close(conn, pstmt);
		} catch (Exception e) {
			System.out.println("예외발생 : " + e.getMessage());
		}
		return re;
	}
	
	// 상품 전체 조회
	public ArrayList<HashMap<String, Object>> listProduct(){
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "select i.img1, p.p_name, p.price, p.rating"
				+ "from product p, image i"
				+ "where p.pno = i.pno";
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("img", rs.getString(1));
				map.put("p_name", rs.getString(2));
				map.put("price", rs.getInt(3));
				map.put("rating", rs.getDouble(4));
			}
			ConnectionProvider.close(conn, stmt, rs);
		} catch (Exception e) {
			System.out.println("예외발생 : " + e.getMessage());
		}
		return list;
	}
	
	// 상세페이지 조회 조건 추가시 매개변수,sql 수정 -- 미완성
	public ArrayList<ProductVO> detailProduct(){
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		String sql = "select * from product";
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				list.add(new ProductVO( rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5),
						rs.getInt(6),
						rs.getInt(7),
						rs.getDouble(8),
						rs.getDouble(9),
						rs.getInt(10),
						rs.getInt(11)	
						));
			}
			ConnectionProvider.close(conn, stmt, rs);
		} catch (Exception e) {
			System.out.println("예외발생 : " + e.getMessage());
		}
		return list;
	}
	
	// 카드 view 내용에 따라 매개변수, sql 수정 - 미완성
	public ArrayList<ProductVO> listCard(){
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		String sql = "select * from product";
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				list.add(new ProductVO( rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5),
						rs.getInt(6),
						rs.getInt(7),
						rs.getDouble(8),
						rs.getDouble(9),
						rs.getInt(10),
						rs.getInt(11)	
						));
			}
			ConnectionProvider.close(conn, stmt, rs);
		} catch (Exception e) {
			System.out.println("예외발생 : " + e.getMessage());
		}
		return list;
	}
	
	// 상품 등록시 번호 부여
	public int getNextNo() {
		int no = 0;
		String sql  = "select nvl(max(no),0) + 1 from Product";
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				no = rs.getInt(1);
			}
			ConnectionProvider.close(conn, stmt, rs);
		} catch (Exception e) {
			System.out.println("예외발생 : " + e.getMessage());
		}
		return no;
	}
}
