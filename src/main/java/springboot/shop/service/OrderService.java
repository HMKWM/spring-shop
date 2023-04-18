package springboot.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.shop.domain.Order;
import springboot.shop.domain.OrderItem;
import springboot.shop.domain.OrderStatus;
import springboot.shop.repository.CartItemRepository;
import springboot.shop.repository.OrderItemRepository;
import springboot.shop.repository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public void saveOrder(Long memberId, List<OrderItem> orderItemList){
        Order order = new Order();
        order.setMemberId(memberId);
        order.setStatus(OrderStatus.ORDER);
        orderRepository.save(order);
        orderItemRepository.save(order.getOrderId(), orderItemList);
    }

    public List<Order> getOrderList(Long memberId){
        return orderRepository.findByMemberId(memberId);
    }

}
