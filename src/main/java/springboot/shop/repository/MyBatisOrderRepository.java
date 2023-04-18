package springboot.shop.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springboot.shop.domain.Order;
import springboot.shop.repository.mapper.OrderMapper;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class MyBatisOrderRepository implements OrderRepository{

    private final OrderMapper orderMapper;

    @Override
    public Order save(Order order) {
        orderMapper.save(order);
        return order;
    }

    @Override
    public List<Order> findByMemberId(Long memberId) {
        return orderMapper.findByMemberId(memberId);
    }
}
