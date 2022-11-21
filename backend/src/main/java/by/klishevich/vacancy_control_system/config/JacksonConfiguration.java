package by.klishevich.vacancy_control_system.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@AllArgsConstructor
public class JacksonConfiguration {
    private final ObjectMapper objectMapper;

    @PostConstruct
    ObjectMapper jacksonObjectMapper() {
        objectMapper.registerModule(new JsonNullableModule());
        return objectMapper;
    }
}
