package lk.ijse.greenshadow.service;

import lk.ijse.greenshadow.dto.EquipmentDTO;

import java.util.List;

public interface EquipmentService {
    void saveEquipment(EquipmentDTO equipmentDTO);
    void updateEquipment(EquipmentDTO equipmentDTO);
    void deleteEquipment(String equipmentId);
    String findLastEquipmentId();
    List<EquipmentDTO> getAllEquipments();
    EquipmentDTO getEquipmentById(String equipmentId);
}
