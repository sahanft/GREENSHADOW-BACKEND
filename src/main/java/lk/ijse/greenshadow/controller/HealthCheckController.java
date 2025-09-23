package lk.ijse.greenshadow.controller;

import lk.ijse.greenshadow.dto.FieldStaffDTO;
import lk.ijse.greenshadow.dto.StaffDTO;
import lk.ijse.greenshadow.service.StaffService;
import lk.ijse.greenshadow.util.AppUtil;
import lk.ijse.greenshadow.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/health")
public class HealthCheckController {
//    @GetMapping
//    public String healthCheck() {
//        return "Healthy";
//    }
//----------------------------------------------------------------------------------------
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil healthCheck() {
        return new ResponseUtil(200, "Application is Healthy ✅", null);
    }

    public ResponseUtil createHealthRecord(@RequestBody String request) {
        return new ResponseUtil(201, "Health record created successfully ✅", request);
    }
    public ResponseUtil getHealthRecord(@PathVariable String id) {
        return new ResponseUtil(200, "Health record fetched successfully ✅", "HealthRecord#" + id);
    }
    public ResponseUtil updateHealthRecord(@PathVariable String id, @RequestBody String updateRequest) {
        return new ResponseUtil(200, "Health record updated successfully ✅", "UpdatedRecord#" + id + " -> " + updateRequest);
    }





}

























