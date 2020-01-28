package com.milo.recipes.repository;

import com.milo.recipes.model.Difficulty;
import org.springframework.data.repository.CrudRepository;

public interface DifficultyRepository extends CrudRepository<Difficulty, Long> {
}
