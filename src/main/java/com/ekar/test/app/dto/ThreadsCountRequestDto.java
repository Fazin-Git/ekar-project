package com.ekar.test.app.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class ThreadsCountRequestDto {

    @NotNull
    @ApiModelProperty("Number of producer threads")
    private Integer producerCount;

    @NotNull
    @ApiModelProperty("Number of consumer threads")
    private Integer consumerCount;
}
