package lk.ijse.greenshadow.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.greenshadow.dto.FieldStaffDTO;
import lk.ijse.greenshadow.dto.StaffDTO;
import lk.ijse.greenshadow.entity.FieldEntity;
import lk.ijse.greenshadow.entity.StaffEntity;
import lk.ijse.greenshadow.exception.DataPersistException;
import lk.ijse.greenshadow.exception.FieldNotFoundException;
import lk.ijse.greenshadow.exception.StaffNotFoundException;
import lk.ijse.greenshadow.repo.FieldRepo;
import lk.ijse.greenshadow.repo.StaffRepo;
import lk.ijse.greenshadow.service.StaffService;
import lk.ijse.greenshadow.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffRepo staffRepo;
    @Autowired
    private FieldRepo fieldRepo;
    @Autowired
    private MapperUtil mapperUtil;
    @Override
    public void saveStaff(StaffDTO staffDTO) {
        if (staffRepo.existsById(staffDTO.getStaffId())) {
            throw new DataPersistException(staffDTO.getStaffId() + " : Staff Already Exist");
        }
        staffRepo.save(mapperUtil.mapStaffDtoToEntity(staffDTO));
    }

    @Override
    public void updateStaff(StaffDTO staffDTO) {
        if (!staffRepo.existsById(staffDTO.getStaffId())) {
           throw new StaffNotFoundException(staffDTO.getStaffId() + " : Staff Does Not Exist");
        }
        staffRepo.save(mapperUtil.mapStaffDtoToEntity(staffDTO));
    }

    @Override
    public void deleteStaff(String staffId) {
        if (!staffRepo.existsById(staffId)) {
            throw new StaffNotFoundException(staffId + " : Staff Does Not Exist");
        }
        staffRepo.deleteById(staffId);
    }

    @Override
    public List<StaffDTO> getAllStaffs() {
        return mapperUtil.mapStaffEntitiesToDtos(staffRepo.findAll());
    }

    @Override
    public void saveFieldStaff(FieldStaffDTO fieldStaffDTO) {
        Optional<FieldEntity> fieldOpt = fieldRepo.findById(fieldStaffDTO.getFieldCode());
        Optional<StaffEntity> staffOpt = staffRepo.findById(fieldStaffDTO.getStaffId());
        if(!fieldOpt.isPresent()) {
            throw new FieldNotFoundException(fieldStaffDTO.getFieldCode() + " : Field Does Not Exist");
        } else if(!staffOpt.isPresent()) {
            throw new StaffNotFoundException(fieldStaffDTO.getStaffId() + " : Staff Does Not Exist");
        }
        FieldEntity field = fieldOpt.get();
        StaffEntity staff = staffOpt.get();
        if (field.getStaffs().contains(staff)) {
            throw new DataPersistException(fieldStaffDTO.getFieldCode() + " : Field Already Have This Staff : " + fieldStaffDTO.getStaffId());
        }
        field.getStaffs().add(staff);
        staff.getFields().add(field);
        fieldRepo.save(field);
    }

    @Override
    public void deleteFieldStaff(String fieldCode, String staffId) {
        Optional<FieldEntity> fieldOpt = fieldRepo.findById(fieldCode);
        Optional<StaffEntity> staffOpt = staffRepo.findById(staffId);
        if(!fieldOpt.isPresent()) {
            throw new FieldNotFoundException(fieldCode + " : Field Does Not Exist");
        } else if(!staffOpt.isPresent()) {
            throw new StaffNotFoundException(staffId + " : Staff Does Not Exist");
        }
        FieldEntity field = fieldOpt.get();
        StaffEntity staff = staffOpt.get();
        field.getStaffs().remove(staff);
        staff.getFields().remove(field);
        fieldRepo.save(field);
    }

    @Override
    public String findLastStaffId() {
        return staffRepo.findLastStaffId();
    }

    @Override
    public StaffDTO getStaffById(String staffId) {
        return mapperUtil.mapStaffEntityToDto(staffRepo.findById(staffId).get());
    }

    @Override
    public List<StaffDTO> getFieldStaffByFieldCode(String fieldCode) {
        Optional<FieldEntity> fieldOpt = fieldRepo.findById(fieldCode);
        if(!fieldOpt.isPresent()) {
            throw new FieldNotFoundException(fieldCode + " : Field Does Not Exist");
        }
        return mapperUtil.mapStaffEntitiesToDtos(fieldOpt.get().getStaffs());
    }
}
