package org.peterczigany.userwithelasticsearch;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class SearchForUserAcceptanceTest {
  @Autowired MockMvc mockMvc;

  /**
   * If correct UserSearchRequest (firstName: string, lastName: string) is sent to POST
   * '/api/elasticsearch/search', the response is a UserSearchResponse (total: long [number of
   * matches], userList: List<UserResponse> [user objects matching the search criteria]).
   */
  @Test
  void givenRequestIsCorrect_whenRequestIsSent_thenRespondWithUserSearchResponse() {}

  /**
   * If the UserSearchRequest (firstName: string, lastName: string) is not correct, i.e. firstName
   * or lastName is missing, the response should be 400 Bad Request with a message specifying the
   * missing field(s).
   */
  @Test
  void givenRequestIsInCorrect_whenRequestIsSent_thenRespondWith400AndMessage() {}
}
