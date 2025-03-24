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
        } while (!validateUserName());
    }

    /**
     * Checks and informs user if their selected username is invalid
     * @return True or false depending on if the users input is valid
     */
    private boolean validateUserName() {
        if (userName.isEmpty()) {
            // No log needed here are the default message already describes the issue.
            return false;
        }
        if (userName.length() > 30) {
            log.info("Username must be 30 or less characters");
            return false;
        }
        if (userName.contains(" ")) {
            log.info("Username cannot contain spaces");
            return false;
        }
        if (userName.toLowerCase().contains("server")) {
            log.info("Username cannot be 'server'");
            return false;
        }
        return true;
    }
}
