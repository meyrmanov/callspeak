package com.noveogroup.callspeak.service.impl;

import com.noveogroup.callspeak.model.Interval;
import com.noveogroup.callspeak.service.IntersectionService;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Default implementation of IntersectionService
 */
public class IntersectionServiceImpl implements IntersectionService {

    private static final Logger LOGGER = Logger.getLogger(IntersectionServiceImpl.class);

    private AtomicInteger max;
    private AtomicLong maxEndSecond;
    private Collection<Interval> maxLines;
    private Collection<Interval> current;

    @Override
    public void addInterval(final Interval interval) {
        Interval diff = null;
        if (!current.isEmpty()) { // iterating all intervals (active) which can be intersected with new intervals
            Iterator<Interval> iterator = current.iterator();
            while (iterator.hasNext()) {
                Interval i = iterator.next();
                if (i.isIntersect(interval)) { // if active interval intersects, make 2 actions
                    if (interval.endsBefore(i)) { // #1 if new interval's end < end of one interval
                        diff = i.rightDiff(interval); // #1 creates new interval with right part
                    }
                    i.intersect(interval); // #2 intersect intervals
                } else if (i.endsBefore(interval)) { // if active interval does not intersect new and it ends before
                    if (i.getAmount() >= max.get()) { // if calls amount >= previous max, we save it
                        if (i.getAmount() > max.get()) {
                            maxLines.clear(); // clean old intervals (candidates for peaks)
                            max.set(i.getAmount());
                        }
                        maxLines.add(i);
                    }
                    iterator.remove(); // remove interval as it will not have any intersections anymore
                }
            }
        } else {
            current.add(interval);
        }

        if (diff == null) { // if no right part is calculated, this means new interval ends after all
            diff = interval.rightDiff(maxEndSecond.get()); // create new interval from previous max end to this end
        }
        if (diff != null) {
            current.add(diff);
        }

        maxEndSecond.set(Math.max(interval.getEnd(), maxEndSecond.get())); // update max end
    }

    public void start() {
        max = new AtomicInteger(1);
        maxEndSecond = new AtomicLong(0);
        maxLines = new HashSet<>();
        current = new LinkedList<>();
        LOGGER.info("Intersection calculation started!");
    }

    public void finish() {
        current.stream().filter(i -> i.getAmount() >= max.get()).forEach(i -> {
            if (i.getAmount() > max.get()) { // find any active intervals with amount >= current max
                max.set(i.getAmount());
                maxLines.clear();
            }
            maxLines.add(i);
        });
        current.clear();
        LOGGER.info("Intersection calculation finished!");
    }

    public Collection<Interval> getPeaks() {
        return maxLines;
    }

    public int getPeakAmount() {
        return max.get();
    }
}
