package com.surya.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class LoadRequest {
    @NotBlank(message = "Shipper ID is required")
    private String shipperId;
    
    @Valid
    @NotNull(message = "Facility details are required")
    private FacilityRequest facility;
    
    @NotBlank(message = "Product type is required")
    private String productType;
    
    @NotBlank(message = "Truck type is required")
    private String truckType;
    
    @Positive(message = "Number of trucks must be positive")
    private int noOfTrucks;
    
    @Positive(message = "Weight must be positive")
    private double weight;
    
    private String comment;
}