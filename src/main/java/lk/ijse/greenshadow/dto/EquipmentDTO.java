package lk.ijse.greenshadow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EquipmentDTO {
    private String equipmentId;
    private String name;
    private String type;
    private String status;
    private String staffId;
    private String fieldCode;
}
