package com.noveogroup.callspeak;

import com.noveogroup.callspeak.exception.CallsFileException;
import com.noveogroup.callspeak.service.IntersectionService;
import com.noveogroup.callspeak.service.OutputService;
import com.noveogroup.callspeak.service.impl.IntersectionServiceImpl;
import com.noveogroup.callspeak.service.impl.OutputServiceImpl;
import com.noveogroup.callspeak.util.CallsFileUtils;
import org.apache.log4j.Logger;

/**
 * Main class of the application
 */
public final class CallsPeakApp {

    private static final Logger LOGGER = Logger.getLogger(CallsPeakApp.class);

    private static final IntersectionService INTERSECTION_SERVICE = new IntersectionServiceImpl();
    private static final OutputService OUTPUT_SERVICE = new OutputServiceImpl();

    public static void main(final String[] args) {
        if (args.length == 0) {
            LOGGER.error("No filename passed!");
            System.exit(1);
        }
        INTERSECTION_SERVICE.start();
        try {
            CallsFileUtils.iterateLines(args[0], INTERSECTION_SERVICE::addInterval);
        } catch (CallsFileException e) {
            System.exit(1);
        }
        INTERSECTION_SERVICE.finish();
        OUTPUT_SERVICE.print(INTERSECTION_SERVICE.getPeaks(), INTERSECTION_SERVICE.getPeakAmount());

        // todo maven & readme
    }

    private CallsPeakApp() {

    }
}
