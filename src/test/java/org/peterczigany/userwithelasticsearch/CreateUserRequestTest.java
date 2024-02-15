package org.peterczigany.userwithelasticsearch;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CreateUserRequestTest {
  @Test
  void create() {
    CreateUserRequest createUserRequest =
        new CreateUserRequest("firstName", "lastName", "optionalEmail");

    assertThat(createUserRequest.firstName()).isEqualTo("firstName");
    assertThat(createUserRequest.lastName()).isEqualTo("lastName");
    assertThat(createUserRequest.email()).isEqualTo("optionalEmail");
  }
}
