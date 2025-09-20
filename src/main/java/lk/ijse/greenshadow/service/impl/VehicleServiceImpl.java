package lk.ijse.greenshadow.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.greenshadow.dto.VehicleDTO;
import lk.ijse.greenshadow.exception.DataPersistException;
import lk.ijse.greenshadow.exception.StaffNotFoundException;
import lk.ijse.greenshadow.exception.VehicleNotFoundException;
import lk.ijse.greenshadow.repo.StaffRepo;
import lk.ijse.greenshadow.repo.VehicleRepo;
import lk.ijse.greenshadow.service.VehicleService;
import lk.ijse.greenshadow.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepo vehicleRepo;
    @Autowired
    private StaffRepo staffRepo;
    @Autowired
    private MapperUtil mapperUtil;
    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {
        if (vehicleRepo.existsById(vehicleDTO.getVehicleCode())) {
            throw new DataPersistException(vehicleDTO.getVehicleCode() + " : Vehicle Already Exist");
        } else if (!staffRepo.existsById(vehicleDTO.getStaffId())) {
            throw new StaffNotFoundException(vehicleDTO.getStaffId() + " : Staff Does Not Exist");
        }
        vehicleRepo.save(mapperUtil.mapVehicleDtoToEntity(vehicleDTO));
    }

    @Override
    public void updateVehicle(VehicleDTO vehicleDTO) {
        if (!vehicleRepo.existsById(vehicleDTO.getVehicleCode())) {
            throw new VehicleNotFoundException(vehicleDTO.getVehicleCode() + " : Vehicle Does Not Exist");
        } else if (!staffRepo.existsById(vehicleDTO.getStaffId())) {
            throw new StaffNotFoundException(vehicleDTO.getStaffId() + " : Staff Does Not Exist");
        }
        vehicleRepo.save(mapperUtil.mapVehicleDtoToEntity(vehicleDTO));
    }

    @Override
    public void deleteVehicle(String vehicleCode) {
        if (!vehicleRepo.existsById(vehicleCode)) {
            throw new VehicleNotFoundException(vehicleCode + " : Vehicle Does Not Exist");
        }
        vehicleRepo.deleteById(vehicleCode);
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return mapperUtil.mapVehicleEntitiesToDtos(vehicleRepo.findAll());
    }

    @Override
    public String findLastVehicleCode() {
        return vehicleRepo.findLastVehicleCode();
    }

    @Override
    public VehicleDTO getVehicleByCode(String vehicleCode) {
        return mapperUtil.mapVehicleEntityToDto(vehicleRepo.findById(vehicleCode).get());
    }
}
