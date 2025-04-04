package com.surya.repository;

import com.surya.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    @Query("SELECT b FROM Booking b WHERE " +
           "(:shipperId IS NULL OR b.load.shipperId = :shipperId) OR " +
           "(:transporterId IS NULL OR b.transporterId = :transporterId)")
    Page<Booking> findByLoadShipperIdOrTransporterId(
            @Param("shipperId") String shipperId,
            @Param("transporterId") String transporterId,
            Pageable pageable);
}