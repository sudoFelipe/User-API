package motion.programming.users.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import motion.programming.users.controller.UserResponseDTO;
import motion.programming.users.dto.UserRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import static motion.programming.users.utility.HttpStatus.*;
import static motion.programming.users.utility.HttpStatus.NOT_FOUND_CODE;

@Tag(name = "Management Users", description = "Grouping of endpoints to manage users.")
public interface UserDocumentation {

    @Operation(summary = "Create user", description = "Create a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = CREATED, description = CREATED_CODE),
            @ApiResponse(responseCode = NOT_FOUND, description = NOT_FOUND_CODE),
            @ApiResponse(responseCode = BAD_REQUEST, description = BAD_REQUEST_CODE),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = INTERNAL_SERVER_ERROR_CODE)})
    public ResponseEntity<Mono<UserResponseDTO>> createUser(@RequestBody @Valid UserRequestDTO requestDTO, UriComponentsBuilder uriBuilder);
}
