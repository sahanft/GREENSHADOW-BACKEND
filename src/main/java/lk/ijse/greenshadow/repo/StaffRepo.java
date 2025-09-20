package lk.ijse.greenshadow.repo;

import lk.ijse.greenshadow.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StaffRepo extends JpaRepository<StaffEntity, String> {
    @Query(value = "SELECT staff_id FROM staff WHERE staff_id LIKE 'S00%' ORDER BY CAST(SUBSTRING(staff_id, 4) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findLastStaffId();
}
