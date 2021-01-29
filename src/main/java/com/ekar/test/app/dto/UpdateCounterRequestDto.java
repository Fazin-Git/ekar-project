package com.ekar.test.app.dto;

import lombok.*;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Builder
public class UpdateCounterRequestDto implements Serializable {

    @Size(min = 1,max = 99)
    private Integer counter;
}
