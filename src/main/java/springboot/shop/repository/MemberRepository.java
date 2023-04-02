package springboot.shop.repository;

import springboot.shop.domain.Member;
import springboot.shop.domain.PageHandler;
import springboot.shop.domain.SearchCond;

import java.util.List;

public interface MemberRepository {

    Member save(Member member);

    Member findById(Long id);

    Member findByEmail(String email);

    List<Member> findAll(PageHandler ph);

    void update(Member member);

    void delete(Long id);

    int count();
}
