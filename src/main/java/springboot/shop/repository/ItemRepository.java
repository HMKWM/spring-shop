package springboot.shop.repository;

import springboot.shop.domain.Item;
import springboot.shop.domain.PageHandlerVO;

import java.util.List;

public interface ItemRepository {
    Item save(Item item);

    Item findById(Long id);

    List<Item> findAll(PageHandlerVO ph);

    void update(Item item);

    void delete(Long id);

    int count(String keyword);
}
