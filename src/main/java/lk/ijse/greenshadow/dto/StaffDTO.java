package lk.ijse.greenshadow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StaffDTO {
    private String staffId;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String joinDate;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;
    private String email;
    private String designation;
    private String role;
    private String gender;
}
