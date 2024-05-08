package motion.programming.users.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import motion.programming.users.converter.UserConverter;
import motion.programming.users.dto.CityDTO;
import motion.programming.users.dto.UserRequestDTO;
import motion.programming.users.entity.User;
import motion.programming.users.handler.IbgeHandler;
import motion.programming.users.repository.UserRepository;
import motion.programming.users.service.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository repository;
    private final UserConverter converter;
    private final IbgeHandler handler;

    @Override
    public Mono<User> createUser(UserRequestDTO request) {

        final var locality = Mono.zip(handler.getCity(request.idCity()), handler.getState(request.idState()));

        return repository.findById(request.cpf())
                .flatMap(user -> repository.save(converter.toUpdateUser(request, user)))
                .switchIfEmpty(repository.save(converter.toSaveUser(request)));
    }

    @Override
    public Flux<User> recoverAllUsers() {
        return repository.findAll();
    }
}
