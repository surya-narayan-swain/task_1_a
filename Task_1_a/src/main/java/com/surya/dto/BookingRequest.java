package com.surya.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.UUID;

@Data
public class BookingRequest {
    @NotNull(message = "Load ID is required")
    private UUID loadId;
    
    @NotBlank(message = "Transporter ID is required")
    private String transporterId;
    
    @Positive(message = "Proposed rate must be positive")
    private double proposedRate;
    
    private String comment;
}