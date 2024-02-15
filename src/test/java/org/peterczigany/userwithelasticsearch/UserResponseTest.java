package org.peterczigany.userwithelasticsearch;

import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class UserResponseTest {
  @Test
  void create() {
    UUID uuid = UUID.randomUUID();
    UserResponse userResponse = new UserResponse(uuid, "firstName", "lastName", "optionalEmail");

    Assertions.assertThat(userResponse.id()).isEqualTo(uuid);
    Assertions.assertThat(userResponse.firstName()).isEqualTo("firstName");
    Assertions.assertThat(userResponse.lastName()).isEqualTo("lastName");
    Assertions.assertThat(userResponse.email()).isEqualTo("optionalEmail");
  }
}
