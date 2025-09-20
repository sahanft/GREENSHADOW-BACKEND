package lk.ijse.greenshadow.repo;

import lk.ijse.greenshadow.entity.CropEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CropRepo extends JpaRepository<CropEntity, String> {
    @Query(value = "SELECT crop_code FROM crop WHERE crop_code LIKE 'C00%' ORDER BY CAST(SUBSTRING(crop_code, 4) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findLastCropCode();
}
