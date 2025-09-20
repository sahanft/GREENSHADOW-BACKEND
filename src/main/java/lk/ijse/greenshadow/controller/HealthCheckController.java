package lk.ijse.greenshadow.controller;

import lk.ijse.greenshadow.util.AppUtil;
import lk.ijse.greenshadow.util.ResponseUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/health")
public class HealthCheckController {
    @GetMapping
    public String healthCheck() {
        return "Healthy";
    }
}






