package com.milo.recipes.repository;

import com.milo.recipes.model.Notes;
import org.springframework.data.repository.CrudRepository;

public interface NotesRepository extends CrudRepository<Notes, Long> {
}
