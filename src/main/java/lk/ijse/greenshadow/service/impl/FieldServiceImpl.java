package lk.ijse.greenshadow.service.impl;

import lk.ijse.greenshadow.dto.CropDTO;
import lk.ijse.greenshadow.dto.FieldCropDTO;
import lk.ijse.greenshadow.dto.FieldDTO;
import lk.ijse.greenshadow.entity.CropEntity;
import lk.ijse.greenshadow.entity.FieldEntity;
import lk.ijse.greenshadow.exception.CropNotFoundException;
import lk.ijse.greenshadow.exception.DataPersistException;
import lk.ijse.greenshadow.exception.FieldNotFoundException;
import lk.ijse.greenshadow.repo.CropRepo;
import lk.ijse.greenshadow.repo.FieldRepo;
import lk.ijse.greenshadow.service.FieldService;
import lk.ijse.greenshadow.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {
    @Autowired
    FieldRepo fieldRepo;
    @Autowired
    CropRepo cropRepo;
    @Autowired
    MapperUtil mapperUtil;
    @Override
    public void saveField(FieldDTO fieldDTO) {
        if(fieldRepo.existsById(fieldDTO.getFieldCode())) {
            throw new DataPersistException(fieldDTO.getFieldCode() + " : Field Already Exist");
        }
        fieldRepo.save(mapperUtil.mapFieldDtoToEntity(fieldDTO));
    }

    @Override
    public void updateField(FieldDTO fieldDTO) {
        if(!fieldRepo.existsById(fieldDTO.getFieldCode())) {
            throw new FieldNotFoundException(fieldDTO.getFieldCode() + " : Field Does Not Exist");
        }
        fieldRepo.save(mapperUtil.mapFieldDtoToEntity(fieldDTO));
    }

    @Override
    public void deleteField(String fieldCode) {
        if(!fieldRepo.existsById(fieldCode)) {
            throw new FieldNotFoundException(fieldCode + " : Field Does Not Exist");
        }
        fieldRepo.deleteById(fieldCode);
    }

    @Override
    public List<FieldDTO> getAllFields() {
        return mapperUtil.mapFieldEntitiesToDtos(fieldRepo.findAll());
    }

    @Override
    public void saveFieldCrops(FieldCropDTO fieldCropDTO) {
        Optional<FieldEntity> fieldOpt = fieldRepo.findById(fieldCropDTO.getFieldCode());
        Optional<CropEntity> cropOpt = cropRepo.findById(fieldCropDTO.getCropCode());
        if(!fieldOpt.isPresent()) {
            throw new FieldNotFoundException(fieldCropDTO.getFieldCode() + " : Field Does Not Exist");
        } else if(!cropOpt.isPresent()) {
            throw new CropNotFoundException(fieldCropDTO.getCropCode() + " : Crop Does Not Exist");
        }
        FieldEntity field = fieldOpt.get();
        CropEntity crop = cropOpt.get();
        if (field.getCrops().contains(crop)) {
            throw new DataPersistException(fieldCropDTO.getFieldCode() + " : Field Already Have This Crop : " + fieldCropDTO.getCropCode());
        }
        field.getCrops().add(crop);
        crop.getFields().add(field);
        fieldRepo.save(field);
    }

    @Override
    public void deleteFieldCrops(String fieldCode, String cropCode) {
        Optional<FieldEntity> fieldOpt = fieldRepo.findById(fieldCode);
        Optional<CropEntity> cropOpt = cropRepo.findById(cropCode);
        if(!fieldOpt.isPresent()) {
            throw new FieldNotFoundException(fieldCode + " : Field Does Not Exist");
        } else if(!cropOpt.isPresent()) {
            throw new CropNotFoundException(cropCode + " : Crop Does Not Exist");
        }
        FieldEntity field = fieldOpt.get();
        CropEntity crop = cropOpt.get();
        field.getCrops().remove(crop);
        crop.getFields().remove(field);
        fieldRepo.save(field);
    }

    @Override
    public String findLastFieldCode() {
        return fieldRepo.getLastFieldCode();
    }

    @Override
    public FieldDTO getFieldByCode(String fieldCode) {
        Optional<FieldEntity> fieldOpt = fieldRepo.findById(fieldCode);
        if(!fieldOpt.isPresent()) {
            throw new FieldNotFoundException(fieldCode + " : Field Does Not Exist");
        }
        return mapperUtil.mapFieldEntityToDto(fieldOpt.get());
    }

    @Override
    public List<CropDTO> getFieldCropsByFieldCode(String fieldCode) {
        Optional<FieldEntity> fieldOpt = fieldRepo.findById(fieldCode);
        if(!fieldOpt.isPresent()) {
            throw new FieldNotFoundException(fieldCode + " : Field Does Not Exist");
        }
        return mapperUtil.mapCropEntitiesToDtos(fieldOpt.get().getCrops());
    }
}
