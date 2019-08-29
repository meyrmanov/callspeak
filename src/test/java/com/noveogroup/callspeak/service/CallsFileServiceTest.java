package com.noveogroup.callspeak.service;

import static org.junit.jupiter.api.Assertions.*;

import com.noveogroup.callspeak.exception.CallsFileException;
import com.noveogroup.callspeak.service.impl.CallsFileServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CallsFileServiceTest {

    @Test()
    void callsFileTest() throws CallsFileException {
        String fileName = "samples/calls.txt";
        new CallsFileServiceImpl().iterateLines(fileName, Assertions::assertNotNull);
    }

    @Test()
    void noFileTest() {
        String fileName = "samples/calls0.txt";
        assertThrows(CallsFileException.class, () -> new CallsFileServiceImpl().iterateLines(fileName,
                Assertions::assertNotNull));
    }

    @Test()
    void incorrectFormatTest1() {
        String fileName = "samples/calls2.txt";
        assertThrows(CallsFileException.class, () -> new CallsFileServiceImpl().iterateLines(fileName,
                Assertions::assertNotNull));
    }

    @Test()
    void incorrectFormatTest2() {
        String fileName = "samples/calls3.txt";
        assertThrows(CallsFileException.class, () -> new CallsFileServiceImpl().iterateLines(fileName,
                Assertions::assertNotNull));
    }
}
