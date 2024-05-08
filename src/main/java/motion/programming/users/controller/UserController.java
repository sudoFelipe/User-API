package motion.programming.users.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import motion.programming.users.converter.UserConverter;
import motion.programming.users.docs.UserDocumentation;
import motion.programming.users.dto.UserRequestDTO;
import motion.programming.users.entity.User;
import motion.programming.users.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/motion/user")
@RequiredArgsConstructor
public class UserController extends UserDocumentation {

    private final UserService service;
    private final UserConverter converter;

    @PostMapping
    public ResponseEntity<Mono<UserResponseDTO>> createUser(@RequestBody @Valid UserRequestDTO requestDTO, UriComponentsBuilder uriBuilder) {
        final var user = service.createUser(requestDTO);
        return ResponseEntity
                .created(uriBuilder.path(URI_API_CREATED).buildAndExpand(user.map(User::getCpf)).toUri())
                .body(converter.toUserDTO(user));
    }

    @GetMapping
    public ResponseEntity<Flux<User>> recoverAllUsers() {
        return ResponseEntity.ok(service.recoverAllUsers());
    }

}
