package lk.ijse.greenshadow.controller;

import lk.ijse.greenshadow.dto.VehicleDTO;
import lk.ijse.greenshadow.service.VehicleService;
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
@RequestMapping("/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveVehicle(@RequestBody VehicleDTO vehicleDTO) {
        vehicleService.saveVehicle(vehicleDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateVehicle(@RequestBody VehicleDTO vehicleDTO) {
        vehicleService.updateVehicle(vehicleDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    @DeleteMapping("/{vehicleCode}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable String vehicleCode) {
        vehicleService.deleteVehicle(vehicleCode);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE') or hasRole('SCIENTIST')")
    @GetMapping
    public ResponseUtil getAllVehicles() {
        return new ResponseUtil("Success", "Retrieved All Vehicles", vehicleService.getAllVehicles());
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    @GetMapping("/newcode")
    public ResponseUtil getNewVehicleCode() {
        return new ResponseUtil("Success", "Retrieved New Vehicle Code", AppUtil.generateVehicleCode(vehicleService.findLastVehicleCode()));
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE') or hasRole('SCIENTIST')")
    @GetMapping("/{vehicleCode}")
    public ResponseUtil getVehicleByCode(@PathVariable("vehicleCode") String vehicleCode) {
        return new ResponseUtil("Success", "Retrieved Vehicle", vehicleService.getVehicleByCode(vehicleCode));
    }
}
