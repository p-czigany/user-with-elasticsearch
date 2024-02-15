package org.peterczigany.userwithelasticsearch;

import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(
    @NotBlank String firstName, @NotBlank String lastName, String email) {}
