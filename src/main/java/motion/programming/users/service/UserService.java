package motion.programming.users.service;

import motion.programming.users.controller.UserResponseDTO;
import motion.programming.users.dto.UserRequestDTO;
import motion.programming.users.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<User> createUser(UserRequestDTO request);
    Flux<User> findUsers();
    Mono<User> findUserByCpf(String cpf);
}
