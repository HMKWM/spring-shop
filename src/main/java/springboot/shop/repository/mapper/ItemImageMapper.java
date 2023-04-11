package springboot.shop.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.shop.domain.CartItem;
import springboot.shop.domain.CartItemList;
import springboot.shop.domain.ItemImage;
import springboot.shop.domain.PageHandler;

import java.util.List;

@Mapper
public interface ItemImageMapper {
    void save(ItemImage itemImage);

    void update(ItemImage itemImage);

    void delete(Long id);
}
