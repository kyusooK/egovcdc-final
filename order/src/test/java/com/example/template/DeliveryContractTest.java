package com.example.template;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = egovk.OrderApplication.class)
@AutoConfigureMockMvc
@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL, 
                         ids = "egovk:delivery:+:stubs:8090")
@ActiveProfiles("test")                      
public class DeliveryContractTest {

   @Autowired
   MockMvc mockMvc;

    @Test
    public void getDelivery_stub_test() throws Exception {

        MvcResult result = mockMvc
        .perform(MockMvcRequestBuilders.get("/order/validateDeliveries/1")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();

        String responseString = result.getResponse().getContentAsString();
        DocumentContext parsedJson = JsonPath.parse(responseString);
        // and:
        Assertions.assertThat(parsedJson.read("$.deliveryId", String.class)).matches("[\\S\\s]+");
        Assertions.assertThat(parsedJson.read("$.orderId", String.class)).matches("[\\S\\s]+");
        Assertions.assertThat(parsedJson.read("$.productId", String.class)).matches("[\\S\\s]+");
        Assertions.assertThat(parsedJson.read("$.productName", String.class)).matches("[\\S\\s]+");
        Assertions.assertThat(parsedJson.read("$.qty", Integer.class)).isGreaterThanOrEqualTo(0);
    }

}