package lk.ijse.greenshadow.service;

import lk.ijse.greenshadow.dto.LogDTO;

import java.util.List;

public interface LogService {
    void saveLog(LogDTO logDTO);
    List<LogDTO> getAllLogs();
    void deleteLog(String logCode);
    String findLastLogCode();
}
