package lk.ijse.greenshadow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "equipment")
public class EquipmentEntity {
    @Id
    private String equipmentId;
    private String name;
    private String type;
    private String status;
    @ManyToOne
    @JoinColumn(name = "staff_id")
    StaffEntity staff;
    @ManyToOne
    @JoinColumn(name = "field_id")
    FieldEntity field;
}
