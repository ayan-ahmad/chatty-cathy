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

        boolean nameNonEmpty;
        boolean validLength;
        boolean noSpaces;
        boolean notServer;
        boolean validInput;

        do {
            log.info("Please enter a username: ");
            userName = scanner.nextLine();

            nameNonEmpty = !userName.isEmpty();
            validLength = userName.length() <= 30;
            noSpaces = !userName.contains(" ");
            notServer = !userName.toLowerCase().contains("server");

            if (!validLength) {
                log.info("Username must be 30 or less characters");
            }
            if (!noSpaces) {
                log.info("Username cannot contain spaces");
            }
            if (!notServer) {
                log.info("Username cannot be 'server'");
            }

            validInput = nameNonEmpty && validLength && noSpaces && notServer;
        } while (!validInput);
    }
}
