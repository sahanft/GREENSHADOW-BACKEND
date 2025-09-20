package lk.ijse.greenshadow.repo;

import lk.ijse.greenshadow.entity.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EquipmentRepo extends JpaRepository<EquipmentEntity, String> {
    @Query(value = "SELECT equipment_id FROM equipment WHERE equipment_id LIKE 'E00%' ORDER BY CAST(SUBSTRING(equipment_id, 4) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findLastEquipmentId();
}
