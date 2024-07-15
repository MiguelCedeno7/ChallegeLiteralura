package com.aluracursos.literatura.util;
import com.aluracursos.literatura.model.RecordLibro;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

public class JsonParse {
    private ObjectMapper objectMapper = new ObjectMapper();


    public RecordLibro parsearLibro(String json) {
        try {
            return objectMapper.readValue(json, RecordLibro.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public List<RecordLibro> parsearLibros(String json) {
        List<RecordLibro> lista = new ArrayList<>();
        try {

            JsonNode jsonObject = objectMapper.readTree(json);
            JsonNode resultados = jsonObject.get("results");

            for (JsonNode node : (ArrayNode) resultados) {
                RecordLibro libro = objectMapper.treeToValue(node, RecordLibro.class);
                lista.add(libro);
            }

            return lista;

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
