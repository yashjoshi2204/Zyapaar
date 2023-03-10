package com.example.inventoryManagement.Controller;

import com.example.inventoryManagement.Entity.ImageStoreEntity;
import com.example.inventoryManagement.Entity.ItemEntity;
import com.example.inventoryManagement.Service.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class ItemController {

    @Autowired
    ItemServiceImpl itemService;
    @PostMapping("/image")
    public ResponseEntity<Object> storeImage(@RequestPart(value = "file") MultipartFile file) throws IOException {
         Object response= itemService.storeImage(file);
         return ResponseEntity.status(HttpStatus.OK)
                 .body(response);
    }
    @GetMapping("/image/{uuid}")
    public ModelAndView getImage(@PathVariable(value = "uuid") String uuid, Model model) throws
            UnsupportedEncodingException {
        Optional<ImageStoreEntity> imageStore = itemService.getImage(uuid);
        byte[] encodeBase64 = Base64.getEncoder().encode(imageStore.get().getData());
        String base64Encoded = new String(encodeBase64, "UTF-8");
        model.addAttribute("contentImage", base64Encoded );
        ModelAndView modelAndView = new ModelAndView("view");
        modelAndView.addObject("contentImage",base64Encoded );
        return modelAndView;

    }

    @PostMapping("/item")
    public ItemEntity saveItem(@RequestBody ItemEntity itemEntity){
        return itemService.saveItem(itemEntity);
    }
    @GetMapping("/item/{id}")
    public Optional<ItemEntity> getItem(@PathVariable(value = "id") String id){
        return itemService.getItem(id);
    }
    @GetMapping("/item")
    public List<ItemEntity> getItems(){
        return itemService.getItems();
    }
    @DeleteMapping("/item/{id}")
    public void deleteItem(@PathVariable(value = "id") String id){
        itemService.deleteItem(id);
    }
    @PutMapping("/item")
    public ItemEntity updateItem(@RequestBody ItemEntity itemEntity){
        return itemService.updateItem(itemEntity);
    }
}
