package com.example.inventoryManagement.Service;

import com.example.inventoryManagement.Entity.ImageStoreEntity;
import com.example.inventoryManagement.Entity.ItemEntity;
import com.example.inventoryManagement.Repository.ImageStoreRepo;
import com.example.inventoryManagement.Repository.ItemRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl {
    @Autowired
    private ImageStoreRepo imageStoreRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Transactional
    public ImageStoreEntity storeImage(MultipartFile image) throws IOException {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        try {
            if(fileName==null && fileName.contains("")) {
                throw new FileNotFoundException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            ImageStoreEntity file = new ImageStoreEntity(fileName, image.getBytes());
            return imageStoreRepo.save(file);
        } catch (IOException e) {
            throw new IOException("Please Try Again");
        }
    }

    public Optional<ImageStoreEntity> getImage(String uuid) {
        return imageStoreRepo.findById(uuid);
    }

    public ItemEntity saveItem(ItemEntity itemEntity) {
        return itemRepo.save(itemEntity);
    }

    public Optional<ItemEntity> getItem(String id) {
        return itemRepo.findById(id);
    }

    public List<ItemEntity> getItems() {
        return (List<ItemEntity>) itemRepo.findAll();
    }

    public void deleteItem(String id) {
        itemRepo.deleteById(id);
    }

    public ItemEntity updateItem(ItemEntity itemEntity){
        if(itemEntity.getId()==null){
            throw new RuntimeException("Invalid value for Id");
        }
        Optional<ItemEntity> itemDBOp = itemRepo.findById(itemEntity.getId());

        if(itemDBOp.isPresent()){
            ItemEntity itemDB =itemDBOp.get();
            itemDB.setItemQuantity(itemEntity.getItemQuantity());
            return itemRepo.save(itemDB);
        }
        else{
            throw new RuntimeException("Item Not Found");
        }
    }

}
