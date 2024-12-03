package com.gft.training;

import com.gft.training.model.User;
import com.gft.training.repository.LocalRepository;
import com.gft.training.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    LocalRepository localRepository;

    @BeforeEach
    void setUp() {
        // Init mocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {

        Map<Integer, User> expected = new HashMap<>();
        expected.put(1, new User("john", 23));
        expected.put(2, new User("jane", 40));
        when(localRepository.findAll()).thenReturn(expected);

        Map<Integer, User> result = userService.listUsers();

        // Assert
        assertNotNull(result);
        assertTrue(result.containsKey(1));
        assertTrue(result.containsValue(new User("john", 23)));
    }

    @Test
    void testAddUser() {
    }}


