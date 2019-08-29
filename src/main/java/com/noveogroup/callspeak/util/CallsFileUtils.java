package com.noveogroup.callspeak.util;

import com.noveogroup.callspeak.exception.CallsFileException;
import com.noveogroup.callspeak.model.Interval;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Utility class reads calls information from file
 */
public final class CallsFileUtils {
    private static final Logger LOGGER = Logger.getLogger(CallsFileUtils.class);

    private static final String INTERVAL_DELIMITER = ":";

    /**
     * Iterates lines from file and call the function
     * @param fileName name of file
     * @param function to call during iteration
     * @throws CallsFileException for issues with the file
     */
    public static void iterateLines(final String fileName, final Consumer<Interval> function)
            throws CallsFileException {
        try (Stream<Interval> stream = Files.lines(Paths.get(fileName)).map(CallsFileUtils::parseLine)) {
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
    private static Interval parseLine(final String line) {
        String[] period = line.split(INTERVAL_DELIMITER);
        long start = Long.parseLong(period[0]);
        long end = Long.parseLong(period[1]);
        return new Interval(start, end);
    }

    private CallsFileUtils() {

    }
}
