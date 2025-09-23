package lk.ijse.greenshadow.dto;
import java.time.LocalDateTime;


public class HealthRecordDTO {


    private Long id;


    @NotBlank(message = "serviceName is required")
    @Size(max = 100)
    private String serviceName;

    @NotBlank(message = "status is required")
    @Size(max = 50)
    private String status;


    private String details;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public HealthRecordDTO() {}


    public Long getId() {
        return id;
    }



}

