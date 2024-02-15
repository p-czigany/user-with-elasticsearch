package org.peterczigany.userwithelasticsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  private final UserService service;

  @Autowired
  public UserController(UserService service) {
    this.service = service;
  }

  @PostMapping(value = "/api/elasticsearch/create", produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  public UserResponse createUser(@RequestBody @Validated CreateUserRequest createUserRequest) {
    return service.createUser(createUserRequest);
  }
}
