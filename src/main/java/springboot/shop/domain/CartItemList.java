package springboot.shop.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemList {
    private Long cartItemId;
    private String itemName;
    private int price;
    private int count;
    private String imgName;
}
