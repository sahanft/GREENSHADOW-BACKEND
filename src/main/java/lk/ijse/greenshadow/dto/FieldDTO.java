package lk.ijse.greenshadow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FieldDTO {
    private String fieldCode;
    private String name;
    private String location;
    private String size;
    private String imageOne;
    private String imageTwo;
}
