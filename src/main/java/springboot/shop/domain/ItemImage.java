package springboot.shop.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ItemImage {
    private Long itemImgId;
    private Long itemId;
    private String imgName;
    private String originImgName;
    private String paths;
    private Integer order;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
