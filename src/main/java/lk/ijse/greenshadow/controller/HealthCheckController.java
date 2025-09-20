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
    @GetMapping
    public String healthCheck() {
        return "Healthy";
    }
}
//---------------------------------------------------------------------

    private StaffService staffService;
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveStaff(@RequestBody StaffDTO staffDTO) {
        staffService.saveStaff(staffDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateStaff(@RequestBody StaffDTO staffDTO) {
        staffService.updateStaff(staffDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    @DeleteMapping(path = "/{staffId}")
    public ResponseEntity<Void> deleteStaff(@PathVariable String staffId) {
        staffService.deleteStaff(staffId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }















