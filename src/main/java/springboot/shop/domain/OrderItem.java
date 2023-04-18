package springboot.shop.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem {
    private Long orderItemId;
    private Long orderId;
    private Long itemId;
    private Integer count;

    private Item item;
}
