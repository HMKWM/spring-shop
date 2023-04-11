package springboot.shop.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import springboot.shop.domain.ItemImage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {

    @Value("${upload.dir}")
    String fileDir;

    public List<ItemImage> saveImage(List<MultipartFile> multipartFileList, Long itemId) throws IOException {

        List<ItemImage> itemImageList = new ArrayList<>();

        for(int i = 0 ; i < multipartFileList.size() ; i++){
            UUID uuid = UUID.randomUUID();
            MultipartFile multipartFile = multipartFileList.get(i);
            String saveFileName = uuid + "_" + multipartFile.getOriginalFilename();

            multipartFile.transferTo(new File(fileDir+saveFileName));

            ItemImage itemImage = new ItemImage();
            itemImage.setItemId(itemId);
            itemImage.setImgName(saveFileName);
            itemImage.setOriginImgName(multipartFile.getOriginalFilename());
            itemImage.setOrders(i);
            itemImage.setPaths(fileDir);
            itemImageList.add(itemImage);
        }

        return itemImageList;
    }
}
