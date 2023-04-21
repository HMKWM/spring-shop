package springboot.shop.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.shop.domain.ItemImage;

import java.util.Map;

@Mapper
public interface ItemImageMapper {
    void save(ItemImage itemImage);

    void update(ItemImage itemImage);

    void disconnectItemImage(Map map);

    void delete(Long id);


}
