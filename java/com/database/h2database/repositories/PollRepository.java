package com.database.h2database.repositories;
import com.database.h2database.model.Poll;
import org.springframework.data.repository.CrudRepository;
public interface PollRepository extends CrudRepository<Poll, Long> {
}
