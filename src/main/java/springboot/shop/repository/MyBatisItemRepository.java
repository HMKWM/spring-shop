package springboot.shop.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springboot.shop.domain.Item;
import springboot.shop.domain.PageHandler;
import springboot.shop.repository.mapper.ItemMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisItemRepository implements ItemRepository{

    private final ItemMapper itemMapper;

    @Override
    public Item save(Item item) {
        itemMapper.save(item);
        return item;
    }

    @Override
    public Item findById(Long id) {
        return itemMapper.findById(id);
    }

    @Override
    public List<Item> findAll(PageHandler ph) {
        return itemMapper.findAll(ph);
    }

    @Override
    public void update(Item item) {
        itemMapper.update(item);
    }

    @Override
    public void delete(Long id) {
        itemMapper.delete(id);
    }

    @Override
    public int count(String keyword) {
        return itemMapper.count(keyword);
    }
}
