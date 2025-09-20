package lk.ijse.greenshadow.controller;

import lk.ijse.greenshadow.dto.EquipmentDTO;
import lk.ijse.greenshadow.service.EquipmentService;
import lk.ijse.greenshadow.util.AppUtil;
import lk.ijse.greenshadow.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveEquipment(@RequestBody EquipmentDTO equipmentDTO) {
        equipmentService.saveEquipment(equipmentDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateEquipment(@RequestBody EquipmentDTO equipmentDTO) {
        equipmentService.updateEquipment(equipmentDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    @DeleteMapping({"/{equipmentId}"})
    public ResponseEntity<Void> deleteEquipment(@PathVariable("equipmentId") String equipmentId) {
        equipmentService.deleteEquipment(equipmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
}
