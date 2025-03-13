package com.chattycathy.client.model;


import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserTest {
    @Test
    void testCreateWithRandomString() {
        String userName = "windmill";

        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine()).thenReturn(userName);

        User user = new User(scanner);

        assertEquals(userName, user.getUserName());
    }

    @Test
    void testCreateWithEmptyStringNotAccepted() {
        String emptyInput = "";
        String userName = "groovy";

        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine()).thenReturn(emptyInput, emptyInput, userName);

        User user = new User(scanner);

        assertEquals(userName, user.getUserName());
    }
}
