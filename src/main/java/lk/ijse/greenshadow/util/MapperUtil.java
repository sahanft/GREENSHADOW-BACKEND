package lk.ijse.greenshadow.util;

import lk.ijse.greenshadow.dto.*;
import lk.ijse.greenshadow.entity.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MapperUtil {
    @Autowired
    private ModelMapper modelMapper;
    public FieldEntity mapFieldDtoToEntity(FieldDTO fieldDTO) {
        return modelMapper.map(fieldDTO, FieldEntity.class);
    }
    public List<FieldDTO> mapFieldEntitiesToDtos(List<FieldEntity> fieldEntities) {
        return modelMapper.map(fieldEntities, new TypeToken<List<FieldDTO>>() {}.getType());
    }
    public FieldDTO mapFieldEntityToDto(FieldEntity fieldEntity) {
        return modelMapper.map(fieldEntity, FieldDTO.class);
    }

    public CropEntity mapCropDtoToEntity(CropDTO cropDTO) {
        return modelMapper.map(cropDTO, CropEntity.class);
    }

    public List<CropDTO> mapCropEntitiesToDtos(List<CropEntity> cropEntities) {
        return modelMapper.map(cropEntities, new TypeToken<List<CropDTO>>() {}.getType());
    }

    public LogEntity mapLogDtoToEntity(LogDTO logDTO) {
        return modelMapper.map(logDTO, LogEntity.class);
    }

    public List<LogDTO> mapLogEntitiesToDtos(List<LogEntity> logEntities) {
        return modelMapper.map(logEntities, new TypeToken<List<LogDTO>>() {}.getType());
    }

    public StaffEntity mapStaffDtoToEntity(StaffDTO staffDTO) {
        return modelMapper.map(staffDTO, StaffEntity.class);
    }

    public List<StaffDTO> mapStaffEntitiesToDtos(List<StaffEntity> staffEntities) {
        return modelMapper.map(staffEntities, new TypeToken<List<StaffDTO>>() {}.getType());
    }

    public VehicleEntity mapVehicleDtoToEntity(VehicleDTO vehicleDTO) {
        return modelMapper.map(vehicleDTO, VehicleEntity.class);
    }

    public EquipmentEntity mapEquipmentDtoToEntity(EquipmentDTO equipmentDTO) {
        return modelMapper.map(equipmentDTO, EquipmentEntity.class);
    }

    public List<EquipmentDTO> mapEquipmentEntitiesToDtos(List<EquipmentEntity> equipmentEntities) {
        return modelMapper.map(equipmentEntities, new TypeToken<List<EquipmentDTO>>() {}.getType());
    }

    public List<VehicleDTO> mapVehicleEntitiesToDtos(List<VehicleEntity> vehicleEntities) {
        return modelMapper.map(vehicleEntities, new TypeToken<List<VehicleDTO>>() {}.getType());
    }

    public CropDTO mapCropEntityToDto(CropEntity cropEntity) {
        return modelMapper.map(cropEntity, CropDTO.class);
    }

    public VehicleDTO mapVehicleEntityToDto(VehicleEntity vehicleEntity) {
        return modelMapper.map(vehicleEntity, VehicleDTO.class);
    }

    public StaffDTO mapStaffEntityToDto(StaffEntity staffEntity) {
        return modelMapper.map(staffEntity, StaffDTO.class);
    }

    public EquipmentDTO mapEquipmentEntityToDto(EquipmentEntity equipmentEntity) {
        return modelMapper.map(equipmentEntity, EquipmentDTO.class);
    }

    public UserEntity mapUserDTOtoUserEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }

    public List<UserDTO> mapUserEntitiesToDtos(List<UserEntity> allUsers) {
        return modelMapper.map(allUsers, new TypeToken<List<UserDTO>>() {}.getType());
    }

    public UserStatus mapUserEntityToUserDTO(UserEntity selectedUser) {
        return modelMapper.map(selectedUser, UserStatus.class);
    }
}
