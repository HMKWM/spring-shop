package springboot.shop.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import springboot.shop.domain.Member;
import springboot.shop.domain.Role;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    Member member = new Member();

    @BeforeEach
    void before(){
        member.setId("testId");
        member.setPassword("testPassword");
        member.setEmail("test@test");
        member.setAddress("testAddress");
        member.setName("testName");
        member.setRole(Role.USER);
    }

    @Test
    @Transactional
    void createAndRead() {
        memberRepository.save(member);
        Member findMember = memberRepository.findById(member.getId());
        Assertions.assertThat(findMember).isEqualTo(member);
    }

    @Test
    @Transactional
    void update() {
        memberRepository.save(member);
        member.setName("test update");
        memberRepository.update(member);
        Member updateMember = memberRepository.findById(member.getId());
        Assertions.assertThat(updateMember).isEqualTo(member);
    }

    @Test
    @Transactional
    void delete() {
        memberRepository.save(member);
        memberRepository.delete(member.getId());
        Member deleteMember = memberRepository.findById(member.getId());
        Assertions.assertThat(deleteMember).isNull();
    }

}