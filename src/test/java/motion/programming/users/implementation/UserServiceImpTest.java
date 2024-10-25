package motion.programming.users.implementation;

import motion.programming.users.apis.IbgeHandler;
import motion.programming.users.converter.UserConverter;
import motion.programming.users.dto.*;
import motion.programming.users.entity.User;
import motion.programming.users.exception.UserNotFoundException;
import motion.programming.users.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(SpringExtension.class)
class UserServiceImpTest {

    @InjectMocks
    private UserServiceImp service;

    @Mock
    private UserConverter converter;

    @Mock
    private IbgeHandler handler;

    @Mock
    private UserRepository repository;

    private final User user = UserCreator.createValidUser();
    private final StateDTO state = HandlerCreator.createState();
    private final CityDTO city = HandlerCreator.createCity();


    @Test
    @DisplayName("Return user's flux")
    void findAllUsersSucessfully() {
        BDDMockito.when(repository.findAll()).thenReturn(Flux.just(user));
        StepVerifier.create(service.findUsers())
                .expectSubscription()
                .expectNext(user)
                .verifyComplete();
    }

    @Test
    @DisplayName("Return user mono")
    void findUserByCpfSucessfully() {
        BDDMockito.when(repository.findById(anyString())).thenReturn(Mono.just(user));
        final var cpf = "12345678901";
        StepVerifier.create(service.findUserByCpf(cpf))
                .expectSubscription()
                .expectNext(user)
                .verifyComplete();
    }

    @Test
    @DisplayName("Return mono empty")
    void findUserByCpfWhenNotFound() {
        BDDMockito.when(repository.findById(anyString()))
                .thenReturn(Mono.empty());
        StepVerifier.create(service.findUserByCpf("1"))
                .expectSubscription()
                .expectError(UserNotFoundException.class)
                .verify();
    }

    @Test
    @DisplayName("Save user mono")
    void saveUserSucessfully() {
        final var userExpect = user;
        userExpect.setState(state);
        userExpect.setCity(city);
        BDDMockito.when(handler.getState(any())).thenReturn(state);
        BDDMockito.when(handler.getCity(any())).thenReturn(city);
        BDDMockito.when(converter.toSaveUser(any())).thenReturn(user);
        BDDMockito.when(repository.findById(anyString())).thenReturn(Mono.empty());
        BDDMockito.when(repository.save(any())).thenReturn(Mono.just(user));
        StepVerifier.create(service.createUser(
                UserRequestDTO.builder()
                        .cpf("123456789")
                        .build()))
                .expectSubscription()
                .expectNext(userExpect)
                .verifyComplete();
    }
}