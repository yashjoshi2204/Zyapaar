package com.example.inventoryManagement.Repository;
import com.example.inventoryManagement.Entity.ImageStoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageStoreRepo extends JpaRepository<ImageStoreEntity,String> {

    ImageStoreEntity findByFileName(String imageName);
}
