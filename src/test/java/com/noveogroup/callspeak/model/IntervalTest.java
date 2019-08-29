package com.noveogroup.callspeak.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class IntervalTest {

    @Test
    void isIntersectTest() {
        Interval i1 = new Interval(1, 5);
        Interval i2 = new Interval(3, 7);
        assertTrue(i1.isIntersect(i2));

        i1 = new Interval(3, 5);
        i2 = new Interval(1, 4);
        assertTrue(i1.isIntersect(i2));

        i1 = new Interval(3, 5);
        i2 = new Interval(1, 7);
        assertTrue(i1.isIntersect(i2));

        i1 = new Interval(3, 5);
        i2 = new Interval(8, 9);
        assertFalse(i1.isIntersect(i2));
    }

    @Test
    void intersectionTest() {
        Interval i1 = new Interval(1, 5);
        Interval i2 = new Interval(3, 7);
        i1.intersect(i2);
        assertEquals(3, i1.getStart());
        assertEquals(5, i1.getEnd());

        i1 = new Interval(3, 5);
        i2 = new Interval(1, 4);
        i1.intersect(i2);
        assertEquals(3, i1.getStart());
        assertEquals(4, i1.getEnd());

        i1 = new Interval(3, 5);
        i2 = new Interval(1, 7);
        i1.intersect(i2);
        assertEquals(3, i1.getStart());
        assertEquals(5, i1.getEnd());

        i1 = new Interval(3, 5);
        i2 = new Interval(8, 9);
        i1.intersect(i2);
        assertEquals(3, i1.getStart());
        assertEquals(5, i1.getEnd());
    }

    @Test
    void rightDiffTest() {
        Interval i1 = new Interval(1, 5);
        Interval i2 = new Interval(3, 7);
        Interval i3 = i1.rightDiff(i2);
        Interval i4 = i2.rightDiff(i1);
        assertEquals(i3.getStart(), i4.getStart());
        assertEquals(i3.getEnd(), i4.getEnd());
        assertEquals(6, i3.getStart());
        assertEquals(7, i3.getEnd());

        i1 = new Interval(3, 5);
        i2 = new Interval(1, 4);
        i3 = i1.rightDiff(i2);
        i4 = i2.rightDiff(i1);
        assertEquals(i3.getStart(), i4.getStart());
        assertEquals(i3.getEnd(), i4.getEnd());
        assertEquals(5, i3.getStart());
        assertEquals(5, i3.getEnd());

        i1 = new Interval(3, 5);
        i2 = new Interval(1, 7);
        i3 = i1.rightDiff(i2);
        i4 = i2.rightDiff(i1);
        assertEquals(i3.getStart(), i4.getStart());
        assertEquals(i3.getEnd(), i4.getEnd());
        assertEquals(6, i3.getStart());
        assertEquals(7, i3.getEnd());

        i1 = new Interval(3, 5);
        i2 = new Interval(8, 9);
        i3 = i1.rightDiff(i2);
        i4 = i2.rightDiff(i1);
        assertNull(i3);
        assertNull(i4);
    }

    @Test
    void rightDiffWithPointTest() {
        Interval i1 = new Interval(1, 5);
        Interval i2 = i1.rightDiff(3);
        assertEquals(4, i2.getStart());
        assertEquals(5, i2.getEnd());

        i1 = new Interval(1, 5);
        i2 = i1.rightDiff(6);
        assertNull(i2);

        i1 = new Interval(2, 5);
        i2 = i1.rightDiff(1);
        assertEquals(2, i2.getStart());
        assertEquals(5, i2.getEnd());
    }

    @Test
    void endsBeforeTest() {
        Interval i1 = new Interval(1, 5);
        Interval i2 = new Interval(3, 6);
        assertTrue(i1.endsBefore(i2));
        assertFalse(i2.endsBefore(i1));

        i1 = new Interval(1, 5);
        i2 = new Interval(3, 4);
        assertFalse(i1.endsBefore(i2));
        assertTrue(i2.endsBefore(i1));

        i1 = new Interval(1, 5);
        i2 = new Interval(7, 9);
        assertTrue(i1.endsBefore(i2));
        assertFalse(i2.endsBefore(i1));

        i1 = new Interval(1, 5);
        i2 = new Interval(3, 5);
        assertFalse(i1.endsBefore(i2));
        assertFalse(i2.endsBefore(i1));
    }
}
