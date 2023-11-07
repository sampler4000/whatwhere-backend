package ee.spacexy.whatwhere.service.domain;

import ee.spacexy.whatwhere.service.domain.enums.LayerType;
import ee.spacexy.whatwhere.service.domain.enums.SubscriptionPlanType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "layer")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Layer extends AuditableEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "schema")
    private String schema;

    @Column(name = "description", columnDefinition = "clob")
    private String description;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private LayerType type;

    @Column(name = "url", columnDefinition = "clob")
    private String url;

    @Column(name = "attribution", columnDefinition = "clob")
    private String attribution;

    @Column(name = "legend_url", columnDefinition = "clob")
    private String legendUrl;

    @Column(name = "thumbnail_url", columnDefinition = "clob")
    private String thumbnailUrl;

    @Column(name = "base_layer")
    private Boolean baseLayer;

    @Column(name = "params", columnDefinition = "clob")
    private String params;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

}
