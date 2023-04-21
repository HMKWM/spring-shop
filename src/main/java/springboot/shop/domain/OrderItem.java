package springboot.shop.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem {
    private Long orderItemId;
    private Long orderId;
    private Long itemId;
    private Long itemImgId;
    private String orderItemName;
    private Integer count;
    private Integer orderPrice;

    private ItemImage itemImage;
//    private Item item;
}
