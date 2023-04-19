package springboot.shop.repository;

import springboot.shop.domain.Order;
import springboot.shop.domain.OrderStatus;

import java.util.List;

public interface OrderRepository {

    Order save(Order order);
    List<Order> findByMemberId(Long memberId);

    List<Order> findAll();

    void updateStatus(Long orderId, OrderStatus status);

    Long findMemberIdByOrderId(Long orderId);

    void delete(Long orderId);

}
