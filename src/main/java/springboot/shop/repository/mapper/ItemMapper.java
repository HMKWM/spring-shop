package springboot.shop.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.shop.domain.Item;
import springboot.shop.domain.Member;
import springboot.shop.domain.PageHandler;

import java.util.List;

@Mapper
public interface ItemMapper {

    void save(Item item);

    Item findById(Long id);

    List<Item> findAll(PageHandler ph);

    void update(Item item);

    void delete(Long id);

    int count(String keyword);
}
