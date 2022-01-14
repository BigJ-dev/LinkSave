package com.merga.linkSave.services;

import com.merga.linkSave.mockObjects.MockObject;
import com.merga.linkSave.models.Link;
import com.merga.linkSave.models.User;
import com.merga.linkSave.repositories.LinkRepository;
import com.merga.linkSave.repositories.NoteRepository;
import com.merga.linkSave.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserActionServiceImplTest {
    @Mock
    private User user;
    @Mock
    private Link siteLink;
    @Mock
    private UserRepository userRepo;
    @Mock
    private LinkRepository linkRepo;
    @Mock
    private NoteRepository noteRepo;
    private UserActionServiceImpl unitUnderTest;

    @BeforeEach
    void setUp() {
        user = MockObject.user();
        siteLink = MockObject.siteLink(user);
        unitUnderTest = new UserActionServiceImpl(userRepo, linkRepo, noteRepo);
    }

    @Test
    void canAddSiteLink() {
        //given
        when(userRepo.getById(1L)).thenReturn(user);
        when(userRepo.existsById(1L)).thenReturn(true);

        //when
        unitUnderTest.addSiteLink(siteLink.getSiteName(), siteLink.getSiteUrl(), user.getId());

        ArgumentCaptor<Link> linkArgumentCaptor = ArgumentCaptor.forClass(Link.class);
        verify(linkRepo).save(linkArgumentCaptor.capture());
        Link capturedSiteLink = linkArgumentCaptor.getValue();
        capturedSiteLink.setId(1L);

        //then
        assertThat(capturedSiteLink)
                .isNotNull()
                .isEqualTo(siteLink);
    }

    @Test
    void canUpdateSiteLink() {
        //given
        when(userRepo.getById(1L)).thenReturn(user);
        when(userRepo.existsById(1L)).thenReturn(true);
        when(linkRepo.existsById(1L)).thenReturn(true);
        when(linkRepo.getById(1L)).thenReturn(siteLink);
        Link siteLinkBeforeUpdate = MockObject.siteLink(user);

        //when
        unitUnderTest.updateSiteLink(MockObject.UPDATE_SITE_NAME, siteLink.getSiteUrl(), siteLink.getId(), user.getId());
        ArgumentCaptor<Link> linkArgumentCaptor = ArgumentCaptor.forClass(Link.class);
        verify(linkRepo).save(linkArgumentCaptor.capture());
        Link capturedSiteLink = linkArgumentCaptor.getValue();

        //then
        assertThat(capturedSiteLink)
                .isNotNull()
                .isNotEqualTo(siteLinkBeforeUpdate);
    }

    @Test
    void canGetSiteLink() {
        //given
        when(userRepo.existsById(1L)).thenReturn(true);
        when(linkRepo.existsById(1L)).thenReturn(true);
        when(linkRepo.getById(1L)).thenReturn(siteLink);

        //when
        unitUnderTest.getSiteLink(siteLink.getId(), user.getId());
        verify(linkRepo).getById(siteLink.getId());

        //then
        assertThat(linkRepo.getById(1L))
                .isNotNull()
                .isEqualTo(siteLink);
    }

    @Test
    void canGetAllUserSiteLinks() {
        //given

        //when
        //unitUnderTest.getAllUserLinks(userRepo.getById(1L));
        //then

    }

}