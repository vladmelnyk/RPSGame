package com.company.repository.inmemory;

import com.company.model.Game;
import org.springframework.stereotype.Repository;

@Repository
public class GameInMemoryRepository extends InMemoryRepository<Game> {
}
