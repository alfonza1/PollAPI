package com.database.h2database.service;

import com.database.h2database.errorhandling.ResourceNotFoundException;
import com.database.h2database.model.Poll;
import com.database.h2database.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;

    public Poll createPoll(Poll poll) {
        return pollRepository.save(poll);
    }

    public Optional<Poll> getPoll(Long id) {
        return pollRepository.findById(id);
    }


    public Iterable<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    public Poll updatePoll(Poll poll) {

        return pollRepository.save(poll);
    }

    public ResponseEntity<Void> deletePoll(Long id) {
        pollRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public void verifyPoll(Long pollId) throws ResourceNotFoundException {
        Optional<Poll> poll = pollRepository.findById(pollId);
        if (poll == null) { // if the poll given does not exist it will return the message below
            throw new ResourceNotFoundException("The poll with id " + pollId + " does not exist");
        }
    }

}
