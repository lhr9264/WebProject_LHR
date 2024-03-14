package membership;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBConnect;
import jakarta.servlet.ServletContext;

public class MemberDAO extends JDBConnect{
	
	public MemberDAO(String drv, String url, String id, String pw) {
		super(drv, url, id, pw);
	}
	
	//application 내장객체를 인수로 전달한 후 DB 연결
	public MemberDAO(ServletContext application) {
		super(application);
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
        String query = "INSERT INTO member (id, pass, name, regidate) VALUES (?, ?, ?, SYSDATE)";
        
        try (PreparedStatement psmt = con.prepareStatement(query)) {
            psmt.setString(1, member.getId());
            psmt.setString(2, member.getPass());
            psmt.setString(3, member.getName());
            
            int result = psmt.executeUpdate();
            return result > 0; // 회원 추가 성공 여부 반환
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // 오류 발생 시 회원 추가 실패로 처리
        }
    }
    
    //로그인
//    public boolean login(String id, String pass) {
//    	String query = "SELECT COUNT(*) FROM member WHERE id=? AND pass=?";
//    	
//    	try (PreparedStatement psmt = con.prepareStatement(query)) {
//    		psmt.setString(1, id);
//    		psmt.setString(2, pass);
//    		ResultSet rs = psmt.executeQuery();
//    		if (rs.next()) {
//    			int count = rs.getInt(1);
//    			return count > 0;
//    		}
//    	} catch (SQLException e) {
//    		e.printStackTrace();
//    	}
//    	
//    	return false;
//    }
    
    public MemberDTO getMemberDTO(String id, String pass) {
		MemberDTO dto = new MemberDTO();
		/* 로그인 폼에서 입력한 아이디, 패스워드를 통해 인파라미터를 설정할
		수 있또록 쿼리문을 작성 */
		String query = "SELECT * FROM member WHERE id=? AND pass=?";
		
		try {
			//쿼리문 실행과 인파라미터 설정을 위한 prepared 인스턴스 생성
			psmt = con.prepareStatement(query);
			//인파라미터를 설정
			psmt.setString(1, id);
			psmt.setString(2, pass);
			//쿼리문을 실행한 후 결과는 ResultSet을 통해 반환
			rs = psmt.executeQuery();
			//반환된 ResultSet에 회원정보가 저장되어 있는지 확인
			if (rs.next()) {
				//정보가 있다면 DTO에 저장한다.
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString(2));
				dto.setPass(rs.getString("pass"));
				dto.setRegidate(rs.getString(4));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}
}