package lk.ijse.greenshadow.controller;

import lk.ijse.greenshadow.dto.FieldCropDTO;
import lk.ijse.greenshadow.dto.FieldDTO;
import lk.ijse.greenshadow.service.FieldService;
import lk.ijse.greenshadow.util.AppUtil;
import lk.ijse.greenshadow.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/field")
public class FieldController {
    @Autowired
    FieldService fieldService;
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveField(@RequestPart("fieldCode") String fieldCode,
                                    @RequestPart("name") String name,
                                    @RequestPart("location") String location,
                                    @RequestPart("size") String size,
                                    @RequestPart("imageOne") MultipartFile imageOne,
                                    @RequestPart("imageTwo") MultipartFile imageTwo) {
        try {
            byte[] imageOneBytes = imageOne.getBytes();
            byte[] imageTwoBytes = imageTwo.getBytes();
            String imageOneBase64 = AppUtil.imageToBase64(imageOneBytes);
            String imageTwoBase64 = AppUtil.imageToBase64(imageTwoBytes);

            fieldService.saveField(new FieldDTO(fieldCode, name, location, size, imageOneBase64, imageTwoBase64));
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateField(@RequestPart("fieldCode") String fieldCode,
                                  @RequestPart("name") String name,
                                  @RequestPart("location") String location,
                                  @RequestPart("size") String size,
                                  @RequestPart("imageOne") MultipartFile imageOne,
                                  @RequestPart("imageTwo") MultipartFile imageTwo) {
        try {
            byte[] imageOneBytes = imageOne.getBytes();
            byte[] imageTwoBytes = imageTwo.getBytes();
            String imageOneBase64 = AppUtil.imageToBase64(imageOneBytes);
            String imageTwoBase64 = AppUtil.imageToBase64(imageTwoBytes);

            fieldService.updateField(new FieldDTO(fieldCode, name, location, size, imageOneBase64, imageTwoBase64));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    @DeleteMapping("/{fieldCode}")
    public ResponseEntity<Void> deleteField(@PathVariable("fieldCode") String fieldCode) {
        fieldService.deleteField(fieldCode);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST') or hasRole('ADMINISTRATIVE')")
    @GetMapping
    public ResponseUtil getAllFields() {
        return new ResponseUtil("Success", "Retrieved All Fields", fieldService.getAllFields());
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    @PostMapping(value = "/fieldcrops" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveFieldCrops(@RequestBody FieldCropDTO fieldCropDTO) {
        fieldService.saveFieldCrops(fieldCropDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    @DeleteMapping("/fieldcrops")
    public ResponseEntity<Void> deleteFieldCrops(@RequestParam("fieldCode") String fieldCode, @RequestParam("cropCode") String cropCode) {
        fieldService.deleteFieldCrops(fieldCode, cropCode);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    @GetMapping("/nextcode")
    public ResponseUtil getNewFieldCode() {
        String newFieldCode = AppUtil.generateFieldCode(fieldService.findLastFieldCode());
        return new ResponseUtil("Success", "Retrieved New Field Code", newFieldCode);
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST') or hasRole('ADMINISTRATIVE')")
    @GetMapping("/{fieldCode}")
    public ResponseUtil getFieldByCode(@PathVariable("fieldCode") String fieldCode) {
        return new ResponseUtil("Success", "Retrieved Field", fieldService.getFieldByCode(fieldCode));
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST') or hasRole('ADMINISTRATIVE')")
    @GetMapping("/fieldcrops/{fieldCode}")
    public ResponseUtil getFieldCropsByFieldCode(@PathVariable("fieldCode") String fieldCode) {
        return new ResponseUtil("Success", "Retrieved Field Crops", fieldService.getFieldCropsByFieldCode(fieldCode));
    }
}
