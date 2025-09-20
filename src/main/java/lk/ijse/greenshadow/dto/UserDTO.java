package lk.ijse.greenshadow.dto;

import lk.ijse.greenshadow.entity.StaffRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String userId;
    private String email;
    private String password;
    private StaffRole role;
}
