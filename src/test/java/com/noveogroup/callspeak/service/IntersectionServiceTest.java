package com.noveogroup.callspeak.service;

import static org.junit.jupiter.api.Assertions.*;
import com.noveogroup.callspeak.model.Interval;
import com.noveogroup.callspeak.service.impl.IntersectionServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class IntersectionServiceTest {

    @Test
    void simpleTest() {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 6));
        intervals.add(new Interval(1, 3));
        intervals.add(new Interval(4, 9));
        intervals.add(new Interval(7, 12));
        intervals.add(new Interval(8, 11));
        IntersectionService service = new IntersectionServiceImpl();
        service.start();
        intervals.forEach(service::addInterval);
        service.finish();

        assertEquals(3, service.getPeakAmount());
        assertEquals(1, service.getPeaks().size());

        Interval res = service.getPeaks().iterator().next();
        assertEquals(8, res.getStart());
        assertEquals(9, res.getEnd());
        assertEquals(3, res.getAmount());
    }
}
