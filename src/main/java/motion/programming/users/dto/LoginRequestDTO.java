package motion.programming.users.dto;

import jakarta.validation.constraints.NotNull;

public record LoginRequestDTO(
        @NotNull
        String username,

        @NotNull
        String password) {
}
