package com.database.h2database.controllers;

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

    @GetMapping("/polls/votes/{optionId}")
    public ResponseEntity<Integer> getVoteCount(@PathVariable Long optionId) {
        Optional<Option> optionOptional = optionRepository.findById(optionId);
        if (optionOptional.isPresent()) {
            Option option = optionOptional.get();
            int voteCount = option.getVotes();
            return ResponseEntity.ok(voteCount);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
