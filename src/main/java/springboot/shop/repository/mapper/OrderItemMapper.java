package springboot.shop.repository.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface OrderItemMapper {

    void save(Map map);
}
