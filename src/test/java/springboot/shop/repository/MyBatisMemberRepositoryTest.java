package springboot.shop.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import springboot.shop.domain.*;
import springboot.shop.repository.mapper.CartItemMapper;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MyBatisMemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    CartItemMapper cartMapper;

    Member member = new Member();

    @BeforeEach
    void before(){
        member.setEmail("test1");
        member.setPassword("testPassword");
        member.setAddress("testAddress");
        member.setName("testName");
        member.setRole(Role.USER);
    }

    @Test
    @Transactional
    void saveAndFind() {
        memberRepository.save(member);
        Member findMember = memberRepository.findById(member.getMemberId());
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    @Transactional
    void update() {
        memberRepository.save(member);
        member.setName("test update");
        memberRepository.update(member);
        Member updateMember = memberRepository.findById(member.getMemberId());
        assertThat(updateMember).isEqualTo(member);
    }

    @Test
    @Transactional
    void delete() {
        memberRepository.save(member);
        memberRepository.delete(member.getMemberId());
        Member deleteMember = memberRepository.findById(member.getMemberId());
        assertThat(deleteMember).isNull();
    }

    @Test
    void test(){
    }
}