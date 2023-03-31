package springboot.shop.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.shop.domain.Member;

import java.util.List;

@Mapper
public interface MemberMapper {

    void save(Member member);

    Member findById(String id);

    List<Member> findAll();

    void update(Member member);

    void delete(String id);
}
