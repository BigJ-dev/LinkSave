package com.merga.linkSave.controllers;

import com.merga.linkSave.models.Link;
import com.merga.linkSave.models.User;
import com.merga.linkSave.models.UserPage;
import com.merga.linkSave.models.UserSearchCriteria;
import com.merga.linkSave.repositories.UserRepository;
import com.merga.linkSave.services.UserActionServiceImpl;
import dto.UserLinksDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/link")
@RequiredArgsConstructor
@Slf4j
public class LinkController {
    public static final String URI_ADD_LINK = "/user/addLink";
    public static final String URI_GET_USER_LINKS = "/user/getUserLinks";
    private final UserActionServiceImpl userActionService;
    private final UserRepository userRepo;
    // produces = MediaType.APPLICATION_JSON_VALUE,

    @RequestMapping(value = URI_ADD_LINK,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    public ResponseEntity<Link> addUserLink(@RequestBody Link link) {
        return ResponseEntity.ok().body(userActionService.addSiteLink(link.getSiteName(), link.getSiteUrl(), 1L));
    }


    @RequestMapping(value = "/user/getUserLinks", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<UserLinksDTO>> getUserLinks() {
        User user = userActionService.getById(1L);
        return ResponseEntity.ok().body(userActionService.getAllUserLinks(user));
    }
    @RequestMapping(value = "/user/getUserLink/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Link> getUserLink(@PathVariable("id") Long linkId) {
        User user = userActionService.getById(1L);
        return ResponseEntity.ok().body(userActionService.getSiteLink(4L,user.getId()));
    }

    @RequestMapping(value = "/user/deleteUserLink/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<String> deleteUserLink(@PathVariable("id") Long linkId) {
        User user = userActionService.getById(1L);
        return ResponseEntity.ok().body( userActionService.deleteSiteLink(linkId, user.getId()));
    }

    @RequestMapping(value = "/user/updateUserLink/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Link> updateUserLink(@PathVariable("id") Long linkId, @RequestBody Link link) {
        User user = userActionService.getById(1L);
        return ResponseEntity.ok().body(userActionService.updateSiteLink(link.getSiteName(),link.getSiteUrl(),linkId,user.getId()));
    }

    @GetMapping("/users")
    public ResponseEntity<String> getUsers() {
        return ResponseEntity.ok().body(userActionService.getById(1L).getUsername());
    }

//    @GetMapping("/usersPage")
//    public ResponseEntity<Page<Link>> getUUsers(UserPage userPage, UserSearchCriteria userSearchCriteria) {
//        return ResponseEntity.ok().body(userActionService.getUserNames(userPage, userSearchCriteria));
//    }


//    @GetMapping("/activeUsers")
//    public ResponseEntity<List<UserDetails>> getAllLoggedInUsers() {
//        return ResponseEntity.ok().body(userService.getAllLoggedInUsers());
//    }

//    @PutMapping("/user/{id}")
//    public ResponseEntity<User> updateUserInfo(@PathVariable("id") Long userId, @RequestBody User user) {
//        return ResponseEntity.ok().body(userService.updateUserDetails(user, userId));
//    }

//    @PostMapping("/login")
//    public ResponseEntity<Map<String, String> > userSessionToken(@RequestHeader String authorization) {
//        return ResponseEntity.ok().body(userService.getUserSessionToken(userRepo.findByUsername(userService.getUserNameByToken(authorization))));
//    }

//    @PostMapping("/logout/{id}")
//    public ResponseEntity<Map<String, String>> getUserSessionTokenById(@PathVariable("id") Long userId) {
//        Map<String, String> userDetails = userService.getUserSessionToken(userRepo.getById(userId));
//        userDetails.remove("id");
//        return ResponseEntity.ok().body(userDetails);
//    }

//    @GetMapping("/user/{userId}")
//    public User getUserByUserId(@PathVariable Long userId) {
//        return userService.getUserByUserId(userId);
//    }

//    @PostMapping("/link")
//    public ResponseEntity<Link> addSiteLink(@PathVariable String siteName, String siteUrl) {
//
////
////        return ResponseEntity.ok().body(userService.getUserSessionToken(userRepo.findByUsername(userService.getUserNameByToken(authorization))));
////        return ResponseEntity.created(UserHelper.getUri("/api/addLink")).body(userActionService.addSiteLink(siteName, siteUrl));
//
//        return ResponseEntity.ok().body(userActionService.addSiteLink(siteName, siteUrl));
//    }
}
