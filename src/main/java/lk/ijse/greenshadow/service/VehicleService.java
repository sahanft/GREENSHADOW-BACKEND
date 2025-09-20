package lk.ijse.greenshadow.service;

import lk.ijse.greenshadow.dto.VehicleDTO;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDTO vehicleDTO);
    void updateVehicle(VehicleDTO vehicleDTO);
    void deleteVehicle(String vehicleCode);
    List<VehicleDTO> getAllVehicles();
    String findLastVehicleCode();
    VehicleDTO getVehicleByCode(String vehicleCode);
}
