package jorge.cardona.concepts.entity;

import jorge.cardona.concepts.annotations.properties.PropertyAnnotationValidationLengthDescription;
import jorge.cardona.concepts.annotations.methods.AnotationValidateContentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "nature")
@Entity
public class NatureEntity {

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
    @Size(min = 3, max = 10)
    @AnotationValidateContentType
    @NotBlank(message = "kingdom is mandatory")
    @Pattern(regexp = "^[\\p{Alnum}]{1,32}$")
    private String kingdom;

    @NotNull
    @AnotationValidateContentType(message = "the message was changed from original and the content must be other characters differents of numbers")
    @PropertyAnnotationValidationLengthDescription
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

    @ColumnDefault("1")
    @Column(name = "rating")
    @Positive
    private int rating;

}