package com.example.inventoryManagement.Repository;

import com.example.inventoryManagement.Entity.ImageStoreEntity;
import com.example.inventoryManagement.Entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends CrudRepository<ItemEntity,String> {

    ItemEntity findAllByItemName(String imageName);
}
