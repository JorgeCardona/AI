package jorge.cardona.concepts.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "nature")
@Entity
public class Nature {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "kingdom")
    private String kingdom;

    @NotNull
    @Type(type="text")
    @Column(name = "description")
    private String description;

    @ColumnDefault("now()")
    @Type(type="timestamp")
    @CreationTimestamp
    @Column(name = "createDateTime")
    private Timestamp createDateTime;

    @ColumnDefault("now()")
    @Type(type="timestamp")
    @UpdateTimestamp
    @Column(name = "updateDateTime")
    private Timestamp updateDateTime;

}