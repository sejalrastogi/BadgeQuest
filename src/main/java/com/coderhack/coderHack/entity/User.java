package com.coderhack.coderHack.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document(collection = "contest-users")
@Data
public class User {

    @Id
    private ObjectId id;

    @NonNull
    private String userName;

    private int score = 0;

    private Set<Badge> badges = new HashSet<>(); // as a user can have unique badges only

}
