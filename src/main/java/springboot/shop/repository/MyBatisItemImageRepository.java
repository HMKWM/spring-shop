package springboot.shop.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springboot.shop.domain.ItemImage;
import springboot.shop.repository.mapper.ItemImageMapper;

@Repository
@RequiredArgsConstructor
public class MyBatisItemImageRepository implements ItemImageRepository{

    private final ItemImageMapper itemImageMapper;

    @Override
    public ItemImage save(ItemImage itemImage) {
        itemImageMapper.save(itemImage);
        return itemImage;
    }

    @Override
    public void update(ItemImage itemImage) {
        itemImageMapper.update(itemImage);
    }

    @Override
    public void delete(Long id) {
        itemImageMapper.delete(id);
    }
}
