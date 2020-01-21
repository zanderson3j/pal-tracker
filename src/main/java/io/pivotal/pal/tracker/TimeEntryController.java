package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {

        TimeEntry body = timeEntryRepository.create(timeEntryToCreate);

        ResponseEntity responseEntity = new ResponseEntity(
                body,
                HttpStatus.CREATED
        );

        return responseEntity;
    }

    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable() long timeEntryId) {

        ResponseEntity responseEntity;

        TimeEntry body = timeEntryRepository.find(timeEntryId);

        if(body == null) {
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity(
                    body,
                    HttpStatus.OK
            );
        }

        return responseEntity;
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {

        List<TimeEntry> body = timeEntryRepository.list();

        ResponseEntity responseEntity = new ResponseEntity(
                body,
                HttpStatus.OK
        );

        return responseEntity;
    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity update(@PathVariable() long timeEntryId,
                                 @RequestBody TimeEntry expected) {
        ResponseEntity responseEntity;

        TimeEntry body = timeEntryRepository.update(timeEntryId, expected);

        if(body == null) {
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity(
                    body,
                    HttpStatus.OK
            );
        }

        return responseEntity;
    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity delete(@PathVariable() long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
