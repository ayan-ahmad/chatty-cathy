package com.chattycathy.server.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Used to hold poster data
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Poster")
public class Poster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posterId")
    private int posterId;

    @Column(name = "userName")
    private String userName;

    @ManyToMany
    @JoinTable(
            name = "Posts",
            joinColumns = { @JoinColumn(name = "posterId") },
            inverseJoinColumns = { @JoinColumn(name = "postId") }
    )
    Set<Post> posts = new HashSet<>();

    @ManyToMany
    @JoinTable(name="Follows",
            joinColumns=@JoinColumn(name="follower"),
            inverseJoinColumns=@JoinColumn(name="followed")
    )
    private Set<Poster> following = new HashSet<>();

    @ManyToMany
    @JoinTable(name="Follows",
            joinColumns=@JoinColumn(name="followed"),
            inverseJoinColumns=@JoinColumn(name="follower")
    )
    private Set<Poster> followedBy = new HashSet<>();

    public Poster(String userName) {
        this.userName = userName;
    }
}
