package springboot.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.shop.domain.Item;
import springboot.shop.domain.PageHandler;
import springboot.shop.domain.SearchCond;
import springboot.shop.repository.ItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Item findItem(Long id){
        return itemRepository.findById(id);
    }

    public List<Item> getItemList(PageHandler ph){
        return itemRepository.findAll(ph);
    }

    public int count(String keyword){
        return itemRepository.count(keyword);
    }


}
