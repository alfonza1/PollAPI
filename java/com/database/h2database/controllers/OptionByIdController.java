package com.database.h2database.controllers;

import com.database.h2database.errorhandling.ResourceNotFoundException;
import com.database.h2database.model.Option;
import com.database.h2database.repositories.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class OptionByIdController {
    @Autowired
    private OptionRepository optionRepository;

    @GetMapping("/polls/{pollId}/votes/{optionId}")
    public ResponseEntity<String> getVoteCount(
            @PathVariable Long pollId,
            @PathVariable Long optionId
    ) {
        Optional<Option> optionOptional = optionRepository.findById(optionId);
        if (optionOptional.isPresent()) {
            Option option = optionOptional.get();
            String optionValue = option.getValue();
            int voteCount = option.getVotes();
            String result = "Poll ID: " + pollId +
                    ", Option: " + optionValue +
                    ", Votes: " + voteCount;
            return ResponseEntity.ok(result);
        } else {
            throw new ResourceNotFoundException("Option with id " + optionId + " not found");
        }
    }



}
