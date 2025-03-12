package com.chattycathy.client.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Expandable class for constructing message dataframe to transmit
 */
@AllArgsConstructor // Creates a constructor with all arguments: body
@NoArgsConstructor // Creates a constructor with no arguments
@Getter // Provides getters for all attributes
@Setter // Provides setters for all attributes
public class Message {
    private String userName;
    private String message;
}
