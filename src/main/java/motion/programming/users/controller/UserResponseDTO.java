package motion.programming.users.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserResponseDTO(
        String cpf,
        String name,
        Integer age,
        LocalDate birthday,
        String phone,
        String state,
        String city,
        String address
) { }
