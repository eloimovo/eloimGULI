package com.eloim.servicebase.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuLiException extends RuntimeException{
    @ApiModelProperty(value = "状态码")
    private Integer code;
    private String msg;
}
