package springboot.shop.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.shop.domain.Order;

import java.util.List;

@Mapper
public interface OrderMapper {

    void save(Order order);
    List<Order> findByMemberId(Long memberId);
}
