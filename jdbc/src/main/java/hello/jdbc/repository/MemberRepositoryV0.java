package hello.jdbc.repository;

import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.NoSuchElementException;

/*
 * JDBC - DriverManager 사용
 */
@Slf4j
public class MemberRepositoryV0 {

    public Member save(Member member) throws SQLException {
        String sql = "INSERT INTO member(member_id, money) VALUES(?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        // PreparedStatement 는 Statement 의 자식타입인데, '?'를 통한 파라미터 바인딩을 가능하게 해준다.
        // SQL Injection 공격을 예방하려면, PreparedStatement 를 통한 파라미터 바인딩 방식을 사용해야한다.

        try {
            conn = getConnection(); // DBConnectionUtil 을 통해서 데이터베이스 커넥션을 획득 
            pstmt = conn.prepareStatement(sql); // 데이터베이스에 전달할 SQL과 파라미터로 전달할 데이터들을 준비
            pstmt.setString(1, member.getMember_id());
            pstmt.setInt(2, member.getMoney());

            pstmt.executeUpdate();  // 위에서 준비되었던 쿼리가 실제 데이터베이스에서 실행 (데이터를 변경할 때는 executeUpdate 사용)

            return member;
        } catch (SQLException e) {
            log.error("DB Error", e);
            throw e;
        } finally {
            close(conn, pstmt, null);   // 리소스 정리
        }

    }

    public Member findById(String memberId) throws SQLException {
        String sql = "select * from member where member_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                Member member = new Member();
                member.setMember_id(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));

                return member;
            } else {
                throw new NoSuchElementException("member not found memberId=" + memberId);
            }
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(conn, pstmt, rs);
        }
    }

    public void update(String memberId, int money) throws SQLException {
        String sql = "UPDATE member SET money = ? WHERE member_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, money);
            pstmt.setString(2, memberId);

            int resultSize = pstmt.executeUpdate();
            log.info("resultSize = {}" , resultSize);
        } catch (SQLException e) {
            log.error("DB Error", e);
            throw e;
        } finally {
            close(conn, pstmt, null);
        }
    }

    public void delete(String memberId) throws SQLException {
        String sql = "DELETE FROM member WHERE member_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error("DB Error", e);
            throw e;
        } finally {
            close(conn, pstmt, null);
        }
    }

    // 리소스를 정리할 때는 항상 역순으로 진행 (안하면, 리소스 누수 발생 -> 커넥션 부족으로 장애가 발생할 수 있다)
    private void close(Connection conn, Statement stmt, ResultSet rs) {

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                log.info("Error", e);
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                log.info("Error", e);
            }
        }
        
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                log.info("Error", e);
            }
        }
        
    }

    private Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }
}
