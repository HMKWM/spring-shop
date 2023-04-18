package springboot.shop.repository;

import springboot.shop.domain.CartItem;
import springboot.shop.domain.CartItemView;

import java.util.List;

public interface CartItemRepository {
    CartItem save(CartItem cartItem);

    CartItemView findById(Long id);

    List<Long> findByMemberId(Long memberId);

    List<CartItemView> findAll(Long memberId);

    void update(CartItem cartItem);

    void delete(List<Long> cartItemIdList);
}
