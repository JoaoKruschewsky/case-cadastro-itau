package com.example.demo.application.service;

import com.example.demo.adapter.dto.ResponserUser;
import com.example.demo.domain.ports.out.H2Manager;
import org.apache.logging.log4j.util.InternalException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static com.example.demo.helpers.HelpersTest.buildRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ManagerUserImplTest {

    @Mock
    private H2Manager h2Manager;

    @InjectMocks
    private ManagerUserImpl managerUser;


    @Test
    void registerWithSuccess() {
        when(h2Manager.saveUser(any(), anyString())).thenReturn(true);

        ResponserUser service = managerUser.registerUser(buildRequest("Joao Pedro Silva"));

        assertNotNull(service);
        assertNotNull(service.loginResponse());
    }
    @Test
    void mustGenerateLoginCaseLetters () {
        when(h2Manager.saveUser(any(), anyString())).thenReturn(true);

        ResponserUser response = managerUser.registerUser(buildRequest("Maria Silva"));

        assertTrue(response.loginResponse().matches("[a-z]{7}"));
    }

    @Test
    void newLoginIfExists() {
        when(h2Manager.saveUser(any(), anyString()))
                .thenReturn(false)
                .thenReturn(true);

        ResponserUser response = managerUser.registerUser(buildRequest("Maria Silva"));

        assertNotNull(response);
    }

    @Test
    void expcetion10Attempts() {
        when(h2Manager.saveUser(any(), anyString())).thenReturn(false);

        assertThrows(InternalException.class, () ->
                managerUser.registerUser(buildRequest("Maria Silva"))
        );
    }

    @Test
    void separateCompoundNameCorrectly() {
        when(h2Manager.saveUser(any(), anyString())).thenReturn(true);

        ResponserUser response = managerUser.registerUser(buildRequest("Joao Pedro Lima"));

        assertNotNull(response);
        assertEquals(7, response.loginResponse().length());
    }

    @Test
    void generateLoginWithFirstNameANDTwoLastNames() {
        when(h2Manager.saveUser(any(), anyString())).thenReturn(true);

        ResponserUser response = managerUser.registerUser(buildRequest("Ana Paula Souza"));

        assertNotNull(response);
        assertTrue(response.loginResponse().matches("[a-z]{7}"));
    }
}