package springboot.shop.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.shop.domain.CartItem;
import springboot.shop.domain.CartItemList;
import springboot.shop.domain.Item;
import springboot.shop.domain.PageHandler;

import java.util.List;

@Mapper
public interface CartItemMapper {

    void save(CartItem cartItem);

    CartItemList findById(Long id);

    List<Long> findByMemberId(Long memberId);

    List<CartItemList> findAll(Long memberId);

    void update(CartItem cartItem);

    void delete(List<Long> cartItemIdList);
}
