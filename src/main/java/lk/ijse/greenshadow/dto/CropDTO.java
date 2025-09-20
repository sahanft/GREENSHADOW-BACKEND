package lk.ijse.greenshadow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CropDTO {
    private String cropCode;
    private String commonName;
    private String scientificName;
    private String category;
    private String season;
    private String image;
}
