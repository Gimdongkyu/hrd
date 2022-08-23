package hrd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hrd.vo.MemberVO;
import hrd.vo.SaleVO;
import jdbc.util.OracleConnectionUtil;

public class HrdDao {
	private static HrdDao dao = new HrdDao();
	private HrdDao() { }
	public static HrdDao getInstance() {
		return dao;
	}
	
	//1)
	public int getNextSeq() {
		String sql ="select custno_seq.nextval from dual";
		String sql2 ="select max(custno)+1 from member_tbl_02";		//메모장 설명 19번 참고
		int seq =0;
		try (Connection conn = OracleConnectionUtil.connect();
				PreparedStatement pstmt=conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery())
		{
			if(rs.next())
				seq = rs.getInt(1);
			
		}catch(SQLException e) {
			System.out.println("Next SEQ에 문제가 있습니다. : " + e.getMessage());
		}
		return seq;
	}
	
	//2)
	public int registMember(MemberVO vo) {
		
		String sql ="insert into member_tbl_02 values "
				+ "	(?, ?, ?, ?, to_date(?,'yyyymmdd'), ?, ?)";
		int cnt =0;
		try (Connection conn = OracleConnectionUtil.connect();
			PreparedStatement pstmt=conn.prepareStatement(sql))
		{
			pstmt.setInt(1, vo.getCustno());
			pstmt.setString(2, vo.getCustname());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4, vo.getAddress());
			pstmt.setString(5, vo.getSjoindate());
			pstmt.setString(6, vo.getGrade());
			pstmt.setString(7, vo.getCity());
			cnt = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println("member regist 에 문제가 있습니다. : " + e.getMessage());
		}
		return cnt;
		
		
		
	}
	
	//3)
	public MemberVO getMember(int custno) {
		
		String sql = "select * from member_tbl_02 where custno=?";
				
		MemberVO vo=null;
		try(Connection conn = OracleConnectionUtil.connect();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		) 
		{
			ResultSet rs=null;
			pstmt.setInt(1, custno);
			rs = pstmt.executeQuery();
			if (rs.next()) {		
				vo = new MemberVO(rs.getInt(1),rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getDate(5),null,rs.getString(6),rs.getString(7));
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Member One 조회에 문제가 있습니다. : " + e.getMessage());
		} 

		return vo;		
	}
	
	//4)
	public int updateMember(MemberVO vo) {
		String sql ="update member_tbl_02 set "
				+ "	phone=? , address=?, grade=?,city=? where custno=?";
		int cnt =0;
		try (Connection conn = OracleConnectionUtil.connect();
			PreparedStatement pstmt=conn.prepareStatement(sql))
		{
			pstmt.setString(1, vo.getPhone());
			pstmt.setString(2, vo.getAddress());
			pstmt.setString(3, vo.getGrade());
			pstmt.setString(4, vo.getCity());
			pstmt.setInt(5, vo.getCustno());
			cnt = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println("member update 에 문제가 있습니다. : " + e.getMessage());
		}
		return cnt;
	}
	
	
	
	public List<MemberVO> getMembers() {
		Connection conn = OracleConnectionUtil.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select custno,"
				+ "custname,phone,address,joindate,"
				+ "decode(grade,'A','우수','B','일반','C','직원') AS agrade,"
				+ "city from member_tbl_02 order by custno";
				
		MemberVO vo;
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {		
				vo = new MemberVO(rs.getInt(1),rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getDate(5),null,rs.getString(6),rs.getString(7));
				list.add(vo); // 리스트에 추가합니다.
			}
			
		} catch (Exception e) {
			System.out.println("Member List 조회에 문제가 있습니다. : " + e.getMessage());
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("close 오류 : " + e.getMessage());
			}
			OracleConnectionUtil.close(conn,pstmt);
		}

		return list;		
		
	}
	
	public List<SaleVO> getSales() {
		Connection conn = OracleConnectionUtil.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT  mt.CUSTNO , \r\n" + 
				"	mt.CUSTNAME , \r\n" + 
				"	decode(mt.GRADE,'A','우수','B','일반','C','직원') AS agrade, \r\n" + 
				"	sale.asum total " + 
				"FROM MEMBER_TBL_02 mt ,\r\n" + 
				"		(SELECT CUSTNO, sum(price) AS asum FROM MONEY_TBL_02  \r\n" + 
				"		GROUP BY CUSTNO\r\n" + 
				"		ORDER BY asum desc) sale\r\n" + 
				"WHERE mt.CUSTNO = sale.CUSTNO order by total desc"; 

		//메모장 설명 17번 참고
		String sql2 = "select met.custno, custname,\r\n"
				+ "	decode(met.grade, 'A', 'VIP', 'B', '일반', 'C', '직원') as grade,\r\n"
				+ "	nvl(sale.asum,0) total	\r\n"
				+ "	from member_tbl_02 met LEFT OUTER join\r\n"
				+ "	(select custno, sum(price) asum from money_tbl_02 mot \r\n"
				+ "	group by custno\r\n"
				+ "	order by asum desc) sale\r\n"
				+ "	on met.custno = sale.custno \r\n"
				+ "ORDER BY total DESC ,custno";
		
		
		
		SaleVO vo;
		List<SaleVO> list = new ArrayList<SaleVO>();
		try {
			pstmt = conn.prepareStatement(sql2);

			rs = pstmt.executeQuery();
			while (rs.next()) {		//첫번째 결과행부터 순서대로 다음행 이동 , 다음행이 있으면
				vo = new SaleVO(rs.getInt(1),
						rs.getString(2), 
						rs.getString(3), 
						rs.getInt(4));
				list.add(vo); // 리스트에 추가합니다.
			}
			return list;
		} catch (SQLException e) {
			System.out.println("회원매출조회에  문제가 있습니다. : " + e.getMessage());
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("close 오류 : " + e.getMessage());
			}
			OracleConnectionUtil.close(conn,pstmt);
		}

		return null;	
	}
}
