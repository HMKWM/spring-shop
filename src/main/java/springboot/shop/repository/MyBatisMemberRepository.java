package springboot.shop.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springboot.shop.domain.Member;
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
    public Member findById(String id) {
        return memberMapper.findById(id);
    }

    @Override
    public List<Member> findAll() {
        return memberMapper.findAll();
    }

    @Override
    public void update(Member member) {
        memberMapper.update(member);
    }

    @Override
    public void delete(String id) {
        memberMapper.delete(id);
    }
}
