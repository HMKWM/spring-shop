package springboot.shop.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.shop.domain.CartItem;
import springboot.shop.domain.Item;
import springboot.shop.domain.PageHandler;

import java.util.List;

@Mapper
public interface CartItemMapper {

    void save(CartItem cartItem);

    CartItem findById(Long id);

    List<CartItem> findAll(PageHandler ph);

    void update(CartItem cartItem);

    void delete(Long id);
}
