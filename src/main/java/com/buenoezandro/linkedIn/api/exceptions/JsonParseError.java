package com.buenoezandro.linkedIn.api.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JsonParseError {

    private int code;
    private String status;
    private OffsetDateTime timestamp;
    private String message;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<String> errors;

}