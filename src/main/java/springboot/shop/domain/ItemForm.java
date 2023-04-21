package springboot.shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class ItemForm {
    @NotBlank
    private String name;
    @NotNull
    private Integer price;
    @NotNull
    private Integer quantity;
    @NotBlank
    private String content;
}
