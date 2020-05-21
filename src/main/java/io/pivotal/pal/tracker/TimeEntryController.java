package io.pivotal.pal.tracker;

import io.pivotal.pal.tracker.TimeEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.sql.Time;
import java.util.List;

@RestController("/")
public class TimeEntryController {


    @Autowired
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {

        return new ResponseEntity(timeEntryRepository.create(timeEntryToCreate), HttpStatus.CREATED);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity(timeEntryRepository.list(), HttpStatus.OK);
    }

    @GetMapping("/time-entries/{nonExistentTimeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long nonExistentTimeEntryId) {
        TimeEntry timeEntry = timeEntryRepository.find(nonExistentTimeEntryId);
        if (null != timeEntry) {
            return new ResponseEntity(timeEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity(timeEntry, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/time-entries/{nonExistentTimeEntryId}")
    public ResponseEntity update(@PathVariable long nonExistentTimeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry timeEntry = timeEntryRepository.update(nonExistentTimeEntryId, expected);
        if (null != timeEntry) {
            return new ResponseEntity(timeEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/time-entries/{nonExistentTimeEntryId}")
    public ResponseEntity<String> delete(@PathVariable long nonExistentTimeEntryId) {
        timeEntryRepository.delete(nonExistentTimeEntryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
