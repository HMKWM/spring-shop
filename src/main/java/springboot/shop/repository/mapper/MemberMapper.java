package springboot.shop.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.shop.domain.Member;
import springboot.shop.domain.PageHandlerVO;

import java.util.List;

@Mapper
public interface MemberMapper {

    void save(Member member);

    Member findById(Long id);

    Member findByEmail(String email);

    List<Member> findAll(PageHandlerVO ph);

    void update(Member member);

    void delete(Long id);

    int count();
}
