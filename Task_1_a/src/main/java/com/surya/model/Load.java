package com.surya.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Load {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @NotBlank(message = "Shipper ID is required")
    private String shipperId;
    
    @Embedded
    @NotNull(message = "Facility details are required")
    private Facility facility;
    
    @NotBlank(message = "Product type is required")
    private String productType;
    
    @NotBlank(message = "Truck type is required")
    private String truckType;
    
    @Positive(message = "Number of trucks must be positive")
    private int noOfTrucks;
    
    @Positive(message = "Weight must be positive")
    private double weight;
    
    private String comment;
    
    @CreationTimestamp
    private LocalDateTime datePosted;
    
    @Enumerated(EnumType.STRING)
    private LoadStatus status = LoadStatus.POSTED;
}