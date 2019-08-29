package com.noveogroup.callspeak;

import com.noveogroup.callspeak.exception.CallsFileException;
import com.noveogroup.callspeak.service.CallsFileService;
import com.noveogroup.callspeak.service.IntersectionService;
import com.noveogroup.callspeak.service.OutputService;
import com.noveogroup.callspeak.service.impl.CallsFileServiceImpl;
import com.noveogroup.callspeak.service.impl.IntersectionServiceImpl;
import com.noveogroup.callspeak.service.impl.OutputServiceImpl;
import org.apache.log4j.Logger;

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
        intersectionService.start();
        try {
            fileService.iterateLines(args[0], intersectionService::addInterval);
        } catch (CallsFileException e) {
            System.exit(1);
        }
        intersectionService.finish();
        outputService.print(intersectionService.getPeaks(), intersectionService.getPeakAmount());
    }

    private CallsPeakApp() {

    }
}
