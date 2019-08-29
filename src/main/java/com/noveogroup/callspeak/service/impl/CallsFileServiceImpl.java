package com.noveogroup.callspeak.service.impl;

import com.noveogroup.callspeak.exception.CallsFileException;
import com.noveogroup.callspeak.model.Interval;
import com.noveogroup.callspeak.service.CallsFileService;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Default implementation of CallsFileService
 */
public class CallsFileServiceImpl implements CallsFileService {

    private static final Logger LOGGER = Logger.getLogger(CallsFileServiceImpl.class);

    private static final String INTERVAL_DELIMITER = ":";

    public void iterateLines(final String fileName, final Consumer<Interval> function) throws CallsFileException {
        try (Stream<Interval> stream = Files.lines(Paths.get(fileName)).sorted().map(this::parseLine)) {
            stream.forEach(function);
        } catch (IOException e) {
            LOGGER.error("Couldn't read from file!");
            throw new CallsFileException();
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            LOGGER.error("File format is not correct!");
            throw new CallsFileException();
        }
    }

    /**
     * Converts line in the following format "start:end" to {@link com.noveogroup.callspeak.model.Interval}
     * @param line String representation of interval
     * @return new interval
     */
    private Interval parseLine(final String line) {
        String[] period = line.split(INTERVAL_DELIMITER);
        long start = Long.parseLong(period[0]);
        long end = Long.parseLong(period[1]);
        return new Interval(start, end);
    }
}
