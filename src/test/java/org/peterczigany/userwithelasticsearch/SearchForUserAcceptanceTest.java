package org.peterczigany.userwithelasticsearch;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class SearchForUserAcceptanceTest {
  @Autowired private MockMvc mockMvc;

  /**
   * If correct UserSearchRequest (firstName: string, lastName: string) is sent to POST
   * '/api/elasticsearch/search', the response is a UserSearchResponse (total: long [number of
   * matches], userList: List<UserResponse> [user objects matching the search criteria]).
   */
  @ParameterizedTest
  @CsvFileSource(resources = "/validUserSearchRequestJson.csv")
  void givenRequestIsCorrect_whenRequestIsSent_thenRespondWithUserSearchResponse(
      String userSearchRequestJson) throws Exception {
    mockMvc
        .perform(
            post("http://localhost:8080/api/elasticsearch/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userSearchRequestJson))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  /**
   * If the UserSearchRequest (firstName: string, lastName: string) is not correct, i.e. firstName
   * or lastName is missing, the response should be 400 Bad Request with a message specifying the
   * missing field(s).
   */
  @ParameterizedTest
  @CsvFileSource(resources = "/invalidUserSearchRequestJson.csv")
  void givenRequestIsInCorrect_whenRequestIsSent_thenRespondWith400AndMessage(
      String userSearchRequestJson) throws Exception {
    mockMvc
        .perform(
            post("http://localhost:8080/api/elasticsearch/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userSearchRequestJson))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    // todo: expect specific error message
  }
}
