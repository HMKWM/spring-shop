package springboot.shop.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springboot.shop.domain.OrderItem;
import springboot.shop.repository.mapper.OrderItemMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class MyBatisOrderItemRepository implements OrderItemRepository{

    private final OrderItemMapper orderItemMapper;

    @Override
    public void save(Long orderId, List<OrderItem> orderItemList) {
        Map map = new HashMap();
        map.put("orderId", orderId);
        map.put("orderItemList", orderItemList);

        orderItemMapper.save(map);
    }
}
