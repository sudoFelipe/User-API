package motion.programming.users.handler;

import lombok.RequiredArgsConstructor;
import motion.programming.users.client.IbgeClient;
import motion.programming.users.dto.CityDTO;
import motion.programming.users.dto.StateDTO;
import motion.programming.users.exception.CityNotFoundException;
import motion.programming.users.exception.IbgeIntegrationException;
import motion.programming.users.exception.StateNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
public class IbgeHandler {

    private final IbgeClient client;

    public CityDTO getCity(Integer id) {
        try {
            return client.getCityById(id);
        } catch (WebClientRequestException ex1) {
            throw new IbgeIntegrationException();
        } catch (Exception ex) {
            throw new CityNotFoundException();
        }
    }

    public StateDTO getState(Integer id) {
        try {
            return client.getStateById(id);
        } catch (WebClientRequestException ex1) {
            throw new IbgeIntegrationException();
        } catch (Exception ex) {
            throw new StateNotFoundException();
        }
    }
}
