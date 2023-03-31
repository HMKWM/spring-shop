package springboot.shop.repository;

import springboot.shop.domain.Member;

import java.util.List;

public interface MemberRepository {

    Member save(Member member);

    Member findById(String id);

    List<Member> findAll();

    void update(Member member);

    void delete(String id);
}
