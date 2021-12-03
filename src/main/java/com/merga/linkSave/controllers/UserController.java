package com.merga.linkSave.controllers;

import com.merga.linkSave.models.Link;
import com.merga.linkSave.models.User;
import com.merga.linkSave.models.UserPage;
import com.merga.linkSave.models.UserSearchCriteria;
import com.merga.linkSave.repositories.LinkRepository;
import com.merga.linkSave.repositories.UserRepository;
import com.merga.linkSave.services.UserActionServiceImpl;
import com.merga.linkSave.utility.UserHelper;
import dto.UserLinksDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserActionServiceImpl userActionService;
    private final UserRepository userRepo;

    // @PostMapping("/user/save")
//    public ResponseEntity<User> saveUser(@RequestBody User user) {
//        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
//        return ResponseEntity.created(uri).body(userService.saveUser(user));
//    }

    @GetMapping("/users")
    public ResponseEntity<String> getUsers() {
        return ResponseEntity.ok().body(userActionService.getById(1L).getUsername());
    }

    @GetMapping("/usersPage")
    public ResponseEntity<Page<User>> getUUsers(UserPage userPage, UserSearchCriteria userSearchCriteria) {
        return ResponseEntity.ok().body(userActionService.getUserNames(userPage,userSearchCriteria));
    }

    @GetMapping("/usersLink")
    public ResponseEntity<UserLinksDTO> getLinkUsers() {
        User user = userActionService.getById(1L);
        return ResponseEntity.ok().body(userActionService.getAllUserLinks(user));
    }

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
