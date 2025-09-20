package lk.ijse.greenshadow.controller;

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

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST') or hasRole('ADMINISTRATIVE')")
    @GetMapping("/fieldcrops/{fieldCode}")
    public ResponseUtil getFieldCropsByFieldCode(@PathVariable("fieldCode") String fieldCode) {
        return new ResponseUtil("Success", "Retrieved Field Crops", fieldService.getFieldCropsByFieldCode(fieldCode));
    }
