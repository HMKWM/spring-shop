package springboot.shop.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springboot.shop.domain.Order;
import springboot.shop.domain.OrderStatus;
import springboot.shop.repository.mapper.OrderMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<Order> findAll() {
        return orderMapper.findAll();
    }

    @Override
    public void updateStatus(Long orderId, OrderStatus status) {
        Map map = new HashMap();
        map.put("orderId", orderId);
        map.put("status", status);
        orderMapper.updateStatus(map);
    }

    @Override
    public Long findMemberIdByOrderId(Long orderId) {
        return orderMapper.findMemberIdByOrderId(orderId);
    }

    @Override
    public void delete(Long orderId) {
        orderMapper.delete(orderId);
    }
}
