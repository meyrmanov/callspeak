package com.noveogroup.callspeak.service;

import com.noveogroup.callspeak.exception.CallsFileException;
import com.noveogroup.callspeak.service.impl.CallsFileServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CallsFileServiceTest {

    @Test()
    void callsFileTest() throws CallsFileException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("calls.txt");
        try (Stream<String> stream = new BufferedReader(new InputStreamReader(is)).lines()) {
            new CallsFileServiceImpl().iterateLines(stream, Assertions::assertNotNull);
        }
    }

    @Test()
    void incorrectFormatTest1() {
        InputStream is = getClass().getClassLoader().getResourceAsStream("calls2.txt");
        try (Stream<String> stream = new BufferedReader(new InputStreamReader(is)).lines()) {
            assertThrows(CallsFileException.class, () -> new CallsFileServiceImpl().iterateLines(stream,
                    Assertions::assertNotNull));
        }
    }

    @Test()
    void incorrectFormatTest2() {
        InputStream is = getClass().getClassLoader().getResourceAsStream("calls3.txt");
        try (Stream<String> stream = new BufferedReader(new InputStreamReader(is)).lines()) {
            assertThrows(CallsFileException.class, () -> new CallsFileServiceImpl().iterateLines(stream,
                    Assertions::assertNotNull));
        }
    }
}
