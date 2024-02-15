package org.peterczigany.userwithelasticsearch;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
class CreateUserAcceptanceTest {
  @Autowired private MockMvc mockMvc;

  /**
   * If correct CreateUserRequest (firstName: string, lastName: string, email: string) is sent to
   * POST '/api/elasticsearch/create', there will be an UserResponse (id: uuid, firstName: string,
   * lastName: string, email: string) in the response.
   */
  @ParameterizedTest
  @CsvFileSource(resources = "/validCreateUserRequestJson_withEmail.csv")
  void givenRequestIsCorrectAndEmailIsGiven_whenRequestIsSent_thenRespondWithUserResponse(
      String createUserRequestJson) throws Exception {

    mockMvc
        .perform(
            post("http://localhost:8080/api/elasticsearch/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserRequestJson))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").isNotEmpty())
        .andExpect(jsonPath("$.firstName").value("Janos"))
        .andExpect(jsonPath("$.lastName").value("Szabo"))
        .andExpect(jsonPath("$.email").value("janos.szabo@szabojanosok.hu"));
  }

  /**
   * If correct CreateUserRequest (firstName: string, lastName: string) is sent to POST
   * '/api/elasticsearch/create', there will be an UserResponse (id: uuid, firstName: string,
   * lastName: string) in the response.
   */
  @ParameterizedTest
  @CsvFileSource(resources = "/validCreateUserRequestJson_withoutEmail.csv")
  void givenRequestIsCorrectAndEmailIsNotGiven_whenRequestIsSent_thenRespondWithUserResponse(
      String createUserRequestJson) throws Exception {

    mockMvc
        .perform(
            post("http://localhost:8080/api/elasticsearch/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserRequestJson))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").isNotEmpty())
        .andExpect(jsonPath("$.firstName").value("Janos"))
        .andExpect(jsonPath("$.lastName").value("Szabo"))
        .andExpect(jsonPath("$.email").doesNotExist());
  }

  /**
   * If the CreateUserRequest (firstName: string, lastName: string, email: string) is not correct,
   * i.e. firstName or lastName is missing, the response should be 400 Bad Request with a message
   * specifying the missing field(s).
   */
  @ParameterizedTest
  @CsvFileSource(resources = "/invalidCreateUserRequestJson.csv")
  void givenRequestIsInCorrect_whenRequestIsSent_thenRespondWith400AndMessage(
      String createUserRequestJson) throws Exception {

    mockMvc
        .perform(
            post("http://localhost:8080/api/elasticsearch/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserRequestJson))
        .andExpect(status().isBadRequest());
    // todo: expect error message
  }
}
