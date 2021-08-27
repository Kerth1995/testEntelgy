package com.pruebaEntelgy.jcotrina.pruebaEntelgy.rest;

import com.pruebaEntelgy.jcotrina.pruebaEntelgy.domain.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RestEntelgyTest {

    @InjectMocks
    RestEntelgy restEntelgy;

    @BeforeAll
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void retornarInfoTest(){
        ResponseEntity<Response> response = restEntelgy.retornarInfo();
        assertTrue(response!=null?true:false);
    }
}