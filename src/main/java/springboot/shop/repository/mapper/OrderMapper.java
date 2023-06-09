package springboot.shop.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.shop.domain.Order;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {

    void save(Order order);

    Order findById(Long orderId);

    List<Order> findByMemberId(Long memberId);

    List<Order> findAll();

    void updateStatus(Map map);

    Long findMemberIdByOrderId(Long orderId);

    void delete(Long orderId);
}
