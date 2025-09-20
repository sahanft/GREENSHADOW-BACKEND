package lk.ijse.greenshadow.controller;

import lk.ijse.greenshadow.repo.UserRepo;
import lk.ijse.greenshadow.service.UserService;
import lk.ijse.greenshadow.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping
    public ResponseUtil getAllUsers() {
        return new ResponseUtil("Success", "Retrieved All Users", userService.getAllUsers());
    }

    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("/{userId}")
    public ResponseUtil deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
        return new ResponseUtil("Success", "Deleted User", null);
    }
}
