package motion.programming.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record CityDTO(
    Integer id,
    @JsonProperty("nome")
    String name
) {}
