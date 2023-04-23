package springboot.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.shop.domain.Order;
import springboot.shop.domain.OrderItem;
import springboot.shop.domain.OrderStatus;
import springboot.shop.repository.OrderItemRepository;
import springboot.shop.repository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public void addOrder(Long memberId, List<OrderItem> orderItemList){
        Order order = new Order();
        order.setMemberId(memberId);
        order.setStatus(OrderStatus.ORDER);
        orderRepository.save(order);

        orderItemRepository.save(order.getOrderId(), orderItemList);
    }

    public Order getOrder(Long orderId){
        return orderRepository.findById(orderId);
    }

    public List<Order> getOrderList(Long memberId){
        return orderRepository.findByMemberId(memberId);
    }

    public List<Order> getAllOrderList(){
        return orderRepository.findAll();
    }

    public void changeOrderStatus(Long orderId, OrderStatus status){
        orderRepository.updateStatus(orderId, status);
    }

    public Long getOrderOwner(Long orderId){
        return orderRepository.findMemberIdByOrderId(orderId);
    }

    public void deleteOrder(Long orderId){
        orderRepository.delete(orderId);
    }
}
