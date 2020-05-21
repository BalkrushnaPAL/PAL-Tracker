package io.pivotal.pal.tracker;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    Map<Long,TimeEntry> timeEntries = new HashMap<>();
    long timeEntryIdCounter = 1L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(timeEntryIdCounter++);
        timeEntries.put(timeEntry.getId(), timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry find(long id) {
        return timeEntries.get(id);
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        timeEntry.setId(id);
        timeEntries.put(id, timeEntry);
        return timeEntry;
    }

    @Override
    public void delete(long id) {

        timeEntries.remove(id);
    }

    @Override
    public List list() {
        return Arrays.asList(timeEntries.values().toArray());
    }

}
