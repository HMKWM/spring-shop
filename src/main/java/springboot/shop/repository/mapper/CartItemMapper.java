package springboot.shop.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.shop.domain.CartItem;
import springboot.shop.domain.CartItemView;

import java.util.List;

@Mapper
public interface CartItemMapper {

    void save(CartItem cartItem);

    CartItemView findById(Long id);

    List<Long> findByMemberId(Long memberId);

    List<CartItemView> findAll(Long memberId);

    void update(CartItem cartItem);

    void delete(List<Long> cartItemIdList);
}
