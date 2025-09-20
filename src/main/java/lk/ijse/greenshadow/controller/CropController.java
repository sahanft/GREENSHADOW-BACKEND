package lk.ijse.greenshadow.controller;

import lk.ijse.greenshadow.dto.CropDTO;
import lk.ijse.greenshadow.service.CropService;
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
@RequestMapping("/crop")
public class CropController {
    @Autowired
    CropService cropService;
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveCrop(@RequestPart("cropCode") String cropCode,
                                   @RequestPart("commonName") String commonName,
                                   @RequestPart("scientificName") String scientificName,
                                   @RequestPart("category") String category,
                                   @RequestPart("season") String season,
                                   @RequestPart("image") MultipartFile image) {
        try {
            byte[] imageBytes = image.getBytes();
            String imageBase64 = AppUtil.imageToBase64(imageBytes);
            cropService.saveFieldCrops(new CropDTO(cropCode, commonName, scientificName, category, season, imageBase64));
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateCrop(@RequestPart("cropCode") String cropCode,
                                   @RequestPart("commonName") String commonName,
                                   @RequestPart("scientificName") String scientificName,
                                   @RequestPart("category") String category,
                                   @RequestPart("season") String season,
                                   @RequestPart("image") MultipartFile image) {
        try {
            byte[] imageBytes = image.getBytes();
            String imageBase64 = AppUtil.imageToBase64(imageBytes);
            cropService.updateFieldCrops(new CropDTO(cropCode, commonName, scientificName, category, season, imageBase64));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    @DeleteMapping("/{cropCode}")
    public ResponseEntity<Void> deleteCrop(@PathVariable("cropCode") String cropCode) {
        cropService.deleteCrop(cropCode);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST') or hasRole('ADMINISTRATIVE')")
    @GetMapping
    public ResponseUtil getAllCrops() {
        return new ResponseUtil("Success", "Retrieved All Crops", cropService.getAllCrops());
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST') ")
    @GetMapping("/nextcode")
    public ResponseUtil getNewCropCode() {
        String newCropCode = AppUtil.generateCropCode(cropService.findLastCropCode());
        return new ResponseUtil("Success", "Retrieved New Crop Code", newCropCode);
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST') or hasRole('ADMINISTRATIVE')")
    @GetMapping("/{cropCode}")
    public ResponseUtil getCropByCode(@PathVariable("cropCode") String cropCode) {
        return new ResponseUtil("Success", "Retrieved Crop", cropService.getCropByCode(cropCode));
    }
}
