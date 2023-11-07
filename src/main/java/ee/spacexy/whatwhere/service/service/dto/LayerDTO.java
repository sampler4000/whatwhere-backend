package ee.spacexy.whatwhere.service.service.dto;

import ee.spacexy.whatwhere.service.domain.enums.LayerType;
import ee.spacexy.whatwhere.service.domain.enums.SubscriptionPlanType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LayerDTO implements BaseDTO {
    private UUID id;
    private String name;
    private String title;
    private String schema;
    private String description;
    private LayerType type;
    private String url;
    private String attribution;
    private String legendUrl;
    private String thumbnailUrl;
    private Boolean baseLayer;
    private Object params;
    private Boolean active;
    private Boolean deleted;
    private Boolean hidden;
    private Integer order;
    private SubscriptionPlanType planType;
    private CategoryDTO category;
}
