package org.peterczigany.userwithelasticsearch;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
class UserControllerTest {
  @MockBean private UserService service;
  @Autowired private MockMvc mockMvc;

  @ParameterizedTest
  @CsvFileSource(resources = "/validCreateUserRequestJson_withEmail.csv")
  void Create_a_new_user_with_email_successfully(String createUserRequestJson) throws Exception {

    Mockito.when(service.createUser(any(CreateUserRequest.class)))
        .thenReturn(
            new UserResponse(UUID.randomUUID(), "Janos", "Szabo", "janos.szabo@szabojanosok.hu"));

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

  @ParameterizedTest
  @CsvFileSource(resources = "/validCreateUserRequestJson_withoutEmail.csv")
  void Create_a_new_user_without_email_successfully(String createUserRequestJson) throws Exception {

    Mockito.when(service.createUser(any(CreateUserRequest.class)))
        .thenReturn(new UserResponse(UUID.randomUUID(), "Janos", "Szabo", null));

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

  @ParameterizedTest
  @CsvFileSource(resources = "/invalidCreateUserRequestJson.csv")
  void Respond_with_400_and_error_message_to_incorrect_user_creation_request(
      String createUserRequestJson) throws Exception {

    mockMvc
        .perform(
            post("http://localhost:8080/api/elasticsearch/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserRequestJson))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    // todo: expect error message
  }
}
