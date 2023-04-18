package springboot.shop.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.shop.domain.ItemImage;

@Mapper
public interface ItemImageMapper {
    void save(ItemImage itemImage);

    void update(ItemImage itemImage);

    void delete(Long id);
}
