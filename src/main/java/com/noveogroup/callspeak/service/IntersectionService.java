package com.noveogroup.callspeak.service;

import com.noveogroup.callspeak.dto.PeakResultDTO;
import com.noveogroup.callspeak.model.Interval;

/**
 * Service to calculate calls intersections to detect peaks
 */
public interface IntersectionService {

    /**
     * Recalculates intersections and peaks with new interval
     *
     * @param interval new interval
     */
    void addInterval(Interval interval);

    /**
     * @return DTO which contains interval with maximum amount of the calls and max value
     */
    PeakResultDTO getPeakResult();
}
