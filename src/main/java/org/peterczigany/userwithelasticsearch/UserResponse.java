package org.peterczigany.userwithelasticsearch;

import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "user")
public record UserResponse(
    @Id UUID id,
    @Field(name = "firstName", type = FieldType.Text) String firstName,
    @Field(name = "lastName", type = FieldType.Text) String lastName,
    @Field(name = "email") String email) {}
