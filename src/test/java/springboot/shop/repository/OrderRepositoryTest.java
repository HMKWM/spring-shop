package springboot.shop.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springboot.shop.domain.Item;
import springboot.shop.domain.ItemImage;
import springboot.shop.domain.Order;
import springboot.shop.domain.OrderItem;
import springboot.shop.repository.mapper.OrderMapper;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderRepositoryTest {

    @Autowired
    OrderMapper orderMapper;

    @Test
    void findByMemberId() {
        Order findOrder = orderMapper.findByMemberId(9L).get(0);
        System.out.println("findOrder.getOrderId() = " + findOrder.getOrderId());
        OrderItem orderItem = findOrder.getOrderItemList().get(0);
        System.out.println("orderItem.getOrderItemId() = " + orderItem.getOrderItemId());
        Item item = orderItem.getItem();
        System.out.println("item.getItemId() = " + item.getItemId());
        ItemImage itemImage = item.getItemImageList().get(0);
        System.out.println("itemImage.getItemImgId() = " + itemImage.getItemImgId());
    }
}