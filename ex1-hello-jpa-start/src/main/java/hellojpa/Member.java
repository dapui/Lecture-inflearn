package hellojpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

// SEQUENCE 전략
//@Entity
//@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq", initialValue = 1, allocationSize = 1)
//public class Member {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
//    private Long id;
//
//    @Column(name = "name", nullable = false)
//    private String username;
//
//    public Member() {}
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//}


// TABLE 전략
//@Entity
//@TableGenerator(name = "member_seq_generator", table = "my_sequences", pkColumnValue = "member_seq", allocationSize = 1)
//public class Member {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "member_seq_generator")
//    private Long id;
//
//    @Column(name = "name", nullable = false)
//    private String username;
//
//    public Member() {}
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//}


@Entity
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

//    @ManyToOne(fetch = FetchType.LAZY)  // 지연로딩 : 사용시 프록시 객체로 조회한다
    @ManyToOne(fetch = FetchType.EAGER)  // 즉시로딩 : Member와 Team을 Join 하여 한번에 조회한다
    @JoinColumn
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}