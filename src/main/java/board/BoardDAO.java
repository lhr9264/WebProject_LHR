package board;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;
import board.BoardDTO;

public class BoardDAO extends DBConnPool {

	//기본생성자 호출로 커넥션풀을 사용한다.
		public BoardDAO() {
			super();
		}
		
		//게시물의 갯수 카운트
		public int selectCount(Map<String, Object> map) {
			int totalCount = 0;
			String query = "SELECT COUNT(*) FROM board";
			if (map.get("searchWord") != null) {
				query += " WHERE " + map.get("searchField") + " "
					   + " LIKE '%" + map.get("searchWord") + "%'";
			} 
			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				rs.next();
				totalCount = rs.getInt(1);
			} 
			catch (Exception e) {
				System.out.println("게시물 수를 구하는 중 예외 발생");
				e.printStackTrace();
			}
			return totalCount;
		}
		public List<BoardDTO> selectListPage(Map<String, Object> map) {
			
			List<BoardDTO> board = new Vector<BoardDTO>();
			String query = " "
						 + "SELECT * FROM ( "
						 + "	SELECT Tb.*, ROWNUM rNum FROM ( "
						 + "		SELECT * FROM board ";
			if (map.get("searchWord") != null) 
			{
				query += " WHERE " + map.get("searchField")
					   + " LIKE '%" + map.get("searchWord") + "%' "; 
			}
			query += " 		  ORDER BY idx DESC "
				   + "	  ) Tb "
				   + " ) "
				   + " WHERE rNum BETWEEN ? AND ?";
			
			try {
				psmt = con.prepareStatement(query);
				psmt.setString(1, map.get("start").toString());
				psmt.setString(2, map.get("end").toString());
				rs = psmt.executeQuery();
				
				while(rs.next()) {
					BoardDTO dto = new BoardDTO();
					
					dto.setIdx(rs.getString(1));
					dto.setTitle(rs.getString(2));
					dto.setName(rs.getString(3));
					dto.setContent(rs.getString(4));
					dto.setThumb(rs.getInt(5));
					dto.setVisitcount(rs.getInt(6));
					dto.setPostdate(rs.getDate(7));
	
					board.add(dto);
				}
			} catch (Exception e) {
				System.out.println("게시물 조회 중 예외 발생");
				e.printStackTrace();
			}
			return board;
		}	
		
		//글쓰기 처리
		public int insertWrite(BoardDTO dto) {
			int result = 0;
			try {
				/* 쿼리문의 일련번호는 모델1 게시판에서 생성한 시퀀스를 그대로
				사용한다. 나머지 값들은 컨트롤러(서블릿)에서 받은 후 모델(DAO)로
				전달한다.*/
				String query = "INSERT INTO mvcboard ( "
							+ " idx, title, name, thumb, visitcount, postdate) "
							+ " VALUES ( "
							+ " seq_board_num.NEXTVAL,?,?,?,?,?)";
				psmt = con.prepareStatement(query);
				psmt.setString(1, dto.getTitle());
				psmt.setString(2, dto.getName());
				psmt.setInt(3, dto.getThumb());
				psmt.setInt(4, dto.getVisitcount());
				psmt.setDate(5, dto.getPostdate());
				result = psmt.executeUpdate();
			} 
			catch (Exception e) {
				System.out.println("게시물 입력 중 예외 발생");
				e.printStackTrace();
			}
			return result;
		}
		
		//조회수 증가
		public void updateVisitCount(String idx) {
			String query = "UPDATE board SET "
						+ " visitcount=visitcount + 1 "
						+ " WHERE idx=?";
			try {
				psmt = con.prepareStatement(query);
				psmt.setString(1, idx);
				psmt.executeQuery();
			} 
			catch (Exception e) {
				System.out.println("게시물 조회수 증가중 예외 발생");
				e.printStackTrace();
			}			
		}
}
