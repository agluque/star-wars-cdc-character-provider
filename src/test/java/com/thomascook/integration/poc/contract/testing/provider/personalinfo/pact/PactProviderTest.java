package com.thomascook.integration.poc.contract.testing.provider.personalinfo.pact;

import au.com.dius.pact.provider.junit.IgnoreNoPactsToVerify;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import au.com.dius.pact.provider.spring.target.SpringBootHttpTarget;
import com.thomascook.integration.poc.contract.testing.provider.personalinfo.ContractTestingProviderPersonalInfoApplication;
import com.thomascook.integration.poc.contract.testing.provider.personalinfo.model.Character;
import com.thomascook.integration.poc.contract.testing.provider.personalinfo.repository.CharacterRepository;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.context.ConfigurableWebApplicationContext;

import java.util.Map;
import java.util.Optional;

@RunWith(SpringRestPactRunner.class)
@Provider("star-wars-cdc-character-provider")
@PactBroker(host = "localhost", port = "80")
//@PactUrl(urls = "http://localhost:8080/contracts/start-wars-consumer-start-wars-provider-personal-info.json")
@IgnoreNoPactsToVerify
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PactProviderTest {

    private static ConfigurableWebApplicationContext application;

    @MockBean
    private CharacterRepository personalInfoRepository;

    @TestTarget
    public final Target target = new SpringBootHttpTarget();

    @BeforeClass
    public static void setUp() {
        application = (ConfigurableWebApplicationContext) SpringApplication.run(ContractTestingProviderPersonalInfoApplication.class);
    }

    @State("A Star Wars character named Luke Skywalker and ID luke")
    public void verifyValidResponse(Map<String, String> providerStateParameters) {
        String id = providerStateParameters.get("id");
        Mockito.when(personalInfoRepository.getById(id)).thenReturn(Optional.of(Character.builder()
                .name("Luke Skywalker")
                .height("172")
                .mass("77")
                .build()));
    }

    @AfterClass
    public static void shutDown() {
        application.stop();
    }

}
