package lk.ijse.greenshadow.repo;

import lk.ijse.greenshadow.entity.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FieldRepo extends JpaRepository<FieldEntity, String> {
    @Query(value = "SELECT field_code FROM field WHERE field_code LIKE 'F00%' ORDER BY CAST(SUBSTRING(field_code, 4) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String getLastFieldCode();
}
