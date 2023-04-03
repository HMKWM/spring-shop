package springboot.shop.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class Item {
    private Long itemId;
    private String name;
    private Integer price;
    private Integer quantity;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name) && Objects.equals(price, item.price) && Objects.equals(quantity, item.quantity) && Objects.equals(content, item.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, quantity, content);
    }
}
