package com.merga.linkSave.repositories;

import com.merga.linkSave.models.Note;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface NoteRepository extends PagingAndSortingRepository<Note, Long> {
    List<Note> findAll();

    Note getById(Long NoteId);
}
