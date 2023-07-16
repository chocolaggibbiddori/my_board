package com.bestteam.repository;

import com.bestteam.dto.PostDetailDto;
import com.bestteam.dto.PostListDto;
import com.bestteam.dto.PostModifyDto;
import com.bestteam.dto.PostSaveDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Repository extends DBConnector {

	public Repository() {
		connectMySQL();
	}

	public PostDetailDto findById(int num) {
		PostDetailDto detail = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select * from board where num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				detail = new PostDetailDto(rs.getInt("num"), rs.getString("subject"), rs.getString("writer"),
						rs.getString("contents"), rs.getDate("reg_date").toString(), rs.getInt("hit"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, rs);
		}

		return detail;
	}

	public void save(PostSaveDto newPost) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(
					"insert into board (subject, writer, contents, hit, ip, reg_date, mod_date) values (?, ?, ?, 0, ?, now(), now())");
			pstmt.setString(1, newPost.getSubject());
			pstmt.setString(2, newPost.getWriter());
			pstmt.setString(3, newPost.getContents());
			pstmt.setString(4, newPost.getIp());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, rs);
		}
	}

	public void addHit(int num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("update board set hit = hit + 1 where num = ?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, rs);
		}
	}

	public List<PostListDto> extractTen(int pageNum) {
		List<PostListDto> postList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(
					"select num, subject, writer, reg_date, hit from board order by num desc limit ?, 10");
			pstmt.setInt(1, (pageNum - 1) * 10);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				postList.add(new PostListDto(rs.getInt("num"), rs.getString("subject"), rs.getString("writer"),
						rs.getDate("reg_date").toString(), rs.getInt("hit")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, rs);
		}
		
		return postList;
	}

	public List<PostListDto> extractTenByAll(int pageNum, String searchContent) {
		List<PostListDto> postList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(
					"select num, subject, writer, reg_date, hit from board where subject like concat('%', ?, '%') or writer like concat('%', ?, '%') or contents like concat('%', ?, '%') order by num desc limit ?, 10");
			pstmt.setString(1, searchContent);
			pstmt.setString(2, searchContent);
			pstmt.setString(3, searchContent);
			pstmt.setInt(4, (pageNum - 1) * 10);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				postList.add(new PostListDto(rs.getInt("num"), rs.getString("subject"), rs.getString("writer"),
						rs.getDate("reg_date").toString(), rs.getInt("hit")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, rs);
		}
		
		return postList;
	}

	public List<PostListDto> extractTenBySubject(int pageNum, String searchContent) {
		List<PostListDto> postList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(
					"select num, subject, writer, reg_date, hit from board where subject like concat('%', ?, '%') order by num desc limit ?, 10");
			pstmt.setString(1, searchContent);
			pstmt.setInt(2, (pageNum - 1) * 10);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				postList.add(new PostListDto(rs.getInt("num"), rs.getString("subject"), rs.getString("writer"),
						rs.getDate("reg_date").toString(), rs.getInt("hit")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, rs);
		}

		return postList;
	}

	public List<PostListDto> extractTenByWriter(int pageNum, String searchContent) {
		List<PostListDto> postList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(
					"select num, subject, writer, reg_date, hit from board where writer like concat('%', ?, '%') order by num desc limit ?, 10");
			pstmt.setString(1, searchContent);
			pstmt.setInt(2, (pageNum - 1) * 10);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				postList.add(new PostListDto(rs.getInt("num"), rs.getString("subject"), rs.getString("writer"),
						rs.getDate("reg_date").toString(), rs.getInt("hit")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, rs);
		}

		return postList;
	}

	public List<PostListDto> extractTenByContents(int pageNum, String searchContent) {
		List<PostListDto> postList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(
					"select num, subject, writer, reg_date, hit from board where contents like concat('%', ?, '%') order by num desc limit ?, 10");
			pstmt.setString(1, searchContent);
			pstmt.setInt(2, (pageNum - 1) * 10);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				postList.add(new PostListDto(rs.getInt("num"), rs.getString("subject"), rs.getString("writer"),
						rs.getDate("reg_date").toString(), rs.getInt("hit")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, rs);
		}

		return postList;
	}

	public void modify(PostModifyDto modifyPost) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement("update board set subject = ?, contents = ?, mod_date = now(), hit = hit - 1 where num = ?");
			pstmt.setString(1, modifyPost.getSubject());
			pstmt.setString(2, modifyPost.getContents());
			pstmt.setInt(3, modifyPost.getNum());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, rs);
		}
	}

	public void delete(int num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement("delete from board where num = ?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, rs);
		}
	}

	public int size() {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from board");
			if (rs.next()) {
				return rs.getInt(1) / 10 + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt, rs);
		}

		return 1;
	}

	public int getMaxNum() {
		Statement stmt = null;
		ResultSet rs = null;
		int maxNum = 0;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select max(num) from board");

			if (rs.next()) {
				maxNum = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt, rs);
		}

		return maxNum;
	}
	
	public int size(String condition, String searchContent) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select count(*) where " + condition + " like concat('%', ?, '%') from board");
			pstmt.setString(1, searchContent);

			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return rs.getInt(1) / 10 + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, rs);
		}

		return 1;
	}
}