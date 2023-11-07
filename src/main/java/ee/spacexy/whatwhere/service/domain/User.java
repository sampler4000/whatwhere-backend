package ee.spacexy.whatwhere.service.domain;

import ee.spacexy.whatwhere.service.service.converter.StringListConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(length = 50, nullable = false, unique = true)
    private String login;

    @Size(max = 50)
    @Column(length = 50)
    private String firstName;

    @Size(max = 50)
    @Column(length = 50)
    private String lastName;

    @Size(max = 100)
    @Column(length = 100)
    private String fullName;

    @Email
    @Size(min = 5, max = 254)
    @Column(length = 254, unique = true)
    private String email;

    @Size(max = 256)
    @Column(length = 256)
    private String imageUrl;

    @Column(nullable = false)
    private boolean activated = false;

    @Size(min = 2, max = 10)
    @Column(length = 10)
    private String langKey;

    @Column(name = "authorities")
    @Convert(converter = StringListConverter.class)
    private List<String> authorities;
}
