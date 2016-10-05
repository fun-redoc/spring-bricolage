package com.rsh.project.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.List;

/**
 * Created by fun-redoc on 01.10.16.
 */
@Entity
//@Getter
//@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString(exclude = "projectPersonList")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Builder
public class Project {

    @Id
    @GeneratedValue
    @Getter @Setter private Long id;

    @NotNull
    @Getter @Setter private String name;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd.M.yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Getter @Setter private Calendar startDate;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd.M.yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Getter @Setter private Calendar endDate;

    @Column(columnDefinition = "TEXT")
	@Getter @Setter private String description;

    //@JsonIgnore
    //@JsonBackReference
    //@OneToMany(mappedBy="project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OneToMany(mappedBy="project")
    @Getter private List<ProjectPerson> projectPersonList;
    public void setProjectPersonList(List<ProjectPerson> projectPersonList) {
        this.projectPersonList.clear();
        this.projectPersonList.addAll(projectPersonList);
    }
}
