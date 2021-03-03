package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class TimeEntryController {
    private static TimeEntry timeEntry;
    private final TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    public ResponseEntity<TimeEntry> create(TimeEntry timeEntryToCreate) {
        TimeEntry timeEntryRecord = this.timeEntryRepository.create(timeEntryToCreate);
        return ResponseEntity.created(null).body(timeEntryRecord);
    }

    public ResponseEntity<TimeEntry> read(long id) {
        TimeEntry timeEntry = this.timeEntryRepository.find(id);
        if (timeEntry == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(timeEntry);
    }

    public ResponseEntity<Void> delete(long id) {
        this.timeEntryRepository.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntryList = this.timeEntryRepository.list();
        return ResponseEntity.ok(timeEntryList);
    }

    public ResponseEntity<TimeEntry> update(long id, TimeEntry timeEntryToUpdate) {
        TimeEntry timeEntry = this.timeEntryRepository.update(id, timeEntryToUpdate);
        if (timeEntry != null) {
            return ResponseEntity.ok(timeEntry);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
