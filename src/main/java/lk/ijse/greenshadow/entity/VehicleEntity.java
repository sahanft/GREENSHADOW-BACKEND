package lk.ijse.greenshadow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "vehicle")
public class VehicleEntity {
    @Id
    private String vehicleCode;
    private String vehicleNumber;
    private String vehicleCategory;
    private String fuelType;
    private String status;
    private String remarks;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private StaffEntity staff;
}
