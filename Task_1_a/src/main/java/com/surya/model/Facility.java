package com.surya.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDateTime;

@Embeddable
@Data
public class Facility {
    @NotBlank(message = "Loading point is required")
    private String loadingPoint;
    
    @NotBlank(message = "Unloading point is required")
    private String unloadingPoint;
    
    private LocalDateTime loadingDate;
    private LocalDateTime unloadingDate;
}