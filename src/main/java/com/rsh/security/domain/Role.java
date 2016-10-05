package com.rsh.security.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by fun-redoc on 06.10.16.
 */
@Entity
@Table(name="roles")
@SuppressWarnings("unused")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    @NotNull
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

}
