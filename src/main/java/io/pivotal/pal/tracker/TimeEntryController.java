package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry body = timeEntryRepository.create(timeEntryToCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @GetMapping("/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable() long timeEntryId) {

        HttpStatus status;

        TimeEntry body = timeEntryRepository.find(timeEntryId);

        if(body == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.OK;
        }

        return ResponseEntity.status(status).body(body);
    }

    @GetMapping()
    public ResponseEntity<List<TimeEntry>> list() {

        List<TimeEntry> body = timeEntryRepository.list();

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @PutMapping("/{timeEntryId}")
    public ResponseEntity update(@PathVariable() long timeEntryId,
                                 @RequestBody TimeEntry expected) {

        HttpStatus status;

        TimeEntry body = timeEntryRepository.update(timeEntryId, expected);

        if(body == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.OK;
        }

        return ResponseEntity.status(status).body(body);
    }

    @DeleteMapping("/{timeEntryId}")
    public ResponseEntity delete(@PathVariable() long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
