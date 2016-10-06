package com.rsh.project.security.domain;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by fun-redoc on 06.10.16.
 */
@Entity
@Table(name="roles")
@SuppressWarnings("unused")
//@Data
@Getter @Setter
@ToString(exclude = {"users"})
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
    private Set<User> users = new HashSet<>();

}
