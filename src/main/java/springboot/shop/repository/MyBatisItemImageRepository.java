package springboot.shop.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springboot.shop.domain.ItemImage;
import springboot.shop.repository.mapper.ItemImageMapper;

import java.util.HashMap;
import java.util.Map;

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
    public void disconnectItemImage(Long itemId, Integer order) {
        Map map = new HashMap();
        map.put("itemId", itemId);
        map.put("order", order);
        itemImageMapper.disconnectItemImage(map);
    }

    @Override
    public void delete(Long id) {
        itemImageMapper.delete(id);
    }
}
