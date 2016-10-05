package com.rsh.project.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by fun-redoc on 01.10.16.
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Builder
public class ProjectPerson {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @NotNull
    //@JsonManagedReference
    //@JsonIgnore
    private Project project;

    @ManyToOne
    @NotNull
    //@JsonManagedReference
    private Person person;

    @ElementCollection
    @CollectionTable(
            name="PERIOD",
            joinColumns=@JoinColumn(name="PROJECT_USER_ID")
    )
    @Size(min=1)
    private List<Period> periods;
}

