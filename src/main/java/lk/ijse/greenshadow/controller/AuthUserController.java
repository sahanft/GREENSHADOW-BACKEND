package lk.ijse.greenshadow.controller;

import lk.ijse.greenshadow.dto.UserDTO;
import lk.ijse.greenshadow.entity.StaffRole;
import lk.ijse.greenshadow.exception.DataPersistException;
import lk.ijse.greenshadow.secure.JWTAuthResponse;
import lk.ijse.greenshadow.secure.SignIn;
import lk.ijse.greenshadow.service.AuthService;
import lk.ijse.greenshadow.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthUserController {
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    @PostMapping(value = "/signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTAuthResponse> saveUser(
            @RequestPart("email") String email,
            @RequestPart("role") String role,
            @RequestPart("password") String password
    ) {
        try {
            String userId = AppUtil.generateUserId();
            UserDTO buildUserDTO = new UserDTO();
            buildUserDTO.setUserId(userId);
            buildUserDTO.setEmail(email);
            buildUserDTO.setPassword(passwordEncoder.encode(password));
            buildUserDTO.setRole(StaffRole.valueOf(role));

            return ResponseEntity.ok(authService.signUp(buildUserDTO));
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value = "/signin", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTAuthResponse> signIn(@RequestBody SignIn signIn){
        return ResponseEntity.ok(authService.signIn(signIn));
    }
    @PostMapping("/refresh")
    public ResponseEntity<JWTAuthResponse> refreshToken(@RequestParam("existingToken") String existingToken) {
        return ResponseEntity.ok(authService.refreshToken(existingToken));
    }
}
