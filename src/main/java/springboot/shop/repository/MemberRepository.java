package springboot.shop.repository;

import springboot.shop.domain.Member;
import springboot.shop.domain.PageHandlerVO;
import springboot.shop.domain.SearchCond;

import java.util.List;

public interface MemberRepository {

    Member save(Member member);

    Member findById(Long id);

    Member findByEmail(String email);

    List<Member> findAll(SearchCond cond);

    void update(Member member);

    void delete(Long id);

    int count();
}
