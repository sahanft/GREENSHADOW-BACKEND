package lk.ijse.greenshadow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "staff")
public class StaffEntity {
    @Id
    private String staffId;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Date joinDate;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;
    private String email;
    private String designation;
    private String gender;

    @Enumerated(EnumType.STRING)
    private StaffRole role;

    @ManyToMany(mappedBy = "staffs", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<LogEntity> logs;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "field_staff",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "field_id")
    )
    private List<FieldEntity> fields;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.PERSIST)
    private List<VehicleEntity> vehicles;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    private List<EquipmentEntity> equipment;
}
