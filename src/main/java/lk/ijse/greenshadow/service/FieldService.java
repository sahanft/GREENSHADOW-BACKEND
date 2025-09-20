package lk.ijse.greenshadow.service;

import lk.ijse.greenshadow.dto.CropDTO;
import lk.ijse.greenshadow.dto.FieldCropDTO;
import lk.ijse.greenshadow.dto.FieldDTO;

import java.util.List;

public interface FieldService {
    void saveField(FieldDTO fieldDTO);
    void updateField(FieldDTO fieldDTO);
    void deleteField(String fieldCode);
    List<FieldDTO> getAllFields();
    void saveFieldCrops(FieldCropDTO fieldCropDTO);
    void deleteFieldCrops(String fieldCode, String cropCode);
    String findLastFieldCode();
    FieldDTO getFieldByCode(String fieldCode);
    List<CropDTO> getFieldCropsByFieldCode(String fieldCode);
}
