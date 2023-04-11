package springboot.shop.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartItem {
    private Long cartItemId;
    private Long memberId;
    private Long itemId;
    private Integer count;
}
