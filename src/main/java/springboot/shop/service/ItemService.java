package springboot.shop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import springboot.shop.domain.*;
import springboot.shop.repository.ItemImageRepository;
import springboot.shop.repository.ItemRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemImageRepository itemImageRepository;

    private final FileService fileService;

    public Item createItem(ItemForm itemForm){
        Item item = new Item();
        item.setName(itemForm.getName());
        item.setPrice(itemForm.getPrice());
        item.setQuantity(itemForm.getQuantity());
        item.setContent(itemForm.getContent());
        return item;
    }

    @Transactional
    public void saveItem(Item item, List<MultipartFile> itemImgList){
        Item saveItem = itemRepository.save(item);
        try{
            List<ItemImage> itemImages = fileService.saveImage(itemImgList, saveItem.getItemId());
            for(ItemImage itemImage : itemImages){
                itemImageRepository.save(itemImage);
            }
        } catch (Exception e){
            log.error("error = {}",e);
        }
    }

    public Item findItem(Long id){
        return itemRepository.findById(id);
    }

    public List<Item> getItemList(PageHandlerVO ph){
        return itemRepository.findAll(ph);
    }

    public int count(String keyword){
        return itemRepository.count(keyword);
    }

    @Transactional
    public void update(Item item, List<MultipartFile> itemImgList){
        itemRepository.update(item);
        try{
            List<ItemImage> itemImages = fileService.saveImage(itemImgList, item.getItemId());
            for(ItemImage itemImage : itemImages){
                itemImageRepository.disconnectItemImage(item.getItemId(), 0);
                itemImageRepository.save(itemImage);
            }
        } catch (Exception e){
            log.error("error = {}",e);
        }
    }

    public void delete(Long id){
        itemRepository.delete(id);
    }
}
