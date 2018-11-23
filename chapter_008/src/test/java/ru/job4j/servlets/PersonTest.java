package ru.job4j.servlets;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PersonTest {
    Person primary;

    @Before
    public void preparePerson() {
        primary = new Person();
        primary.setFirstname("Roman");
        primary.setSecondname("Bed");
        primary.setSex("M");
        primary.setDescription("Java junior");
    }


    @Test
    public void dataBindJsonTest() throws IOException {
        //Преобразуем в json
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(primary);

        //Из json в объект Java
        Person secondary = mapper.readValue(jsonString, Person.class);
        assertThat(secondary, is(primary));
    }

    @Test
    public void treeModelJsonTest() throws IOException {
        //Преобразуем в json
        OutputStream outputStream = new ByteArrayOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.put("firstname", primary.getFirstname());
        rootNode.put("secondname", primary.getSecondname());
        rootNode.put("sex", primary.getSex());
        rootNode.put("description", primary.getDescription());
        mapper.writeValue(outputStream, rootNode);
        String jsonPerson = outputStream.toString();

        //Из json в объект Java
        JsonNode revRootNode = mapper.readValue(jsonPerson, JsonNode.class);
        String firstname = revRootNode.get("firstname").asText();
        String secondname = revRootNode.get("secondname").asText();
        String sex = revRootNode.get("sex").asText();
        String description = revRootNode.get("description").asText();
        Person secondary = new Person();
        secondary.setFirstname(firstname);
        secondary.setSecondname(secondname);
        secondary.setSex(sex);
        secondary.setDescription(description);
        assertThat(secondary, is(primary));

    }

    @Test
    public void streamingJsonTest() throws IOException {
        //Преобразуем в json
        JsonFactory jsonFactory = new JsonFactory();
        OutputStream outputStream = new ByteArrayOutputStream();
        JsonGenerator jsonGenerator = jsonFactory.createGenerator(outputStream, JsonEncoding.UTF8);
        jsonGenerator.writeStartObject(); // создаем токен начала главного объекта( у нас нет полей объектов)
        jsonGenerator.writeStringField("firstname", primary.getFirstname()); // создаем поле firstname
        jsonGenerator.writeStringField("secondname", primary.getSecondname());
        jsonGenerator.writeStringField("sex", primary.getSex());
        jsonGenerator.writeStringField("description", primary.getDescription());
        jsonGenerator.writeEndObject(); // закрываем главный объект
        jsonGenerator.close();
        String jsonPerson = outputStream.toString();

        //Из json в объект Java
        JsonParser jsonParser = jsonFactory.createParser(jsonPerson);
        String firstname = null;
        String secondname = null;
        String sex = null;
        String description = null;
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String fieldname = jsonParser.getCurrentName();
            if ("firstname".equals(fieldname)) {
                jsonParser.nextToken();
                firstname = jsonParser.getText();
            }
            if ("secondname".equals(fieldname)) {
                jsonParser.nextToken();
                secondname = jsonParser.getText();
            }
            if ("sex".equals(fieldname)) {
                jsonParser.nextToken();
                sex = jsonParser.getText();
            }
            if ("description".equals(fieldname)) {
                jsonParser.nextToken();
                description = jsonParser.getText();
            }
        }
        jsonParser.close();
        Person secondary = new Person();
        secondary.setFirstname(firstname);
        secondary.setSecondname(secondname);
        secondary.setSex(sex);
        secondary.setDescription(description);
        assertThat(secondary, is(primary));

    }

}