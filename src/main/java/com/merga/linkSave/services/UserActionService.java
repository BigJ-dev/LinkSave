package com.merga.linkSave.services;

import com.merga.linkSave.models.*;
import dto.UserLinksDTO;
import dto.UserNotesDTO;
import org.springframework.data.domain.Page;

public interface UserActionService {

    User saveUser(User user);

    User updateUserDetails(User user, Long userId);

    User getById(Long userId);

    Link addSiteLink(String siteName, String siteUrl,Long userId);

    Link updateSiteLink(String siteName, String siteUrl, Long linkId, Long userId);

    Note updateNote(String title, String note, Long noteId, Long userId);

    UserLinksDTO getAllUserLinks(User user);

    UserNotesDTO getAllUserNotes(User user);

    void deleteSiteLink(Long linkId, Long userId);

    void deleteNote(Long NoteId, Long userId);

   Page<Link> getUserNames(UserPage userPage, UserSearchCriteria userSearchCriteria);

}
