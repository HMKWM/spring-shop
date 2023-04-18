package springboot.shop.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemView {
    private Long cartItemId;
    private Long itemId;
    private String itemName;
    private int price;
    private int count;
    private String imgName;
}
