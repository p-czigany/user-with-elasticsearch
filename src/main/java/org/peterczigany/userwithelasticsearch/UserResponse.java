package org.peterczigany.userwithelasticsearch;

import java.util.UUID;

public record UserResponse(UUID id, String firstName, String lastName, String email) {

  public UserResponse() {
    this(null, null, null, null);
  }
}
