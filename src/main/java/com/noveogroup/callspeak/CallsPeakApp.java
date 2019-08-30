package com.noveogroup.callspeak;

import com.noveogroup.callspeak.exception.CallsFileException;
import com.noveogroup.callspeak.service.CallsFileService;
import com.noveogroup.callspeak.service.IntersectionService;
import com.noveogroup.callspeak.service.OutputService;
import com.noveogroup.callspeak.service.impl.CallsFileServiceImpl;
import com.noveogroup.callspeak.service.impl.IntersectionServiceImpl;
import com.noveogroup.callspeak.service.impl.OutputServiceImpl;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Main class of the application
 */
public final class CallsPeakApp {

    private static final Logger LOGGER = Logger.getLogger(CallsPeakApp.class);

    public static void main(final String[] args) {
        if (args.length == 0) {
            LOGGER.error("No filename passed!");
            System.exit(1);
        }

        CallsFileService fileService = new CallsFileServiceImpl();
        IntersectionService intersectionService = new IntersectionServiceImpl();
        OutputService outputService = new OutputServiceImpl();

        LOGGER.info("Intersection calculation started!");
        try (Stream<String> stream = Files.lines(Paths.get(args[0]))) {
            fileService.iterateLines(stream, intersectionService::addInterval);
        } catch (CallsFileException e) {
            System.exit(1);
        } catch (IOException e) {
            LOGGER.error("Couldn't read from file!");
            System.exit(1);
        }
        outputService.print(intersectionService.getPeakResult());
    }

    private CallsPeakApp() {

    }
}
