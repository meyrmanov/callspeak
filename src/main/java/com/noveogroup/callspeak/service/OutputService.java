package com.noveogroup.callspeak.service;

import com.noveogroup.callspeak.dto.PeakResultDTO;

/**
 * Service which outputs call peaks
 */
public interface OutputService {
    /**
     * Prints collection of peak intervals and maximum amount
     *
     * @param peakResultDTO intervals and maximum amount
     */
    void print(PeakResultDTO peakResultDTO);
}
