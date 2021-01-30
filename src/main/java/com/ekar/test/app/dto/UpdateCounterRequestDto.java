package com.ekar.test.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class UpdateCounterRequestDto {

    @NotNull
    @Min(1)
    @Max(99)
    private Integer counter;
}
