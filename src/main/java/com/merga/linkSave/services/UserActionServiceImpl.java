package com.merga.linkSave.services;

import com.merga.linkSave.models.Link;
import com.merga.linkSave.models.User;

import com.merga.linkSave.models.UserPage;
import com.merga.linkSave.models.UserSearchCriteria;
import com.merga.linkSave.repositories.LinkRepository;
import com.merga.linkSave.repositories.UserCriteriaRepository;
import com.merga.linkSave.repositories.UserRepository;
import com.merga.linkSave.utility.UserHelper;
import dto.UserLinksDTO;
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
    public Link addSiteLink(String siteName, String siteUrl, Long userId) {
        User user = userRepo.getById(userId);
        Date dateTime = UserHelper.getDateTimeObject();
        Link site = new Link();

        if (userRepo.existsById(userId)) {
            site.setSiteName(siteName);
            site.setSiteUrl(siteUrl);
            site.setSavedDate(dateTime);
            site.setUser(user);
        } else {
            log.error("We can't identify user , Please logout and login");
        }
        return siteLinkRepo.save(site);
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
    public void deleteSiteLink(Long linkId, Long userId) {
        Link site = siteLinkRepo.getById(linkId);
        if (userRepo.existsById(userId) && siteLinkRepo.existsById(linkId)) {
            siteLinkRepo.deleteById(linkId);
            log.info("The site link:" + site.getSiteName() + " " + "is deleted");
        }
    }

    @Override
    public Page<Link> getUserNames(UserPage userPage, UserSearchCriteria userSearchCriteria) {
        return userCriteriaRepo.findAllWithFilters(userPage, userSearchCriteria);
    }

    @Override
    public UserLinksDTO getAllUserLinks(User user) {
        return convertEntityToLinksDto(user);
    }

    private UserLinksDTO convertEntityToLinksDto(User user) {
        UserLinksDTO userLinksDTO = new UserLinksDTO();
        Map<String,  Pair<String, Date>> userLinksMap = new HashMap<>();
        boolean hasUserAddedLink = siteLinkRepo.findAll().stream().anyMatch(link -> link.getUser().getId().equals(user.getId()));

        if (hasUserAddedLink) {
            siteLinkRepo.findAll()
                    .stream()
                    .filter(link -> link.getUser().getId().equals(user.getId()))
                    .forEach(link -> userLinksMap.put(link.getSiteName(), new Pair<>(link.getSiteUrl(), link.getSavedDate())));
            userLinksDTO.setUsername(user.getUsername());
            userLinksDTO.setSiteLinks(userLinksMap);
        } else {
            log.error("You need to add at least on site link");
        }
        return userLinksDTO;
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
