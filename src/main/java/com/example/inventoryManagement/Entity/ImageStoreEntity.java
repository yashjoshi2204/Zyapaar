package com.example.inventoryManagement.Entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import java.io.Serializable;

@Entity
@Table(name = "image_store")
@Data
@NoArgsConstructor
public class ImageStoreEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "file_name")
    private String fileName;
    @Lob
    private byte[] data;

    public ImageStoreEntity(String fileName, byte[] data) {
        this.fileName = fileName;
        this.data = data;
    }

}
