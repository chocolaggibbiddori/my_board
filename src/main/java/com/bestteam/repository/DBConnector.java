package com.bestteam.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DBConnector {
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/my_board?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
	private String id_mysql = "root";
	private String pw_mysql = "tkfkdgo486";

	protected Connection conn = null;

	protected void connectMySQL() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id_mysql, pw_mysql);
			System.out.println("MySQL 드라이버 로드 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("Class.forName(drive) ERR: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("getConnection() ERR:" + e.getMessage());
		}
	}

	protected void close(Statement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("Close ResultSet Error-> " + e.getMessage());
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.out.println("Close Statement Error->" + e.getMessage());
			}
		}
	}
}
