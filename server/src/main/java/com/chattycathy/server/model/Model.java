package com.chattycathy.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A generic, proof-of-concept POJO to test object encoding and decoding
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Model {
    private String name;
    private String message;
}