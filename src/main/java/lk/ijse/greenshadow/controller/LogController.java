package lk.ijse.greenshadow.controller;

import lk.ijse.greenshadow.dto.CropDTO;
import lk.ijse.greenshadow.dto.FieldDTO;
import lk.ijse.greenshadow.dto.LogDTO;
import lk.ijse.greenshadow.dto.StaffDTO;
import lk.ijse.greenshadow.exception.DataPersistException;
import lk.ijse.greenshadow.service.LogService;
import lk.ijse.greenshadow.util.AppUtil;
import lk.ijse.greenshadow.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired
    LogService logService;

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveLog(@RequestParam("logCode") String logCode,
                                        @RequestParam("date") String date,
                                        @RequestParam("details") String details,
                                        @RequestParam("image") MultipartFile image,
                                        @RequestParam("logFields")List<String> logFields,
                                        @RequestParam("logCrops")List<String> logCrops,
                                        @RequestParam("logStaff")List<String> logStaff) {
        try {
            byte[] imageBytes = image.getBytes();
            String imageBase64 = AppUtil.imageToBase64(imageBytes);
            List<FieldDTO> fieldDTOS = new ArrayList<>();
            for (String logField : logFields) {
                FieldDTO fieldDTO = new FieldDTO();
                fieldDTO.setFieldCode(logField);
                fieldDTOS.add(fieldDTO);
            }
            List<CropDTO> cropDTOS = new ArrayList<>();
            for (String logCrop : logCrops) {
                CropDTO cropDTO = new CropDTO();
                cropDTO.setCropCode(logCrop);
                cropDTOS.add(cropDTO);
            }
            List<StaffDTO> staffDTOS = new ArrayList<>();
            for (String logStaff1 : logStaff) {
                StaffDTO staffDTO = new StaffDTO();
                staffDTO.setStaffId(logStaff1);
                staffDTOS.add(staffDTO);
            }
            System.out.println(fieldDTOS);
            logService.saveLog(new LogDTO(logCode, date, details, imageBase64, fieldDTOS, cropDTOS, staffDTOS));
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (DataPersistException e) {
            throw new DataPersistException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST') or hasRole('ADMINISTRATIVE')")
    @GetMapping
    public ResponseUtil getAllLogs() {
        return new ResponseUtil("Success", "Retrieved All Logs", logService.getAllLogs());
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    @DeleteMapping("/{logCode}")
    public ResponseEntity<Void> deleteLog(@PathVariable("logCode") String logCode) {
        logService.deleteLog(logCode);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST') or hasRole('ADMINISTRATIVE')")
    @GetMapping("/nextcode")
    public ResponseUtil getNewLogCode() {
        String newLogCode = AppUtil.generateLogCode(logService.findLastLogCode());
        return new ResponseUtil("Success", "Retrieved New Log Code", newLogCode);
    }
}
