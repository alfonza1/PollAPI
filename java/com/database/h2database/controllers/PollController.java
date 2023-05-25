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
    public ResponseEntity<Optional<Poll>> getPoll(@PathVariable Long id) {
        return pollService.getPoll(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("polls")
    public ResponseEntity<Iterable<Poll>> getAllPolls() {
        return ResponseEntity.ok(pollService.getAllPolls());
    }


    @PutMapping("polls/{id}")
    public ResponseEntity<Poll> updatePoll(@PathVariable Long id, @RequestBody Poll poll) {
        Optional<Poll> existingPoll = pollService.getPoll(id).orElse(null);
        if (existingPoll != null) {
            poll.setId(id);
            return ResponseEntity.ok(pollService.updatePoll(poll));
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

