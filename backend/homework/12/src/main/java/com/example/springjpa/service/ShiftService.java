package com.example.springjpa.service;

import com.example.springjpa.entity.Shifts;
import com.example.springjpa.exception.custom.MyCustomException;
import com.example.springjpa.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Service class for managing shift-related operations.x
 */
@Service
public class ShiftService {

    private final ShiftRepository shiftRepository;

    @Autowired
    public ShiftService(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    @Transactional
    public void saveShift(Shifts shift) {
        try {
            shiftRepository.save(shift);
        } catch (Exception e) {
            throw new MyCustomException("Failed to save shift.");
        }
    }

    public List<Shifts> getShifts(UUID tenantId) {
        return shiftRepository.findByTenantId(tenantId);
    }

    public List<Shifts> findTop3ShiftsByDateRange(LocalDate dateStart, LocalDate dateEnd) {
        Pageable pageable = PageRequest.of(0, 3); // Limiting to 3 results
        return shiftRepository.findTop3Shifts(dateStart, dateEnd,pageable);
    }
}
