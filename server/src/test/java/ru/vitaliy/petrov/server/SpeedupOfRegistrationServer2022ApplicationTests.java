package ru.vitaliy.petrov.server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
class SpeedupOfRegistrationServer2022ApplicationTests {

    private String jwtToken;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .param("phoneNumber", "89215814330")
                        .param("password", "1234"))
                .andReturn();
        String resultString = result.getResponse().getContentAsString();
        jwtToken = resultString.substring(8, resultString.length() - 2);
    }

    @Test
    void registerNotCorrectUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .param("phoneNumber", "89215814329")
                        .param("password", "1234")
                        .param("roleName", "NOT-CORRECT"))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }

    @Test
    void registerWithNotValidData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .param("phoneNumber", "89215814329")
                        .param("roleName", "NOT-CORRECT"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    void registerCorrectUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .param("phoneNumber", "89215814329")
                        .param("password", "1234")
                        .param("roleName", "USER"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.jwt").isString());
    }

    @Test
    void correctUpdateUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/update-user")
                        .param("phoneNumber", "89215814329")
                        .param("password", "1236"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.jwt").isString());
    }

    @Test
    void notValidUpdateUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/update-user")
                        .param("phoneNumber", "89215814329"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    void notCorrectUpdateUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/update-user")
                        .param("phoneNumber", "89215814331")
                        .param("password", "1236"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    void notValidDeleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/delete-user"))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }

    @Test
    void correctLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .param("phoneNumber", "89215814330")
                        .param("password", "1234"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.jwt").isString());
    }

    @Test
    void notCorrectLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .param("phoneNumber", "89215814330")
                        .param("password", "1233"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    void notValidLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .param("phoneNumber", "89215814330"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    void testValidate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/validate")
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.jwt").isString());
    }

    @Test
    void testValidateWithoutHeader() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/validate"))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }

    @Test
    void checkConfirmationCode() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/get-confirmation-code")
                        .param("phoneNumber", "89215814348"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String code = result.getResponse().getContentAsString().substring(19, 26);
        mockMvc.perform(MockMvcRequestBuilders.get("/check-confirmation-code")
                        .param("phoneNumber", "89215814348")
                        .param("confirmationCode", code))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void getUserRole() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/get-user-role")
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.response").value("USER"));
    }

    @Test
    void tryChangeUserState() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/change-state")
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

}
