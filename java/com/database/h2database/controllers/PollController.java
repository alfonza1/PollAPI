package com.database.h2database.controllers;

import com.database.h2database.errorhandling.ResourceNotFoundException;
import com.database.h2database.model.Poll;
import com.database.h2database.model.PollOption;
import com.database.h2database.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
public class PollController {
    private Map<Integer, PollOption> pollOptions = new HashMap<>();
    @Autowired
    private PollService pollService;


    @PostMapping("/polls")
    public ResponseEntity<Poll> createPoll(@RequestBody Poll poll) {
        return ResponseEntity.ok(pollService.createPoll(poll));
    }

    @GetMapping("polls/{id}")
    public ResponseEntity<Poll> getPoll(@PathVariable Long id) {
        Optional<Poll> poll = pollService.getPoll(id);
        if(poll.isPresent()) {
            return ResponseEntity.ok(poll.get());
        } else {
            throw new ResourceNotFoundException("Poll with id " + id + " not found");
        }
    }



    @GetMapping("polls")
    public ResponseEntity<Iterable<Poll>> getAllPolls() {
        return ResponseEntity.ok(pollService.getAllPolls());
    }


    @PutMapping("polls/{id}")
    public ResponseEntity<Poll> updatePoll(@PathVariable Long id, @RequestBody Poll newPoll) {
        Optional<Poll> existingPoll = pollService.getPoll(id);
        if (existingPoll.isPresent()) {
            newPoll.setId(id);
            return ResponseEntity.ok(pollService.updatePoll(newPoll));
        } else {
            throw new ResourceNotFoundException("Poll with id " + id + " not found");
        }
    }



    @DeleteMapping("polls/{id}")
    public ResponseEntity<Void> deletePoll(@PathVariable Long id) {
        return pollService.deletePoll(id);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getPollOption(@PathVariable int id) {
        if (pollOptions.containsKey(id)) {
            return ResponseEntity.ok(pollOptions.get(id));
        } else {
            throw new ResourceNotFoundException("PollOption with id " + id + " not found");
        }
    }

}

