package lk.ijse.greenshadow.service;

import lk.ijse.greenshadow.dto.UserDTO;
import lk.ijse.greenshadow.secure.JWTAuthResponse;
import lk.ijse.greenshadow.secure.SignIn;

public interface AuthService {
    JWTAuthResponse signIn(SignIn signIn);
    JWTAuthResponse signUp(UserDTO userDTO);
    JWTAuthResponse refreshToken(String accessToken);
}
