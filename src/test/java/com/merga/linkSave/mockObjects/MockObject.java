package com.merga.linkSave.mockObjects;

import com.merga.linkSave.models.Link;
import com.merga.linkSave.models.User;
import com.merga.linkSave.utility.UserHelper;

public class MockObject {
    public static String UPDATE_SITE_NAME = "ForexSite";
    public static User user(){
        User user = new User();

        user.setId(1L);
        user.setUsername("Tshepo");
        user.setPassword("1235");
        user.setPhone("0712139561");
        return user;
    }

    public static Link siteLink(User user){
        Link siteLink = new Link();

        siteLink.setUser(user);
        siteLink.setId(1l);
        siteLink.setSiteName("YouTube");
        siteLink.setSiteUrl("https://www.youtube.com/watch?v=hGe4sbJrRcE");
        siteLink.setSavedDate(UserHelper.getDateTimeObject());
        return siteLink;
    }

}
