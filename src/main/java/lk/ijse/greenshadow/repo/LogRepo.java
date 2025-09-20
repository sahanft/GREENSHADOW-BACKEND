package lk.ijse.greenshadow.repo;

import lk.ijse.greenshadow.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LogRepo extends JpaRepository<LogEntity, String> {
    @Query(value = "SELECT log_code FROM log WHERE log_code LIKE 'L00%' ORDER BY CAST(SUBSTRING(log_code, 4) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findLastLogCode();
}
