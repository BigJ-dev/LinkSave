package com.merga.linkSave.services;

import com.merga.linkSave.models.*;
import dto.UserLinksDTO;
import dto.UserNotesDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface UserActionService {

    User saveUser(User user);

    User updateUserDetails(User user, Long userId);

    User getById(Long userId);

    Note addNote(String title, String note,Long userId);

    Link addSiteLink(String siteName, String siteUrl,Long userId);

    Link getSiteLink(Long linkId,Long userId);

    Link updateSiteLink(String siteName, String siteUrl, Long linkId, Long userId);

    Note updateNote(String title, String note, Long noteId, Long userId);

    List<UserLinksDTO> getAllUserLinks(User user);

    Map<String, List<UserNotesDTO>> getAllUserNotes(User user);

    String deleteSiteLink(Long linkId, Long userId);

    void deleteNote(Long NoteId, Long userId);

//   Page<Link> getUserNames(UserPage userPage, UserSearchCriteria userSearchCriteria);

}
