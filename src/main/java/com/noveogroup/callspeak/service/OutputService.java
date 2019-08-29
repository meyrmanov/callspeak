package com.noveogroup.callspeak.service;

import com.noveogroup.callspeak.model.Interval;

import java.util.Collection;

/**
 * Service which outputs call peaks
 */
public interface OutputService {
    /**
     * Prints collection of peak intervals and maximum amount
     * @param peaks Collection of intervals
     * @param peakAmount Amount of calls
     */
    void print(Collection<Interval> peaks, int peakAmount);
}
