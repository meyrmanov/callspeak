package com.noveogroup.callspeak.service.impl;

import com.noveogroup.callspeak.dto.PeakResultDTO;
import com.noveogroup.callspeak.model.Interval;
import com.noveogroup.callspeak.service.OutputService;
import org.apache.log4j.Logger;

import java.util.Comparator;

/**
 * Default implementation of OutputService
 */
public class OutputServiceImpl implements OutputService {

    private static final Logger LOGGER = Logger.getLogger(OutputServiceImpl.class);

    @Override
    public void print(final PeakResultDTO peakResultDTO) {
        if (peakResultDTO.getPeaks().isEmpty()) {
            LOGGER.info("No peaks found. File is empty!");
        } else {
            LOGGER.info("The peak for this call log is " + peakResultDTO.getMax()
                    + " simultaneous calls, that occurred between:");
            peakResultDTO.getPeaks().stream().sorted(Comparator.comparingLong(Interval::getStart)).forEach(
                    i -> LOGGER.info(i.getStart() + " and " + i.getEnd()));
        }
    }
}
