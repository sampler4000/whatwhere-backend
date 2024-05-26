package ee.spacexy.whatwhere.service.service.dto;

import ee.spacexy.whatwhere.service.domain.enums.SubscriptionPlanType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FirebaseCategoryDTO implements BaseDTO {
    private UUID id;
    private String title;
    private String description;
    private Integer order;
    private SubscriptionPlanType planType;
    private Boolean checked = false;
    private List<FirebaseLayerDTO> layers;
}
