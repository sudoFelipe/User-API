package motion.programming.users.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserRequestDTO(

        @NotNull(message = "field CPF required.")
        String cpf,
        @NotNull(message = "field name required.")
        String name,
        @NotNull(message = "field email required.")
        String email,
        @NotNull(message = "field password required.")
        String password,
        @NotNull(message = "field birthday required.")
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate birthday,
        @NotNull(message = "field phone required.")
        String phone,
        @NotNull(message = "field idState required.")
        Integer idState,
        @NotNull(message = "field idCity required.")
        Integer idCity,
        @NotNull(message = "field adress required.")
        String address
) {}
