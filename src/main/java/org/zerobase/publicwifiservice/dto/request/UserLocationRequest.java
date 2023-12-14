package org.zerobase.publicwifiservice.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserLocationRequest{
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
}
