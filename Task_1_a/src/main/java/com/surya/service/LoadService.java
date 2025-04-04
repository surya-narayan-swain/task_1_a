package com.surya.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.surya.dto.FacilityRequest;
import com.surya.dto.LoadRequest;
import com.surya.exception.ResourceNotFoundException;
import com.surya.model.Facility;
import com.surya.model.Load;
import com.surya.model.LoadStatus;
import com.surya.repository.LoadRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoadService {
    private final LoadRepository loadRepository;

    @Transactional
    public Load createLoad(LoadRequest request) {
        Load load = new Load();
        // Map fields from request to entity
        load.setShipperId(request.getShipperId());
        load.setFacility(convertToFacilityEntity(request.getFacility()));
        load.setProductType(request.getProductType());
        load.setTruckType(request.getTruckType());
        load.setNoOfTrucks(request.getNoOfTrucks());
        load.setWeight(request.getWeight());
        load.setComment(request.getComment());
        
        return loadRepository.save(load);
    }

    public Page<Load> getFilteredLoads(String shipperId, String truckType, Pageable pageable) {
        return loadRepository.findByShipperIdAndTruckType(shipperId, truckType, pageable);
    }

    public Load getLoadById(UUID loadId) {
        return loadRepository.findById(loadId)
                .orElseThrow(() -> new ResourceNotFoundException("Load not found with ID: " + loadId));
    }

    @Transactional
    public Load updateLoad(UUID loadId, LoadRequest request) {
        Load load = getLoadById(loadId);
        // Update fields
        load.setShipperId(request.getShipperId());
        load.setFacility(convertToFacilityEntity(request.getFacility()));
        load.setProductType(request.getProductType());
        load.setTruckType(request.getTruckType());
        load.setNoOfTrucks(request.getNoOfTrucks());
        load.setWeight(request.getWeight());
        load.setComment(request.getComment());
        
        return loadRepository.save(load);
    }

    @Transactional
    public void deleteLoad(UUID loadId) {
        Load load = getLoadById(loadId);
        load.setStatus(LoadStatus.CANCELLED);
        loadRepository.save(load);
    }

    private Facility convertToFacilityEntity(FacilityRequest facilityRequest) {
        if (facilityRequest == null) {
            return null;
        }
        
        Facility facility = new Facility();
        facility.setLoadingPoint(facilityRequest.getLoadingPoint());
        facility.setUnloadingPoint(facilityRequest.getUnloadingPoint());
        facility.setLoadingDate(facilityRequest.getLoadingDate());
        facility.setUnloadingDate(facilityRequest.getUnloadingDate());
        return facility;
    }
}