package ee.spacexy.whatwhere.service.domain;

import ee.spacexy.whatwhere.service.domain.enums.SubscriptionPlanType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "category")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Category extends AuditableEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "clob")
    private String description;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "hidden")
    private Boolean hidden;

    @Column(name = "\"order\"")
    private Integer order;

    @Enumerated(EnumType.STRING)
    @Column(name = "plan_type")
    private SubscriptionPlanType planType;

}
