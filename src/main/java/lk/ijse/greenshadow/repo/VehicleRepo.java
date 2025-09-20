package lk.ijse.greenshadow.repo;

import lk.ijse.greenshadow.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VehicleRepo extends JpaRepository<VehicleEntity, String> {
    @Query(value = "SELECT vehicle_code FROM vehicle WHERE vehicle_code LIKE 'V00%' ORDER BY CAST(SUBSTRING(vehicle_code, 4) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findLastVehicleCode();
}
