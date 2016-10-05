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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Embeddable
@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class Period {
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd.M.yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull
    private Calendar start;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd.M.yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull
    private Calendar end;

    @Min(1)
    @Max(100)
    @NotNull
    private Long percentage;
}
