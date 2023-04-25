package springboot.shop.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springboot.shop.domain.Member;
import springboot.shop.domain.PageHandlerVO;
import springboot.shop.domain.SearchCond;
import springboot.shop.repository.mapper.MemberMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisMemberRepository implements MemberRepository{

    private final MemberMapper memberMapper;


    @Override
    public Member save(Member member) {
        memberMapper.save(member);
        return member;
    }

    @Override
    public Member findById(Long id) {
        return memberMapper.findById(id);
    }

    @Override
    public Member findByEmail(String email) {
        return memberMapper.findByEmail(email);
    }

    @Override
    public List<Member> findAll(SearchCond cond) {
        return memberMapper.findAll(cond);
    }

    @Override
    public void update(Member member) {
        memberMapper.update(member);
    }

    @Override
    public void delete(Long id) {
        memberMapper.delete(id);
    }

    @Override
    public int count() {
        return memberMapper.count();
    }
}
