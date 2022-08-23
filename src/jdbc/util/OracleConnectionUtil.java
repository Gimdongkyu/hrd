package jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OracleConnectionUtil {
//웹애플리케이션에 ojdbc11.jar 파일은 WEB-INF 폴더 아래 lib 폴더로 복사해서 라이브러리 사용합니다.
//		          ==> java 11버전이상이어야 합니다. 
	public static Connection connect() {  
		Connection conn = null;

		String url = "jdbc:oracle:thin:@localhost:1521:XE";  
		String driver="oracle.jdbc.driver.OracleDriver";     
		String user ="c##idev";				
		String password="1234";				
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,password);    
		} catch (ClassNotFoundException e) {
			System.out.println("db연결오류 : " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("SQL오류 : " + e.getMessage());
		} 
		return conn;   
	}

	public static void close(Connection conn,PreparedStatement pstmt) {
			try {
				if(conn !=null) conn.close();
				if(pstmt !=null) pstmt.close();
			} catch (SQLException e) {
				System.out.println("db close 실패: " + e.getMessage());
			}
	}
	
}
