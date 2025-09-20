package lk.ijse.greenshadow.service;

import lk.ijse.greenshadow.dto.CropDTO;

import java.util.List;

public interface CropService {
    void saveFieldCrops(CropDTO cropDTO);
    void updateFieldCrops(CropDTO cropDTO);
    void deleteCrop(String cropCode);
    List<CropDTO> getAllCrops();
    String findLastCropCode();
    CropDTO getCropByCode(String cropCode);
}
