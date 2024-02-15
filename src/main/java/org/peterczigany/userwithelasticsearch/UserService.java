package org.peterczigany.userwithelasticsearch;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserResponseRepository repository;

  @Autowired
  public UserService(UserResponseRepository repository) {
    this.repository = repository;
  }

  public UserResponse createUser(CreateUserRequest createUserRequest) {
    return repository.save(
        new UserResponse(
            UUID.randomUUID(),
            createUserRequest.firstName(),
            createUserRequest.lastName(),
            createUserRequest.email()));
  }

  public List<UserResponse> searchUsers(UserSearchRequest userSearchRequest) {
    return repository.findByFirstAndLastNameUsingCustomQuery(
        userSearchRequest.firstName(), userSearchRequest.lastName());
  }
}
