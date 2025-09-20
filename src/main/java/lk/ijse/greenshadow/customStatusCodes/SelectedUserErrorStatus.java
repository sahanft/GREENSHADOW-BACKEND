package lk.ijse.greenshadow.customStatusCodes;

import lk.ijse.greenshadow.dto.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SelectedUserErrorStatus implements UserStatus {
    private int statusCode;
    private String statusMessage;
}
