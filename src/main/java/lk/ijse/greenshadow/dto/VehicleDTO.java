package lk.ijse.greenshadow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleDTO {
    private String vehicleCode;
    private String vehicleNumber;
    private String vehicleCategory;
    private String fuelType;
    private String status;
    private String remarks;
    private String staffId;
}
