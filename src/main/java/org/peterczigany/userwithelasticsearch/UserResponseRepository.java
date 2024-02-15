package org.peterczigany.userwithelasticsearch;

import java.util.List;
import java.util.UUID;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserResponseRepository extends ElasticsearchRepository<UserResponse, UUID> {
  @Query(
      "{\"query\": {\"bool\": {\"must\": [{\"wildcard\": {\"firstName\": \"*?0*\"}},{\"wildcard\": {\"lastName\": \"*?1*\"}}]}}}")
  List<UserResponse> findByFirstAndLastNameUsingCustomQuery(String firstName, String lastName);
}
