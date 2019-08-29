package com.noveogroup.callspeak.model;

/**
 * Represents call interval entity with all necessary operations with the intervals.
 */
public class Interval {

    private int amount;
    private long start;
    private long end;

    private Interval() {

    }

    public Interval(final long start, final long end) {
        this.start = start;
        this.end = end;
        this.amount = 1;
    }

    /**
     * Detects if the interval has an intersection with another interval
     * @param interval second interval
     * @return true if intervals are intersected
     */
    public boolean isIntersect(final Interval interval) {
        return !(this.start > interval.end || this.end < interval.start);
    }

    /**
     * Reduce current interval to the intersection with another interval
     * New calls amount calculated as sum of amounts of the intervals
     * @param interval second interval
     */
    public void intersect(final Interval interval) {
        if (!isIntersect(interval)) {
            return;
        }
        this.start = interval.start > this.start ? interval.start : this.start;
        this.end = interval.end < this.end ? interval.end : this.end;
        this.amount = interval.amount + this.amount;
    }

    /**
     * Creates new interval instance with right non-intersected part, started from next second.
     * New interval has calls amount from the interval which contains this part.
     * Current interval will not be affected.
     * @param interval second interval
     * @return right non-intersected part
     */
    public Interval rightDiff(final Interval interval) {
        if (!isIntersect(interval)) {
            return null;
        }
        Interval result = new Interval();

        if (this.end < interval.end) {
            result.start = this.end + 1;
            result.end = interval.end;
            result.amount = interval.amount;
        } else if (interval.end < this.end) {
            result.start = interval.end + 1;
            result.end = this.end;
            result.amount = this.amount;
        } else {
            return null;
        }
        return result;
    }

    /**
     * Splits current interval with the point (second) and creates new interval with right part,
     * started from next second.
     * New interval has calls amount similar to current interval.
     * Current interval will not be affected.
     * @param second to split the interval
     * @return right part of the interval
     */
    public Interval rightDiff(final long second) {
        if (this.end <= second) {
            return null;
        }
        Interval result = new Interval();
        result.start = second + 1;
        result.end = this.end;
        result.amount = this.amount;
        return result;
    }

    /**
     * Detects if the interval ends before another
     * @param interval second interval
     * @return true if the interval ends before another
     */
    public boolean endsBefore(final Interval interval) {
        return this.end < interval.end;
    }

    /**
     * @return Call amount of this interval
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @return start second
     */
    public long getStart() {
        return start;
    }

    /**
     * @return end second
     */
    public long getEnd() {
        return end;
    }
}
