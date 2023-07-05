package hello.jdbc.domain;

import lombok.Data;

@Data
public class Member {

    private String member_id;
    private int money;

    public Member() { }

    public Member(String member_id, int money) {
        this.member_id = member_id;
        this.money = money;
    }
}
