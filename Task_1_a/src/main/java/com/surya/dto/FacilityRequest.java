package com.surya.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class FacilityRequest {
    @NotBlank
    private String loadingPoint;
    
    @NotBlank
    private String unloadingPoint;
    
    private LocalDateTime loadingDate;
    private LocalDateTime unloadingDate;
}