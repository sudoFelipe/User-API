package motion.programming.users.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import motion.programming.users.converter.UserConverter;
import motion.programming.users.dto.UserRequestDTO;
import motion.programming.users.entity.User;
import motion.programming.users.exception.CityNotFoundException;
import motion.programming.users.exception.SaveUserException;
import motion.programming.users.exception.StateNotFoundException;
import motion.programming.users.handler.IbgeHandler;
import motion.programming.users.repository.UserRepository;
import motion.programming.users.service.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository repository;
    private final UserConverter converter;
    private final IbgeHandler handler;

    @Override
    public Mono<User> createUser(UserRequestDTO request) {

        return repository.findById(request.cpf())
                .flatMap(user -> repository.save(converter.toUpdateUser(request, user)))
                .switchIfEmpty(validateAndSaveUser(request));
    }

    private Mono<User> validateAndSaveUser(UserRequestDTO request) {

        return Mono.just(converter.toSaveUser(request))
                .flatMap(user -> {
                    user.setState(handler.getState(request.idState()));
                    user.setCity(handler.getCity(request.idCity()));

                    return repository.save(user);
                })
                .doOnError(error -> new SaveUserException());
    }

    @Override
    public Flux<User> recoverAllUsers() {
        return repository.findAll();
    }
}
