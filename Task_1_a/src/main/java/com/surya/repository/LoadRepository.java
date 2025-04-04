package com.surya.repository;

import com.surya.model.Load;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface LoadRepository extends JpaRepository<Load, UUID> {
    @Query("SELECT l FROM Load l WHERE " +
           "(:shipperId IS NULL OR l.shipperId = :shipperId) AND " +
           "(:truckType IS NULL OR l.truckType = :truckType)")
    Page<Load> findByShipperIdAndTruckType(
            @Param("shipperId") String shipperId,
            @Param("truckType") String truckType,
            Pageable pageable);
}
