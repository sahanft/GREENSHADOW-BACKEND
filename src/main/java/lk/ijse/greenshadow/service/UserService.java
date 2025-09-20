package lk.ijse.greenshadow.service;

import lk.ijse.greenshadow.dto.UserDTO;
import lk.ijse.greenshadow.dto.UserStatus;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserStatus getUser(String userId);
    void deleteUser(String userId);
    void updateUser(String userId, UserDTO userDTO);
    UserDetailsService userDetailsService();
}
