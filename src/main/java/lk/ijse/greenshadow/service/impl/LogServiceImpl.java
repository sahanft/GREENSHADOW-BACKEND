package lk.ijse.greenshadow.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.greenshadow.dto.CropDTO;
import lk.ijse.greenshadow.dto.FieldDTO;
import lk.ijse.greenshadow.dto.LogDTO;
import lk.ijse.greenshadow.dto.StaffDTO;
import lk.ijse.greenshadow.entity.CropEntity;
import lk.ijse.greenshadow.entity.FieldEntity;
import lk.ijse.greenshadow.entity.LogEntity;
import lk.ijse.greenshadow.entity.StaffEntity;
import lk.ijse.greenshadow.exception.DataPersistException;
import lk.ijse.greenshadow.exception.LogNotFoundException;
import lk.ijse.greenshadow.repo.CropRepo;
import lk.ijse.greenshadow.repo.FieldRepo;
import lk.ijse.greenshadow.repo.LogRepo;
import lk.ijse.greenshadow.repo.StaffRepo;
import lk.ijse.greenshadow.service.LogService;
import lk.ijse.greenshadow.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepo logRepo;

    @Autowired
    private FieldRepo fieldRepo;

    @Autowired
    private CropRepo cropRepo;

    @Autowired
    private StaffRepo staffRepo;

    @Autowired
    private MapperUtil mapperUtil;

    @Transactional
    public void saveLog(LogDTO logDTO) {
        if (logRepo.existsById(logDTO.getLogCode())) {
            throw new DataPersistException(logDTO.getLogCode() + " : Log Already Exist");
        }
        List<String> logFields = new ArrayList<>();
        for (FieldDTO logField : logDTO.getLogFields()) {
            logFields.add(logField.getFieldCode());
        }
        List<String> logCrops = new ArrayList<>();
        for (CropDTO logCrop : logDTO.getLogCrops()) {
            logCrops.add(logCrop.getCropCode());
        }
        List<String> logStaff = new ArrayList<>();
        for (StaffDTO staffDTO : logDTO.getLogStaff()) {
            logStaff.add(staffDTO.getStaffId());
        }
        List<FieldEntity> fields = fieldRepo.findAllById(logFields);
        List<CropEntity> crops = cropRepo.findAllById(logCrops);
        List<StaffEntity> staffs = staffRepo.findAllById(logStaff);
        LogEntity logEntity = mapperUtil.mapLogDtoToEntity(logDTO);
        logEntity.setFields(fields);
        logEntity.setCrops(crops);
        logEntity.setStaffs(staffs);
        logRepo.save(logEntity);
    }

    @Override
    public List<LogDTO> getAllLogs() {
        List<LogEntity> logEntities = logRepo.findAll();
        List<LogDTO> logDTOs = new ArrayList<>();
        for (LogEntity logEntity : logEntities) {
            LogDTO logDTO = new LogDTO();
            logDTO.setLogCode(logEntity.getLogCode());
            logDTO.setDate(logEntity.getDate());
            logDTO.setDetails(logEntity.getDetails());
            logDTO.setImage(logEntity.getImage());
            logDTO.setLogFields(mapperUtil.mapFieldEntitiesToDtos(logEntity.getFields()));
            logDTO.setLogCrops(mapperUtil.mapCropEntitiesToDtos(logEntity.getCrops()));
            logDTO.setLogStaff(mapperUtil.mapStaffEntitiesToDtos(logEntity.getStaffs()));
            logDTOs.add(logDTO);
        }
        return logDTOs;
    }

    @Override
    public void deleteLog(String logCode) {
        if (!logRepo.existsById(logCode)) {
            throw new LogNotFoundException(logCode + " : Log Does Not Exist");
        }
        logRepo.deleteById(logCode);
    }

    @Override
    public String findLastLogCode() {
        return logRepo.findLastLogCode();
    }
}
