package com.chattycathy.client.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FriendList {
    ArrayList<String> friendList;

    public FriendList() {
        this.friendList = new ArrayList<String>();
        try {
            File list = new File("src/main/resources/friendsList.txt");
            Scanner reader = new Scanner(list);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                this.friendList.add(data);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public ArrayList<String> getFriendsList() {
        return this.friendList;
    }
}
