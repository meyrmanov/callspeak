package com.noveogroup.callspeak.service.impl;

import com.noveogroup.callspeak.dto.PeakResultDTO;
import com.noveogroup.callspeak.model.Interval;
import com.noveogroup.callspeak.service.IntersectionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Default implementation of IntersectionService
 */
public class IntersectionServiceImpl implements IntersectionService {

    private Integer max = 1;
    private Long maxEndSecond = 0L;
    private Collection<Interval> maxLines = new HashSet<>();
    private Collection<Interval> current = new LinkedList<>();

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
                    if (i.getAmount() >= max) { // if calls amount >= previous max, we save it
                        if (i.getAmount() > max) {
                            maxLines.clear(); // clean old intervals (candidates for peaks)
                            max = i.getAmount();
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
            diff = interval.rightDiff(maxEndSecond); // create new interval from previous max end to this end
        }
        if (diff != null) {
            current.add(diff);
        }

        maxEndSecond = Math.max(interval.getEnd(), maxEndSecond); // update max end
    }

    public PeakResultDTO getPeakResult() {
        Collection<Interval> resultPeaks = new LinkedList<>(maxLines);
        int initialMax = max;
        current.stream().filter(i -> i.getAmount() >= max).forEach(i -> {
            if (i.getAmount() > max) { // find any active intervals with amount >= current max
                max = i.getAmount();
                resultPeaks.clear();
            }
            resultPeaks.add(i);
        });
        PeakResultDTO result = new PeakResultDTO(resultPeaks, max);
        max = initialMax;
        return result;
    }
}
