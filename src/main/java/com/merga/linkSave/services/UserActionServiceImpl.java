package com.merga.linkSave.services;

import com.merga.linkSave.models.*;

import com.merga.linkSave.repositories.LinkRepository;
import com.merga.linkSave.repositories.NoteRepository;
import com.merga.linkSave.repositories.UserCriteriaRepository;
import com.merga.linkSave.repositories.UserRepository;
import com.merga.linkSave.utility.UserHelper;
import dto.UserLinksDTO;
import dto.UserNotesDTO;
import javafx.util.Pair;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserActionServiceImpl implements UserActionService {
    //UserDetailsService
    private final UserRepository userRepo;
    private final UserSearchCriteria userSearchCriteria;
    private final UserCriteriaRepository userCriteriaRepo;
    private final LinkRepository siteLinkRepo;
    private final NoteRepository noteRepo;
    // private final PasswordEncoder passwordEncoder;

    //For testing
    //private final UserSearchCriteria userSearchCriteria;

    @Override
    public User saveUser(User user) {
        //  user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public User updateUserDetails(User user, Long userId) {
        User existingUser = userRepo.getById(userId);

        if (existingUser.getId().equals(userId)) {
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            existingUser.setPhone(user.getPhone());
        } else {
            log.error("This user don't exist in the db");
        }
        return userRepo.getById(userId);
    }

    @Override
    public User getById(Long userId) {
        return userRepo.getById(userId);
    }

    @Override
    public Note addNote(String title, String note, Long userId) {
        User user = userRepo.getById(userId);
        Date dateTime = UserHelper.getDateTimeObject();
        Note addNote = new Note();

        if (userRepo.existsById(userId)) {
            addNote.setTitle(title);
            addNote.setNote(note);
            addNote.setSavedDate(dateTime);
            addNote.setUser(user);
        } else {
            log.error("we cant identify user, Please logout and log in");
        }
        return noteRepo.save(addNote);
    }

    @Override
    public Link addSiteLink(String siteName, String siteUrl, Long userId) {
        User user = userRepo.getById(userId);
        Date dateTime = UserHelper.getDateTimeObject();
        Link addSite = new Link();

        if (userRepo.existsById(userId)) {
            addSite.setSiteName(siteName);
            addSite.setSiteUrl(siteUrl);
            addSite.setSavedDate(dateTime);
            addSite.setUser(user);
        } else {
            log.error("We can't identify user, Please logout and login");
        }
        return siteLinkRepo.save(addSite);
    }

    @Override
    public Link getSiteLink(Long linkId, Long userId) {
        Link site = null;
        if (userRepo.existsById(userId) && siteLinkRepo.existsById(linkId)) {
            site = siteLinkRepo.findById(linkId).get();
        } else {
            log.error("site link don't exist");
        }
        return site;
    }

    @Override
    public Note updateNote(String title, String note, Long noteId, Long userId) {
        User user = userRepo.getById(userId);
        Note existingNote = noteRepo.getById(noteId);
        Date dateTime = UserHelper.getDateTimeObject();

        if (userRepo.existsById(userId) && noteRepo.existsById(noteId)) {
            existingNote.setTitle(title);
            existingNote.setNote(note);
            existingNote.setLastModifiedDate(dateTime);
            existingNote.setUser(user);
        } else {
            log.error("Failed to update the note, Please refresh");
        }
        return noteRepo.save(existingNote);
    }

    @Override
    public Link updateSiteLink(String siteName, String siteUrl, Long linkId, Long userId) {
        User user = userRepo.getById(userId);
        Link site = siteLinkRepo.getById(linkId);
        Date dateTime = UserHelper.getDateTimeObject();

        if (userRepo.existsById(userId) && siteLinkRepo.existsById(linkId)) {
            site.setSiteName(siteName);
            site.setSiteUrl(siteUrl);
            site.setLastModifiedDate(dateTime);
            site.setUser(user);
        } else {
            log.error("Failed to update the site link, Please refresh");
        }
        return siteLinkRepo.save(site);
    }

    @Override
    public void deleteNote(Long noteId, Long userId) {
        Note note = noteRepo.getById(noteId);
        if (userRepo.existsById(userId) && noteRepo.existsById(noteId)) {
            noteRepo.deleteById(noteId);
            log.info("The note:" + note.getTitle() + " " + "has been deleted");
        }
    }

    @Override
    public String deleteSiteLink(Long linkId, Long userId) {
        Link site = siteLinkRepo.getById(linkId);
        String deletedItem = null;
        if (userRepo.existsById(userId) && siteLinkRepo.existsById(linkId)) {
            siteLinkRepo.deleteById(linkId);
            deletedItem = "The siteLink:" + " " + site.getSiteName() + " " + "has been deleted.";
        }
        return deletedItem;
    }

    @Override
    public Page<Link> getUserNames(UserPage userPage, UserSearchCriteria userSearchCriteria) {
        return userCriteriaRepo.findAllWithFilters(userPage, userSearchCriteria);
    }

    @Override
    public List<UserLinksDTO> getAllUserLinks(User user) {
        return convertEntityToLinksDto(user);
    }

    private List<UserLinksDTO> convertEntityToLinksDto(User user) {
        List<UserLinksDTO> userLinksDTO = new ArrayList<>();
        boolean hasUserAddedLink = siteLinkRepo.findAll().stream().anyMatch(link -> link.getUser().getId().equals(user.getId()));

        if (hasUserAddedLink) {
            siteLinkRepo.findAll()
                    .stream()
                    .filter(link -> link.getUser().getId().equals(user.getId()))
                    .forEach(link -> userLinksDTO.add(new UserLinksDTO(link.getId(), link.getSiteName(), link.getSiteUrl(), link.getSavedDate())));
        } else {
            log.error("Please add website link");
        }
        return userLinksDTO;
    }

    @Override
    public Map<String, List<UserNotesDTO>> getAllUserNotes(User user) {
        return convertEntityToNotesDto(user);
    }

    private Map<String, List<UserNotesDTO>> convertEntityToNotesDto(User user) {
        List<UserNotesDTO> userLinksDTO = new ArrayList<>();
        Map<String, List<UserNotesDTO>> userLinksMap = new HashMap<>();
        boolean hasUserAddedLink = noteRepo.findAll().stream().anyMatch(note -> note.getUser().getId().equals(user.getId()));

        if (hasUserAddedLink) {
            noteRepo.findAll()
                    .stream()
                    .filter(note -> note.getUser().getId().equals(user.getId()))
                    .forEach(note -> userLinksDTO.add(new UserNotesDTO(note.getTitle(), note.getNote(), note.getSavedDate())));
            userLinksMap.put(user.getUsername(), userLinksDTO);
        } else {
            log.error("Please add notes");
        }
        return userLinksMap;
    }


//    @Override
//    public String getUserNameByToken(String authorization) {
//        String token = authorization.substring("Bearer ".length());
//        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
//        JWTVerifier verifier = JWT.require(algorithm).build();
//        DecodedJWT decodedJWT = verifier.verify(token);
//        return decodedJWT.getSubject();
//    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepo.findByUsername(username);
//        if (user == null) {
//            log.error("User not found in the db");
//            throw new UsernameNotFoundException(username);
//        } else {
//            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
//        }
//    }

}
