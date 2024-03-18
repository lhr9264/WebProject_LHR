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
	
	public MemberDAO(ServletContext application) {
		super();
	}
	
	
	
	// 아이디 중복 처리
	public boolean checkDuplicateId(String id) {
	    String query = "SELECT COUNT(*) FROM member WHERE id=?";
	    
	    try (PreparedStatement psmt = con.prepareStatement(query)) {
	        psmt.setString(1, id);
	        ResultSet rs = psmt.executeQuery();
	        if (rs.next()) {
	            int count = rs.getInt(1);
	            return count > 0; 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return false; 
	}

	//계정 추가
    public boolean addMember(MemberDTO member) {
        if (checkDuplicateId(member.getId())) {
            return false;
        }
        
        String query = "INSERT INTO member (id, name, pass, regidate) VALUES (?, ?, ?, SYSDATE)";
        
        try (PreparedStatement psmt = con.prepareStatement(query)) {
            psmt.setString(1, member.getId());
            psmt.setString(2, member.getName());
            psmt.setString(3, member.getPass());
            
            int result = psmt.executeUpdate();
            return result > 0; 
        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
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
                dto = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dto;
    }
    
    public boolean updateMember(String newId, String newPassword, String id) {
        String query = "UPDATE member SET id=?, pass=? WHERE id=?";
        
        try (PreparedStatement psmt = con.prepareStatement(query)) {
            psmt.setString(1, newId); 
            psmt.setString(2, newPassword);
            psmt.setString(3, id);   
            
            int result = psmt.executeUpdate();
            return result > 0; 
        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
        }
    }

    
}