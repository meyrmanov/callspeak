package com.noveogroup.callspeak.service;

import com.noveogroup.callspeak.model.Interval;

import java.util.Collection;

/**
 * Service to calculate calls intersections to detect peaks
 */
public interface IntersectionService {

    /**
     * Recalculates intersections and peaks with new interval
     * @param interval new interval
     */
    void addInterval(Interval interval);

    /**
     * Initialize service. Must be called before new calculation.
     */
    void start();

    /**
     * Finalize calculations.
     */
    void finish();

    /**
     * @return intervals with maximum calls amount
     */
    Collection<Interval> getPeaks();

    /**
     * @return maximum calls amount
     */
    int getPeakAmount();
}
