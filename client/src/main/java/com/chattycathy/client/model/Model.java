package com.chattycathy.client.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A generic, proof-of-concept POJO to test object encoding and decoding
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Model {
    private String name;
    private String message;
}