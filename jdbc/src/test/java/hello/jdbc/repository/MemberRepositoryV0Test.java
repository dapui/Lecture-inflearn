package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class MemberRepositoryV0Test {

    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException {

        // save
        Member member = new Member("memberV10", 10000);
        repository.save(member);

        // findById
        Member findMember = repository.findById(member.getMember_id());
        log.info("findMember = {}", findMember);
        assertThat(findMember).isEqualTo(member);

        // update : money 10000 -> 20000
        repository.update(member.getMember_id(), 20000);
        Member updatedMember = repository.findById(member.getMember_id());
        assertThat(updatedMember.getMoney()).isEqualTo(20000);

        // delete
        repository.delete(member.getMember_id());
        Assertions.assertThatThrownBy(() -> repository.findById(member.getMember_id())).isInstanceOf(NoSuchElementException.class);

    }
}
