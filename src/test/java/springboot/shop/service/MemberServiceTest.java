package springboot.shop.service;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import springboot.shop.domain.Member;
import springboot.shop.domain.Role;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void passwordEncoder() {
        String password = "testPassword";
        String encoded = passwordEncoder.encode(password);

        Assertions.assertThat(password).isNotEqualTo(encoded);
    }

    @Test
    @Transactional
    void saveMember(){
        Member member = new Member();
        member.setId("test1");
        member.setPassword("password");
        member.setName("testName");
        member.setEmail("test@test.com");
        member.setAddress("testAddress");
        member.setRole(Role.USER);

        //성공 상황
        Member saveMember = memberService.save(member);
        Assertions.assertThat(member).isEqualTo(saveMember);

        //실패 상황
        Assertions.assertThatThrownBy(()->{
            memberService.save(member);
        }).isInstanceOf(IllegalStateException.class);
    }
}