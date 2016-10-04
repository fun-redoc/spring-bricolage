package com.rsh.project.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;

@Embeddable
@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class Period {
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "M.yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull
    private Calendar start;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "M.yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull
    private Calendar end;

    @Size(min = 1, max = 100)
    @NotNull
    private Integer percentage;
}
