package com.rsh.security.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rsh.security.domain.Role;
import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by fun-redoc on 06.10.16.
 */
@Entity
@Table(name="users")
@SuppressWarnings("unused")
@Data
@ToString(exclude="password")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    @NotNull
    @NotEmpty
    @JsonIgnore
    private String password;

    @NotEmpty
    @NotNull
    @Email
    private String email;

    @NotEmpty
    @NotNull
    private String fullName;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable( name = "users_roles",
                joinColumns = {@JoinColumn(name="user_id")},
                inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> roles;

}
