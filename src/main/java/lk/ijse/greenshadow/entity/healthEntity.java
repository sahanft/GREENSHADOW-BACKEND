package lk.ijse.greenshadow.entity;


        import org.hibernate.annotations.CreationTimestamp;
        import org.hibernate.annotations.UpdateTimestamp;


        import javax.persistence.*;
        import java.time.LocalDateTime;


@Entity
@Table(name = "health_record")
public class healthEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_name", nullable = false, length = 100)
    private String serviceName;


}