package lk.ijse.greenshadow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LogDTO {
    private String logCode;
    private String date;
    private String details;
    private String image;
    private List<FieldDTO> logFields;
    private List<CropDTO> logCrops;
    private List<StaffDTO> logStaff;
}
