package karate;

import com.devsu.llc.microaffiliate.dto.response.CustomerDto;
import com.devsu.llc.microaffiliate.dto.response.MessageDto;
import com.devsu.llc.microaffiliate.enums.State;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.intuit.karate.junit4.Karate;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@RunWith(Karate.class)
public class CustomerTests {

    private static final int PORT_NUMBER = 8881;

    private static final WireMockServer wireMockServer = new WireMockServer(WireMockConfiguration.options().port(PORT_NUMBER));

    @BeforeClass
    public static void setUp() {
        wireMockServer.start();

        configureFor("localhost", PORT_NUMBER);

        List<CustomerDto> customerDtoList = List.of(new CustomerDto(1, "123456", State.ACTIVE), new CustomerDto(2, "123456", State.ACTIVE));


        stubFor(get(urlEqualTo("/api/clientes"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(String.valueOf(ResponseEntity.ok().body(customerDtoList)))
                )
        );

        stubFor(get(urlEqualTo("/api/clientes/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(String.valueOf(ResponseEntity.ok().body(new CustomerDto(1, "123456", State.ACTIVE))))
                )
        );

        stubFor(post(urlEqualTo("/api/clientes"))
                .withHeader("content-type", equalTo("application/json; charset=UTF-8"))
                .withRequestBody(containing("customerId"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(String.valueOf(ResponseEntity.ok().body(new CustomerDto(1, "123456", State.ACTIVE))))
                )
        );

        stubFor(put(urlEqualTo("/api/clientes/1"))
                .withHeader("content-type", equalTo("application/json; charset=UTF-8"))
                .withRequestBody(containing("password"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(String.valueOf(ResponseEntity.ok().body(new CustomerDto(1, "777741", State.ACTIVE))))
                )
        );

        stubFor(delete(urlEqualTo("/api/clientes/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(String.valueOf(new MessageDto("Cliente eliminado con éxito.")))
                )
        );

    }

    @AfterClass
    public static void tearDown() {
        wireMockServer.stop();
    }

}
