package com.noveogroup.callspeak.service.impl;

import com.noveogroup.callspeak.model.Interval;
import com.noveogroup.callspeak.service.OutputService;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.Comparator;

/**
 * Default implementation of OutputService
 */
public class OutputServiceImpl implements OutputService {

    private static final Logger LOGGER = Logger.getLogger(OutputServiceImpl.class);

    @Override
    public void print(final Collection<Interval> peaks, final int peakAmount) {
        if (peaks.isEmpty()) {
            LOGGER.info("No peaks found. File is empty!");
        } else {
            LOGGER.info("The peak for this call log is " + peakAmount
                    + " simultaneous calls, that occurred between:");
            peaks.stream().sorted(Comparator.comparingLong(Interval::getStart)).forEach(
                    i -> LOGGER.info(i.getStart() + " and " + i.getEnd()));
        }
    }
}
