package ee.spacexy.whatwhere.service.service.dto;

import ee.spacexy.whatwhere.service.domain.enums.Country;
import ee.spacexy.whatwhere.service.domain.enums.SubscriptionPlanType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionPlanDTO implements BaseDTO {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean monthly;
    private Boolean annual;
    private String currency;
    private SubscriptionPlanType type;
    private String status;
    private Country country;
    private String language;
    private Boolean active;
    private Boolean deleted;
    private Boolean hidden;
    private Boolean trial;
    private Integer trialDays;
    private ZonedDateTime validFrom;
    private ZonedDateTime validTo;
}
