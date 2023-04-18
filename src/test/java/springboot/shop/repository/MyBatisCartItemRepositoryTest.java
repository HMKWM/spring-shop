package springboot.shop.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import springboot.shop.domain.CartItem;
import springboot.shop.domain.CartItemView;
import springboot.shop.domain.Item;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MyBatisCartItemRepositoryTest {

    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    ItemRepository itemRepository;

    Item item = null;

    @BeforeEach
    void before(){
        item = new Item();
        item.setName("test Item");
        item.setPrice(10000);
        item.setQuantity(32);
        item.setContent("test content");
        itemRepository.save(item);
    }

    @Test
    @Transactional
    void crd() {
        CartItem cartItem = new CartItem();
        cartItem.setItemId(item.getItemId());
        cartItem.setMemberId(9L);
        cartItem.setCount(10);
        cartItemRepository.save(cartItem);
        //create
        Assertions.assertThat(cartItem.getCartItemId()).isNotNull();

        //delete
        List deleteList = new ArrayList<>();
        deleteList.add(cartItem.getCartItemId());
        cartItemRepository.delete(deleteList);

        //read
        CartItemView findCartItemView = cartItemRepository.findById(cartItem.getCartItemId());
        Assertions.assertThat(findCartItemView).isNull();

    }
}