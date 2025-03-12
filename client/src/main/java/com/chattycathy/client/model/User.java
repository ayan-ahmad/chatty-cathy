package com.chattycathy.client.model;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * Tracks user details, handles input of user details.
 */
@Getter
@Slf4j
public class User {
    String userName;

    /**
     * Sets userName to a non-empty String
     * @param scanner takes input for userName
     */
    public User(Scanner scanner) {
        do {
            log.info("Please enter a username: ");
            userName = scanner.nextLine();
        } while (userName.isEmpty());
    }
}
