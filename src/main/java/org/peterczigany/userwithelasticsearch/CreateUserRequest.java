package org.peterczigany.userwithelasticsearch;

import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(
    @NotBlank(message = "First name may not be null or empty.") String firstName,
    @NotBlank(message = "Last name may not be null or empty.") String lastName,
    String email) {}
