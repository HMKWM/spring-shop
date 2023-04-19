package springboot.shop.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import springboot.shop.domain.Item;
import springboot.shop.domain.ItemImage;
import springboot.shop.domain.PageHandler;
import springboot.shop.domain.SearchCond;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MyBatisItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ItemImageRepository itemImageRepository;

    Item item = null;

    @BeforeEach
    void before(){
        item = new Item();
        item.setName("test");
        item.setPrice(1000);
        item.setQuantity(10);
        item.setContent("이 아이템은 테스트용입니다.");
        itemRepository.save(item);

        ItemImage img = new ItemImage();
        img.setItemId(item.getItemId());
        img.setImgName("test 이미지");
        img.setOriginImgName("test 이미지");
        img.setPaths("/test");
        img.setOrders(0);

        itemImageRepository.save(img);
    }

    @Test
    @Transactional
    void findById(){
        assertThat(item.getItemId()).isNotNull();

        Item findItem = itemRepository.findById(item.getItemId());
        assertThat(findItem).isEqualTo(item);
        assertThat(findItem.getItemImageList().size()).isEqualTo(1);

    }

    @Test
    @Transactional
    void update() throws InterruptedException {
        String updateName = "updateName";
        item.setName(updateName);

        Thread.sleep(2000);
        itemRepository.update(item);
        Item findItem = itemRepository.findById(item.getItemId());
        assertThat(findItem.getName()).isEqualTo(updateName);
        assertThat(findItem.getCreateDate()).isNotEqualTo(findItem.getUpdateDate());
    }

    @Test
    @Transactional
    void delete(){
        Item findItem = itemRepository.findById(item.getItemId());
        assertThat(findItem).isNotNull();
        itemRepository.delete(item.getItemId());
        Item deleteItem = itemRepository.findById(item.getItemId());
        assertThat(deleteItem).isNull();
    }

    @Test
    @Transactional
    void findAll(){
        int count = itemRepository.count("");
        SearchCond cond = new SearchCond();
        cond.setPage(count%9 == 0 ? count/9 : count/9+1);
        cond.setPageSize(9);
        PageHandler ph = new PageHandler(count, 10, cond);
        List<Item> findList = itemRepository.findAll(ph);

        ItemImage itemImage = findList.get(findList.size()-1).getItemImageList().get(0);
        assertThat(itemImage).isNotNull();
    }
}