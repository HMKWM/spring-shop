package springboot.shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class ItemForm {
    @NotBlank
    private String name;
    @NotBlank
    private Integer price;
    @NotBlank
    private Integer quantity;
    @NotBlank
    private String content;
}
