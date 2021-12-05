package com.merga.linkSave.controllers;

import com.merga.linkSave.models.Link;
import com.merga.linkSave.models.Note;
import com.merga.linkSave.repositories.UserRepository;
import com.merga.linkSave.services.UserActionServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/note")
@RequiredArgsConstructor
@Slf4j
public class NoteController {

    public static final String URI_ADD_LINK = "/user/addNote";
    public static final String URI_GET_USER_LINKS = "/user/getUserLinks";
    private final UserActionServiceImpl userActionService;
    private final UserRepository userRepo;

    @RequestMapping(value = URI_ADD_LINK,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            // produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    public ResponseEntity<Note> addUserNote(@RequestBody Note note) {
        return ResponseEntity.ok().body(userActionService.addNote(note.getTitle(),note.getNote(),1L));
    }
}
