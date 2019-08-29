package com.noveogroup.callspeak.util;

import static org.junit.jupiter.api.Assertions.*;
import com.noveogroup.callspeak.exception.CallsFileException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CallsFileUtilTest {

    @Test()
    void callsFileTest() throws CallsFileException {
        String fileName = "samples/calls.txt";
        CallsFileUtils.iterateLines(fileName, Assertions::assertNotNull);
    }

    @Test()
    void noFileTest() {
        String fileName = "samples/calls0.txt";
        assertThrows(CallsFileException.class, () -> CallsFileUtils.iterateLines(fileName, Assertions::assertNotNull));
    }

    @Test()
    void incorrectFormatTest1() {
        String fileName = "samples/calls2.txt";
        assertThrows(CallsFileException.class, () -> CallsFileUtils.iterateLines(fileName, Assertions::assertNotNull));
    }

    @Test()
    void incorrectFormatTest2() {
        String fileName = "samples/calls3.txt";
        assertThrows(CallsFileException.class, () -> CallsFileUtils.iterateLines(fileName, Assertions::assertNotNull));
    }
}
