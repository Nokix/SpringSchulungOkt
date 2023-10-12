package gmbh.conteco.SpringSchulungOkt.jpa;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.experimental.Accessors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Accessors(chain = true)
@Setter
@Getter
public class Student {
    @Id
    @GeneratedValue
    Long id;

    @NotBlank
    String name;

    @Email
    String email;
}
