package com.merga.linkSave.services;

import com.merga.linkSave.models.Link;
import com.merga.linkSave.models.User;
import com.merga.linkSave.models.UserPage;
import com.merga.linkSave.models.UserSearchCriteria;
import dto.UserLinksDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserActionService {

    User saveUser(User user);

    User getUserByUserId(Long userId);

    User updateUserDetails(User user, Long userId);

    User getById(Long userId);

    Link addSiteLink(String siteName, String siteUrl,Long userId);

    Link updateSiteLink(String siteName, String siteUrl, Long linkId, Long userId);

    void deleteSiteLink(Long linkId, Long userId);

    Page<User> getUserNames(UserPage userPage, UserSearchCriteria userSearchCriteria);

    UserLinksDTO getAllUserLinks(User user);

//    List<UserLinkDTO> getAllUsers();


//    String getUserNameByToken(String token);

}
