package springboot.shop.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springboot.shop.domain.CartItem;
import springboot.shop.domain.CartItemList;
import springboot.shop.domain.PageHandler;
import springboot.shop.repository.mapper.CartItemMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisCartItemRepository implements CartItemRepository{

    private final CartItemMapper cartItemMapper;

    @Override
    public CartItem save(CartItem cartItem) {
        cartItemMapper.save(cartItem);
        return cartItem;
    }

    @Override
    public CartItemList findById(Long id) {
        return cartItemMapper.findById(id);
    }

    @Override
    public List<CartItemList> findAll(Long memberId) {
        return cartItemMapper.findAll(memberId);
    }

    @Override
    public void update(CartItem cartItem) {
        cartItemMapper.update(cartItem);
    }

    @Override
    public void delete(Long id) {
        cartItemMapper.delete(id);
    }
}
