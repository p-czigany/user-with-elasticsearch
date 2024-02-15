package org.peterczigany.userwithelasticsearch;

import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document(indexName = "user")
public record UserResponse(
    @Id UUID id,
    @Field(name = "firstName") String firstName,
    @Field(name = "lastName") String lastName,
    @Field(name = "email") String email) {}
