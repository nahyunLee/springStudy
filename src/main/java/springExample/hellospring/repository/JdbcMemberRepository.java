package springExample.hellospring.repository;

import org.springframework.jdbc.datasource.DataSourceUtils;
import springExample.hellospring.domain.Member;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Optional;
public class JdbcMemberRepository{}
//
//public class JdbcMemberRepository implements MemberRepository {
//
//    private final DataSource dataSource; //DB에 붙기 위해 필요
//
//    public JdbcMemberRepository(DataSource dataSource) {
//        this.dataSource = dataSource;
////        dataSource.getConnection(); //연결을 얻을 수 있음 --> 여기다 sql날리는
//    }
//
//    public Member save(Member member) {
//        String sql = "insert into member(name) values(?)";
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try {
//            conn = getConnection();
//            pstmt = conn.prepareStatement(sql,
//                    Statement.RETURN_GENERATED_KEYS);
//            pstmt.setString(1, member.getName());
//            pstmt.executeUpdate();
//            rs = pstmt.getGeneratedKeys();
//            if (rs.next()) {
//                member.setId(rs.getLong(1));
//            } else {
//                throw new SQLException("id 조회 실패");
//            }
//            return member;
//        } catch (Exception e) {
//            throw new IllegalStateException(e);
//        } finally {
//            close(conn, pstmt, rs);
//        }
//    }
//
//    @Override
//    public Optional<Member> findById(Long id) {
//        String sql = "select * from member where id = ?";
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try {
//            conn = getConnection();
//            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            pstmt.setLong(1, id);
//            rs = pstmt.executeQuery();
//            if (rs.next()) {
//                member.setId(rs.getLong(1));
//            } finally{
//                close(conn, pstmt, rs);
//            }
//        }
//    }
//
//    @Override
//    public List<Member> findAll() {
//        String sql = "select * from member";
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try {
//            conn = getConnection();
//            pstmt = conn.prepareStatement(sql);
//            rs = pstmt.executeQuery();
//            List<Member> members = new ArrayList<>();
//            while (rs.next()) {
//                Member member = new Member();
//                member.setId(rs.getLong("id"));
//                member.setName(rs.getString("name"));
//                members.add
//                name = ?";
//                Connection conn = null;
//                PreparedStatement pstmt = null;
//                ResultSet rs = null;
//                try {
//                    conn = getConnection();
//                    pstmt = conn.prepareStatement(sql);
//                    pstmt.setString(1, name);
//                    rs = pstmt.executeQuery();
//                    if (rs.next()) {
//                        Member member = new Member();
//                        member.setId(rs.getLong("id"));
//                        member.setName(rs.getString("name"));
//                        return Optional.of(member);
//                    }
//                    return Optional.empty();
//                } catch (Exception e) {
//                    throw new IllegalStateException(e);
//                } finally {
//                }
//                try {
//                    if (pstmt != null) {
//                        pstmt.close();
//                    }
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    if (conn != null) {
//                        close(conn);
//                    }
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            private void close (Connection conn) throws SQLException {
//                DataSourceUtils.releaseConnection(conn, dataSource);
//            }
//        }
//    }
//}
// 코드가 잘 안들어가짐 ㅠㅠ