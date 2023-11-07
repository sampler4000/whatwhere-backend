package ee.spacexy.whatwhere.service.service.dto;

import ee.spacexy.whatwhere.service.domain.enums.SubscriptionPlanType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO implements BaseDTO {
    private UUID id;
    private String title;
    private String description;
    private Boolean active;
    private Boolean deleted;
    private Boolean hidden;
    private Integer order;
    private SubscriptionPlanType planType;
}
