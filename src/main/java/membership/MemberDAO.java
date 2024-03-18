package membership;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.DBConnPool;
import jakarta.servlet.ServletContext;

public class MemberDAO extends DBConnPool{
	
	public MemberDAO() {
		super();
	}
	
	//application 내장객체를 인수로 전달한 후 DB 연결
	public MemberDAO(ServletContext application) {
		super();
	}
	
	
	
	// 아이디 중복 체크
	public boolean checkDuplicateId(String id) {
	    String query = "SELECT COUNT(*) FROM member WHERE id=?";
	    
	    try (PreparedStatement psmt = con.prepareStatement(query)) {
	        psmt.setString(1, id);
	        ResultSet rs = psmt.executeQuery();
	        if (rs.next()) {
	            int count = rs.getInt(1);
	            return count > 0; // 아이디가 존재하면 true, 아니면 false 반환
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return false; // 오류 발생 시 기본적으로 중복 아이디가 아닌 것으로 처리
	}

    public boolean addMember(MemberDTO member) {
        // 중복 아이디 체크
        if (checkDuplicateId(member.getId())) {
            // 이미 존재하는 아이디라면 회원 추가를 하지 않고 false 반환
            return false;
        }
        
        // 회원 추가 쿼리
        String query = "INSERT INTO member (id, name, pass, regidate) VALUES (?, ?, ?, SYSDATE)";
        
        try (PreparedStatement psmt = con.prepareStatement(query)) {
            psmt.setString(1, member.getId());
            psmt.setString(2, member.getName());
            psmt.setString(3, member.getPass());
            
            int result = psmt.executeUpdate();
            return result > 0; // 회원 추가 성공 여부 반환
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // 오류 발생 시 회원 추가 실패로 처리
        }
    }
    
    public MemberDTO getMemberDTO(String id, String pass) {
        MemberDTO dto = new MemberDTO();
        String query = "SELECT * FROM member WHERE id=? AND pass=?";
        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, id);
            psmt.setString(2, pass);
            rs = psmt.executeQuery();
            if (rs.next()) {
                dto.setId(rs.getString("id"));
                dto.setName(rs.getString("name"));
                dto.setPass(rs.getString("pass"));
                dto.setRegidate(rs.getString("regidate"));
            } else {
                dto = null; // 회원 정보가 없는 경우 null로 설정
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dto;
    }
    
    public boolean updateMember(String newId, String newPassword, String id) {
        String query = "UPDATE member SET id=?, pass=? WHERE id=?";
        
        try (PreparedStatement psmt = con.prepareStatement(query)) {
            psmt.setString(1, newId); // 수정된 아이디를 바인딩
            psmt.setString(2, newPassword); // 수정된 비밀번호를 바인딩
            psmt.setString(3, id);   // 기존 아이디를 바인딩
            
            int result = psmt.executeUpdate();
            return result > 0; // 업데이트 성공 여부 반환
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // 오류 발생 시 업데이트 실패로 처리
        }
    }

    
}