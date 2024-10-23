package motion.programming.users.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import motion.programming.users.controller.UserResponseDTO;
import motion.programming.users.dto.LoginRequestDTO;
import motion.programming.users.dto.LoginResponseDTO;
import motion.programming.users.dto.UserRequestDTO;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static motion.programming.users.utility.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Management Users", description = "Grouping of endpoints to manage users.")
public interface UserDocumentation {

    @Operation(summary = "Create user", description = "Create a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = CREATED, description = CREATED_CODE, content =
                    { @Content(mediaType = APPLICATION_JSON_VALUE, schema =
                    @Schema(implementation = UserResponseDTO.class)) }),
            @ApiResponse(responseCode = NOT_FOUND, description = NOT_FOUND_CODE, content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = BAD_REQUEST, description = BAD_REQUEST_CODE, content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = INTERNAL_SERVER_ERROR_CODE, content = @Content(mediaType = APPLICATION_JSON_VALUE))})
    public Mono<ResponseEntity<UserResponseDTO>> createUser(@RequestBody @Valid UserRequestDTO requestDTO, UriComponentsBuilder uriBuilder);

    @Operation(summary = "Login", description = "User's login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SUCCESS, description = SUCCESS_CODE, content =
                    { @Content(mediaType = APPLICATION_JSON_VALUE, schema =
                    @Schema(implementation = LoginResponseDTO.class)) }),
            @ApiResponse(responseCode = NOT_FOUND, description = NOT_FOUND_CODE, content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = BAD_REQUEST, description = BAD_REQUEST_CODE, content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = INTERNAL_SERVER_ERROR_CODE, content = @Content(mediaType = APPLICATION_JSON_VALUE))})
    public Mono<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO);

    @Operation(summary = "Recover all users", description = "List of users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SUCCESS, description = SUCCESS_CODE, content =
                    { @Content(mediaType = APPLICATION_JSON_VALUE, schema =
                    @Schema(implementation = UserResponseDTO.class)) }),
            @ApiResponse(responseCode = NOT_FOUND, description = NOT_FOUND_CODE, content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = BAD_REQUEST, description = BAD_REQUEST_CODE, content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = INTERNAL_SERVER_ERROR_CODE, content = @Content(mediaType = APPLICATION_JSON_VALUE))})
    public Flux<UserResponseDTO> findAllUsers();

    @Operation(summary = "Recover user by CPF", description = "Get user by CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SUCCESS, description = SUCCESS_CODE, content =
                    { @Content(mediaType = APPLICATION_JSON_VALUE, schema =
                    @Schema(implementation = UserResponseDTO.class)) }),
            @ApiResponse(responseCode = NOT_FOUND, description = NOT_FOUND_CODE, content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = BAD_REQUEST, description = BAD_REQUEST_CODE, content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = INTERNAL_SERVER_ERROR_CODE, content = @Content(mediaType = APPLICATION_JSON_VALUE))})
    public Mono<ResponseEntity<UserResponseDTO>> findUser(@CPF String cpf);

    @Operation(summary = "Recover user by UF", description = "Get user by UF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SUCCESS, description = SUCCESS_CODE, content =
                    { @Content(mediaType = APPLICATION_JSON_VALUE, schema =
                    @Schema(implementation = UserResponseDTO.class)) }),
            @ApiResponse(responseCode = NOT_FOUND, description = NOT_FOUND_CODE, content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = BAD_REQUEST, description = BAD_REQUEST_CODE, content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = INTERNAL_SERVER_ERROR_CODE, content = @Content(mediaType = APPLICATION_JSON_VALUE))})
    public Flux<UserResponseDTO> findUserByUf(Integer uf);
}
