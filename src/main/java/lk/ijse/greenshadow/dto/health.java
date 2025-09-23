package lk.ijse.greenshadow.dto;
import java.time.LocalDateTime;


public class HealthRecordDTO {


    private Long id;


    @NotBlank(message = "serviceName is required")
    @Size(max = 100)
    private String serviceName;



}

