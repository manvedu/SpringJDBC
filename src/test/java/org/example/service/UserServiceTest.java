package org.example.service;

import org.example.dao.UserDao;
import org.example.model.User;

import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import org.mockito.ArgumentCaptor;
import static org.junit.Assert.*;

public class UserServiceTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService userService;
    private User mockedUser;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockedUser = new User(1L, "Michael Jackson", "mjackson@example.com");
    }

    @Test
    public void testCreateUser() {
        Long userId = 1L;
        String userName = "Michael Jackson";
        String email = "mjackson@example.com";

        userService.createUser(userId, userName, email);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userDao, times(1)).save(userCaptor.capture());

        User capturedUser = userCaptor.getValue();

        assertEquals(userId, capturedUser.getId());
        assertEquals(userName, capturedUser.getName());
    }

    @Test
    public void testGetUserById() {
        String userName = "Michael Jackson";
        Long userId = 1L;
        User user = new User(userId, userName, "mjackson@example.com");
        when(userDao.getById(userId)).thenReturn(user);

        User previousUser = userService.getUser(userId);
        System.out.println(previousUser + "the namecito " + previousUser.getName() );

        assertEquals(userName, previousUser.getName());
        verify(userDao, times(1)).getById(1L);
    }
}
