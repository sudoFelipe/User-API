package motion.programming.users.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Builder
public record UserResponseDTO(
        String cpf,
        String name,
        Integer age,
        @JsonFormat(shape = STRING, pattern="dd/MM/yyyy")
        LocalDate birthday,
        String phone,
        String state,
        String city,
        String address
) { }
