package okta.springcloudstreams.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;

@RestController
public class RestResource {

    private static Logger logger = LoggerFactory.getLogger(RestResource.class);

    private final EmitterProcessor<String> streamProcessor = EmitterProcessor.create();

    @GetMapping(value = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getSee() {
        return this.streamProcessor;
    }

    @Bean
    public Consumer<Flux<String>> receiveSse() {
        return recordFlux ->
                recordFlux
                        .doOnNext(this.streamProcessor::onNext)
                        .doOnNext(value -> logger.info("*" +value))
                        .subscribe();
    }
}