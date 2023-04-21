package springboot.shop.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import springboot.shop.domain.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MyBatisOrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    MemberRepository memberRepository;

    Member member = null;
    @BeforeEach
    void before(){
        member = new Member();
        member.setEmail("test1");
        member.setPassword("testPassword");
        member.setAddress("testAddress");
        member.setName("testName");
        member.setRole(Role.USER);
        memberRepository.save(member);
    }

    @Test
    @Transactional
    void crud(){
        //create
        Order order = new Order();
        order.setMemberId(member.getMemberId());
        order.setStatus(OrderStatus.ORDER);
        orderRepository.save(order);

        //update, read
        orderRepository.updateStatus(order.getOrderId(), OrderStatus.CANCEL);

        Long findMemberId = orderRepository.findMemberIdByOrderId(order.getOrderId());
        assertThat(findMemberId).isNotNull();

        //delete
        orderRepository.delete(order.getOrderId());
        Long deleteMember = orderRepository.findMemberIdByOrderId(order.getOrderId());
        assertThat(deleteMember).isNull();
    }

    @Test
    void findByMemberId() {
        Order findOrder = orderRepository.findByMemberId(9L).get(0);
        assertThat(findOrder.getOrderId()).isNotNull();

        OrderItem orderItem = findOrder.getOrderItemList().get(0);
        assertThat(orderItem.getOrderItemId()).isNotNull();

        Item item = orderItem.getItem();
        assertThat(item.getItemId()).isNotNull();

        ItemImage itemImage = item.getItemImageList().get(0);
        assertThat(itemImage.getItemImgId()).isNotNull();
    }
}