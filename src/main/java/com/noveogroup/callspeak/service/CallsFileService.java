package com.noveogroup.callspeak.service;

import com.noveogroup.callspeak.exception.CallsFileException;
import com.noveogroup.callspeak.model.Interval;

import java.util.function.Consumer;

/**
 * Service to work with calls file
 */
public interface CallsFileService {

    /**
     * Iterates lines from file and call the function
     * @param fileName name of file
     * @param function to call during iteration
     * @throws CallsFileException for issues with the file
     */
    void iterateLines(String fileName, Consumer<Interval> function) throws CallsFileException;
}
