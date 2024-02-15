package org.peterczigany.userwithelasticsearch;

import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "user")
public record UserResponse(@Id UUID id, String firstName, String lastName, String email) {}
