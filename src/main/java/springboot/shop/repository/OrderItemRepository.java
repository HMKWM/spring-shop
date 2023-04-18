package springboot.shop.repository;

import springboot.shop.domain.OrderItem;

import java.util.List;

public interface OrderItemRepository {

    void save(Long orderId, List<OrderItem> orderItemList);
}
