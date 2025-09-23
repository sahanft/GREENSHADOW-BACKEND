package lk.ijse.greenshadow.dto;
import java.time.LocalDateTime;


public class Health {


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

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }



}

