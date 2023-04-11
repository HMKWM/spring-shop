package springboot.shop.repository;

import springboot.shop.domain.CartItem;
import springboot.shop.domain.CartItemList;
import springboot.shop.domain.PageHandler;

import java.util.List;

public interface CartItemRepository {
    CartItem save(CartItem cartItem);

    CartItemList findById(Long id);

    List<CartItemList> findAll(Long memberId);

    void update(CartItem cartItem);

    void delete(Long id);
}
