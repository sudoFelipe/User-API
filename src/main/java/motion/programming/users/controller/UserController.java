package motion.programming.users.controller;

import lombok.RequiredArgsConstructor;
import motion.programming.users.converter.UserConverter;
import motion.programming.users.docs.UserDocumentation;
import motion.programming.users.dto.UserRequestDTO;
import motion.programming.users.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.lang.Boolean.TRUE;
import static motion.programming.users.utility.Format.formatAndMaskCpf;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/motion/user")
@RequiredArgsConstructor
public class UserController implements UserDocumentation {

    private final UserService service;
    private final UserConverter converter;

    private static final String URI_API_CREATED = "/api/motion/user/{id}";

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<UserResponseDTO>> createUser(@RequestBody UserRequestDTO requestDTO, UriComponentsBuilder uriBuilder) {
        final var user = service.createUser(requestDTO);

        return user.map(info -> ResponseEntity.created(
                        uriBuilder
                                .path(URI_API_CREATED)
                                .buildAndExpand(user.map(data -> formatAndMaskCpf(data.getCpf(), TRUE)))
                                .toUri())
                .body(converter.toUserDTO(info)));
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Flux<UserResponseDTO> findAllUsers() {
        return service.findUsers().map(this.converter::toUserDTO);
    }

    @GetMapping(value = "/{cpf}", produces = APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<UserResponseDTO>> findUser(@PathVariable String cpf) {
        return service.findUserByCpf(cpf).map(info -> ResponseEntity.ok(this.converter.toUserDTO(info)));
    }
}