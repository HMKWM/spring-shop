package springboot.shop.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Order {
    private Long orderId;
    private Long memberId;
    private LocalDateTime orderDate;
    private OrderStatus status;

    private Member member;
    private List<OrderItem> orderItemList;
}
