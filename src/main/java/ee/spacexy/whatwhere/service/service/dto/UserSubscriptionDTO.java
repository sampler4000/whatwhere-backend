package ee.spacexy.whatwhere.service.service.dto;

import ee.spacexy.whatwhere.service.domain.enums.SubscriptionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSubscriptionDTO implements BaseDTO {
    private UUID id;
    private UserDTO user;
    private SubscriptionPlanDTO subscriptionPlan;
    private SubscriptionStatus status;
    private ZonedDateTime validFrom;
    private ZonedDateTime validTo;
}
