package springboot.shop.repository;

import springboot.shop.domain.Item;
import springboot.shop.domain.Member;
import springboot.shop.domain.PageHandler;

import java.util.List;

public interface ItemRepository {
    Item save(Item item);

    Item findById(Long id);

    List<Item> findAll(PageHandler ph);

    void update(Item item);

    void delete(Long id);

    int count(String keyword);
}
