package com.noveogroup.callspeak.dto;

import com.noveogroup.callspeak.model.Interval;

import java.util.Collection;

/**
 * DTO which keeps calculation results
 */
public class PeakResultDTO {

    private Collection<Interval> peaks;

    private int max;

    public PeakResultDTO(final Collection<Interval> peaks, final int max) {
        this.peaks = peaks;
        this.max = max;
    }

    /**
     * @return intervals with maximum amount of the calls
     */
    public Collection<Interval> getPeaks() {
        return peaks;
    }

    /**
     * @return maximum amount of the calls
     */
    public int getMax() {
        return max;
    }
}
