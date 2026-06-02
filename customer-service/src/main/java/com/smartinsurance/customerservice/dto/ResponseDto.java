package com.smartinsurance.customerservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name="Resposne",
description = "Schema to hold response informaiton")
public class ResponseDto {
    @Schema(
            description = "Status code in the response"
    )
    private String statusCode;
    @Schema(
            description = "Status message in the response"
    )
    private String statusMessage;
}
