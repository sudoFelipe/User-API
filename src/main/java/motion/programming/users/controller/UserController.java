package motion.programming.users.controller;

import lombok.RequiredArgsConstructor;
import motion.programming.users.converter.UserConverter;
import motion.programming.users.docs.UserDocumentation;
import motion.programming.users.dto.LoginRequestDTO;
import motion.programming.users.dto.LoginResponseDTO;
import motion.programming.users.dto.UserRequestDTO;
import motion.programming.users.security.TokenProvider;
import motion.programming.users.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/motion/user")
@RequiredArgsConstructor
public class UserController implements UserDocumentation {

    private final UserService service;
    private final UserConverter converter;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final ReactiveUserDetailsService userDetailsService;

    private static final String URI_API_CREATED = "/api/motion/user/{id}";

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<UserResponseDTO>> createUser(@RequestBody UserRequestDTO requestDTO, UriComponentsBuilder uriBuilder) {
        return service.createUser(requestDTO).map(info -> ResponseEntity.created(uriBuilder
                        .path(URI_API_CREATED).buildAndExpand(info.getCpf()).toUri())
                        .body(converter.toUserDTO(info)));
    }


    @PostMapping(value = "/login",consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Mono<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return userDetailsService.findByUsername(loginRequestDTO.username())
                .filter(user -> passwordEncoder.matches(loginRequestDTO.password(), user.getPassword()))
                .map(tokenProvider::generateToken)
                .map(LoginResponseDTO::new)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED)));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_USER')")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Flux<UserResponseDTO> findAllUsers() {
        return service.findUsers().map(this.converter::toUserDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_USER')")
    @GetMapping(value = "/{cpf}", produces = APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<UserResponseDTO>> findUser(@PathVariable String cpf) {
        return service.findUserByCpf(cpf).map(info -> ResponseEntity.ok(this.converter.toUserDTO(info)));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_USER')")
    @GetMapping(value = "/location/{uf}", produces = APPLICATION_JSON_VALUE)
    public Flux<UserResponseDTO> findUserByUf(@PathVariable Integer uf) {
        return service.findUserByState(uf).map(this.converter::toUserDTO);
    }
}