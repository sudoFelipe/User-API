package motion.programming.users.handler;

import lombok.RequiredArgsConstructor;
import motion.programming.users.client.IbgeClient;
import motion.programming.users.dto.CityDTO;
import motion.programming.users.dto.StateDTO;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
public class IbgeHandler {

    private final IbgeClient client;

    public Mono<CityDTO> getCity(Integer id) {
        try {
            return client.getCityById(id);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    public Mono<StateDTO> getState(Integer id) {
        try {
            return client.getStateById(id);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }
}
