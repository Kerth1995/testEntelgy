package com.pruebaEntelgy.jcotrina.pruebaEntelgy.rest;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pruebaEntelgy.jcotrina.pruebaEntelgy.domain.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController()
@RequestMapping("/prueba")
public class RestEntelgy {

    @Value("${entelgy.url.end_point}")
    private String URL;

    private static final Logger LOGGER = LoggerFactory.getLogger(RestEntelgy.class);

    @GetMapping("/")
    public ResponseEntity<Response> retornarInfo(){

        LOGGER.info(URL);

        RestTemplate restTemplate = new RestTemplate();
        Response response = null;
        HttpStatus httpStatus;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            ResponseEntity<Response> responseData  = restTemplate.exchange("https://reqres.in/api/users", HttpMethod.GET, entity, Response.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode body = (mapper.valueToTree(responseData)).get("body");
            LOGGER.info(body.toString());
            response = mapper.reader().forType(Response.class).readValue(body);
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception exception){
            LOGGER.error(exception.getCause().toString());
            LOGGER.error(exception.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(response, httpStatus);

    }

}
