package motion.programming.users.service;

import motion.programming.users.controller.UserResponseDTO;
import motion.programming.users.dto.UserRequestDTO;
import motion.programming.users.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<User> createUser(UserRequestDTO request);
    Flux<User> findUsers();
    Mono<User> findUserByCpf(String cpf);
    Flux<User> findUserByState(Integer uf);
}
