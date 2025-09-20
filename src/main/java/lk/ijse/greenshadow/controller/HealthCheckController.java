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



    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE') or hasRole('SCIENTIST')")
    @GetMapping
    public ResponseUtil getAllEquipments() {
        return new ResponseUtil("Success", "Retrieved All Equipments", equipmentService.getAllEquipments());
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    @GetMapping("/newid")
    public ResponseUtil getNewEquipmentId() {
        return new ResponseUtil("Success", "Retrieved New Equipment Id", AppUtil.generateEquipmentId(equipmentService.findLastEquipmentId()));
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE') or hasRole('SCIENTIST')")
    @GetMapping("/{equipmentId}")
    public ResponseUtil getEquipmentById(@PathVariable("equipmentId") String equipmentId) {
        return new ResponseUtil("Success", "Retrieved Equipment", equipmentService.getEquipmentById(equipmentId));
    }
