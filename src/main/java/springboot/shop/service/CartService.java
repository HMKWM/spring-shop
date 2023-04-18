package springboot.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.shop.domain.CartItem;
import springboot.shop.domain.CartItemView;
import springboot.shop.exception.CartItemNotFoundException;
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

    public List<CartItemView> getCartItemList(Long memberId){
        return cartItemRepository.findAll(memberId);
    }

    public void removeCartItem(List<Long> cartItemIdList, Long memberId){
        List<Long> findCartItemIdList = cartItemRepository.findByMemberId(memberId);
        if(!findCartItemIdList.containsAll(cartItemIdList) || cartItemIdList.isEmpty()){
            throw new CartItemNotFoundException("Cannot find corresponding cart item(s).");
        }

        cartItemRepository.delete(cartItemIdList);
    }
}
