package lk.ijse.greenshadow.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.greenshadow.customStatusCodes.SelectedUserErrorStatus;
import lk.ijse.greenshadow.dto.UserDTO;
import lk.ijse.greenshadow.dto.UserStatus;
import lk.ijse.greenshadow.entity.UserEntity;
import lk.ijse.greenshadow.exception.DataPersistException;
import lk.ijse.greenshadow.exception.UserNotFoundException;
import lk.ijse.greenshadow.repo.UserRepo;
import lk.ijse.greenshadow.service.UserService;
import lk.ijse.greenshadow.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userDAO;

    @Autowired
    private MapperUtil mapping;

    @Override
    public void saveUser(UserDTO userDTO) {
        UserEntity userEntity = mapping.mapUserDTOtoUserEntity(userDTO);
        userEntity.setUserId(userDTO.getUserId());
        UserEntity saveUser = userDAO.save(userEntity);
        if(saveUser == null) {
            throw new DataPersistException("User Not Saved");
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> allUsers = userDAO.findAll();
        return mapping.mapUserEntitiesToDtos(allUsers);
    }

    @Override
    public UserStatus getUser(String userId) {
        if(userDAO.existsById(userId)){
            UserEntity selectedUser = userDAO.getReferenceById(userId);
            return mapping.mapUserEntityToUserDTO(selectedUser);
        }else {
            return new SelectedUserErrorStatus(2, "User with id " + userId + " not found");
        }
    }

    @Override
    public void deleteUser(String userId) {
        Optional<UserEntity> existedUser = userDAO.findById(userId);
        if(!existedUser.isPresent()){
            throw new UserNotFoundException("User with id " + userId + " not found");
        }else {
            userDAO.deleteById(userId);
        }
    }
    @Override
    public void updateUser(String userId, UserDTO userDTO) {
        Optional <UserEntity> tempUser = userDAO.findById(userId);
        if(tempUser.isPresent()) {
            tempUser.get().setEmail(userDTO.getEmail());
            tempUser.get().setPassword(userDTO.getPassword());
        }
    }

    @Override
    public UserDetailsService userDetailsService() {
        return username ->
                userDAO.findByEmail(username)
                        .orElseThrow(() -> new UserNotFoundException("User not found"));
    }
}
