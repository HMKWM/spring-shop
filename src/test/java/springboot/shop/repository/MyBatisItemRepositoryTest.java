package springboot.shop.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import springboot.shop.domain.Item;

@SpringBootTest
class MyBatisItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;
    Item item;

    @BeforeEach
    void before(){
        item = new Item();
        item.setName("test");
        item.setPrice(1000);
        item.setQuantity(10);
        item.setContent("이 아이템은 테스트용입니다.");
    }

    @Test
    @Transactional
    void save(){
        itemRepository.save(item);
        Assertions.assertThat(item.getItemId()).isNotNull();

        Item findItem = itemRepository.findById(item.getItemId());
        Assertions.assertThat(findItem).isEqualTo(item);
    }

    @Test
    @Transactional
    void update(){
        itemRepository.save(item);
        String updateName = "updateName";
        item.setName(updateName);

        itemRepository.update(item);
        Item findItem = itemRepository.findById(item.getItemId());
        Assertions.assertThat(findItem.getName()).isEqualTo(updateName);
        Assertions.assertThat(findItem.getCreateDate()).isNotEqualTo(findItem.getUpdateDate());
    }

    @Test
    @Transactional
    void delete(){
        itemRepository.save(item);
        Item findItem = itemRepository.findById(item.getItemId());
        Assertions.assertThat(findItem).isNotNull();
        itemRepository.delete(item.getItemId());
        Item deleteItem = itemRepository.findById(item.getItemId());
        Assertions.assertThat(deleteItem).isNull();
    }

}