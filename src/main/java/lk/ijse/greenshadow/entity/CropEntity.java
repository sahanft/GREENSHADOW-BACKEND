package lk.ijse.greenshadow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "crop")
public class CropEntity {
    @Id
    private String cropCode;
    private String commonName;
    private String scientificName;
    private String category;
    private String season;

    @Column(columnDefinition = "LONGTEXT")
    private String image;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "field_crop",
            joinColumns = @JoinColumn(name = "crop_id"),
            inverseJoinColumns = @JoinColumn(name = "field_id")
    )
    private List<FieldEntity> fields;

    @ManyToMany(mappedBy = "crops", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<LogEntity> logs;
}
