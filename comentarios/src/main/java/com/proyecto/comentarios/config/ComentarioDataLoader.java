package com.proyecto.comentarios.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.comentarios.entity.Comentario;
import com.proyecto.comentarios.repository.ComentarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class ComentarioDataLoader implements CommandLineRunner {

    private final ComentarioRepository comentarioRepository;
    private final ObjectMapper objectMapper;

    public ComentarioDataLoader(
            ComentarioRepository comentarioRepository,
            ObjectMapper objectMapper) {

        this.comentarioRepository = comentarioRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {

        if (comentarioRepository.count() > 0) {
            return;
        }

        ClassPathResource resource =
                new ClassPathResource("BD_Config/comentarios.json");

        try (InputStream inputStream = resource.getInputStream()) {

            List<Comentario> comentarios =
                    objectMapper.readValue(
                            inputStream,
                            new TypeReference<List<Comentario>>() {
                            }
                    );

            comentarioRepository.saveAll(comentarios);
        }
    }
}
