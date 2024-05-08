package motion.programming.users.config;

import motion.programming.users.client.IbgeClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
public class WebClientConfig implements WebFluxConfigurer {

    @Bean
    IbgeClient getClientIbge(WebClient.Builder builder, @Value("${client.url.ibge}") String baseUrl, ExchangeStrategies strategy) {

        return getClientAdapter(builder, baseUrl, strategy).createClient(IbgeClient.class);
    }

    @Bean
    public ExchangeStrategies getStrategy() {
        final var size = 16 * 1024 * 1024;
        return ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();
    }

    private static HttpServiceProxyFactory getClientAdapter(WebClient.Builder builder, String baseUrl, ExchangeStrategies strategy) {
        return HttpServiceProxyFactory.builderFor(
                WebClientAdapter.create(getWebClient(builder, baseUrl, strategy))
        ).build();
    }

    private static WebClient getWebClient(WebClient.Builder builder, String baseUrl, ExchangeStrategies strategy) {
        return builder.baseUrl(baseUrl)
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .exchangeStrategies(strategy)
                .build();
    }
}
