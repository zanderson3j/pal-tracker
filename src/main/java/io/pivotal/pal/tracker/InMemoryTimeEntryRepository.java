package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private long id = 1;
    private HashMap<Long, TimeEntry> data = new HashMap<>();

    public TimeEntry create(TimeEntry timeEntry) {

        timeEntry.setId(id++);

        data.put(timeEntry.getId(), timeEntry);

        return data.get(timeEntry.getId());
    }

    public TimeEntry find(long id) {
        return data.get(id);
    }

    public List<TimeEntry> list() {
        return new ArrayList<>(data.values());
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {

        if(!data.containsKey(id)) return null;

        timeEntry.setId(id);
        data.put(id, timeEntry);
        return data.get(id);
    }

    public void delete(long id) {
        data.remove(id);
    }
}
