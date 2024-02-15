package org.peterczigany.userwithelasticsearch;

import org.springframework.stereotype.Service;

@Service
public class UserService {

  public UserResponse createUser(CreateUserRequest createUserRequest) {
    return new UserResponse();
  }
}
