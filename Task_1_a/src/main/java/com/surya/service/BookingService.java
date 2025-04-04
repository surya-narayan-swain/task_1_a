package com.surya.service;

import com.surya.dto.BookingRequest;
import com.surya.exception.BusinessException;
import com.surya.exception.ResourceNotFoundException;
import com.surya.model.*;
import com.surya.repository.BookingRepository;
import com.surya.repository.LoadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final LoadRepository loadRepository;

    @Transactional
    public Booking createBooking(BookingRequest request) {
        Load load = loadRepository.findById(request.getLoadId())
                .orElseThrow(() -> new ResourceNotFoundException("Load not found with ID: " + request.getLoadId()));

        if (load.getStatus() == LoadStatus.CANCELLED) {
            throw new BusinessException("Cannot create booking for cancelled load");
        }

        Booking booking = new Booking();
        booking.setLoad(load);
        booking.setTransporterId(request.getTransporterId());
        booking.setProposedRate(request.getProposedRate());
        booking.setComment(request.getComment());

        load.setStatus(LoadStatus.BOOKED);
        loadRepository.save(load);

        return bookingRepository.save(booking);
    }

    public Page<Booking> getFilteredBookings(String shipperId, String transporterId, Pageable pageable) {
        return bookingRepository.findByLoadShipperIdOrTransporterId(shipperId, transporterId, pageable);
    }

    public Booking getBookingById(UUID bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with ID: " + bookingId));
    }

    @Transactional
    public Booking acceptBooking(UUID bookingId) {
        Booking booking = getBookingById(bookingId);
        booking.setStatus(BookingStatus.ACCEPTED);
        return bookingRepository.save(booking);
    }

    @Transactional
    public void deleteBooking(UUID bookingId) {
        Booking booking = getBookingById(bookingId);
        booking.getLoad().setStatus(LoadStatus.CANCELLED);
        loadRepository.save(booking.getLoad());
        bookingRepository.delete(booking);
    }
}