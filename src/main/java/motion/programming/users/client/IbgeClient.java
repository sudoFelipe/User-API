package motion.programming.users.client;

import motion.programming.users.dto.CityDTO;
import motion.programming.users.dto.StateDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Mono;

public interface IbgeClient {

    @GetExchange("/municipios/{id}")
    CityDTO getCityById(@PathVariable Integer id);

    @GetExchange("/estados/{id}")
    StateDTO getStateById(@PathVariable Integer id);
}
