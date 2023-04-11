package springboot.shop.repository;

import springboot.shop.domain.ItemImage;

public interface ItemImageRepository {
    ItemImage save(ItemImage itemImage);

    void update(ItemImage itemImage);

    void delete(Long id);
}
