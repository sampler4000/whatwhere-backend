package ee.spacexy.whatwhere.service.service.dto;

import ee.spacexy.whatwhere.service.domain.enums.LayerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FirebaseLayerDTO implements BaseDTO {
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
    private Integer order;
    private Boolean visible = false;
}
