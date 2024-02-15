package org.peterczigany.userwithelasticsearch;

import jakarta.validation.constraints.NotBlank;

public record UserSearchRequest(
    @NotBlank(message = "First name may not be null or empty.") String firstName,
    @NotBlank(message = "First name may not be null or empty.") String lastName) {}
