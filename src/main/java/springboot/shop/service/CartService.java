package springboot.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.shop.domain.CartItem;
import springboot.shop.domain.CartItemList;
import springboot.shop.repository.CartItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartItemRepository;

    public void saveCartItem(CartItem cartItem){
        cartItemRepository.save(cartItem);
    }

    public List<Long> getListByMemberId(Long memberId){
        return cartItemRepository.findByMemberId(memberId);
    }

    public List<CartItemList> getCartItemList(Long memberId){
        return cartItemRepository.findAll(memberId);
    }

    public void removeCartItem(List<Long> cartItemIdList){
        cartItemRepository.delete(cartItemIdList);
    }
}
