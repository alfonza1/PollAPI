package com.database.h2database.controllers;

import com.database.h2database.model.Option;
import com.database.h2database.model.Vote;
import com.database.h2database.repositories.OptionRepository;
import com.database.h2database.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.database.h2database.errorhandling.ResourceNotFoundException;


import java.util.Optional;

@RestController
public class VoteController {
    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private OptionRepository optionRepository;

    @PostMapping("/polls/{pollId}/vote/{optionId}")
    public ResponseEntity<?> voteForOption(@PathVariable Long pollId, @PathVariable Long optionId) {
        // Check if the option exists
        Optional<Option> optionOptional = optionRepository.findById(optionId);

        if (optionOptional.isPresent()) {
            Option option = optionOptional.get();

            // Create a new vote and associate it with the option
            Vote vote = new Vote();
            vote.setOption(option);
            vote = voteRepository.save(vote);

            // Increment the vote count for the option
            option.setVotes(option.getVotes() + 1);
            optionRepository.save(option);

            return ResponseEntity.ok().build();
        } else {
            throw new ResourceNotFoundException("Option with id " + optionId + " not found");
        }
    }
}



