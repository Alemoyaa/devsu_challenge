package karate.account;

import com.devsu.llc.microaccountancy.dto.response.AccountDto;
import com.devsu.llc.microaccountancy.dto.response.MessageDto;
import com.devsu.llc.microaccountancy.enums.State;
import com.devsu.llc.microaccountancy.enums.TypeAccount;
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
public class AccountTests {

    private static final int PORT_NUMBER = 8881;

    private static final WireMockServer wireMockServer = new WireMockServer(WireMockConfiguration.options().port(PORT_NUMBER));

    @BeforeClass
    public static void setUp() {
        wireMockServer.start();

        configureFor("localhost", PORT_NUMBER);

        AccountDto accountDto = new AccountDto(1, 123456L, TypeAccount.CORRIENTE, 250.0, State.ACTIVE);
        List<AccountDto> accountDtoList = List.of(accountDto, new AccountDto(2, 123456L, TypeAccount.AHORRO, 550.0, State.ACTIVE));

        stubFor(get(urlEqualTo("/api/cuentas"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(String.valueOf(ResponseEntity.ok().body(accountDtoList)))
                )
        );

        stubFor(get(urlEqualTo("/api/cuentas/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(String.valueOf(ResponseEntity.ok().body(accountDto)))
                )
        );

        stubFor(post(urlEqualTo("/api/cuentas"))
                .withHeader("content-type", equalTo("application/json; charset=UTF-8"))
                .withRequestBody(containing("numberAccount"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(String.valueOf(ResponseEntity.ok().body(accountDto)))
                )
        );

        stubFor(put(urlEqualTo("/api/cuentas/1"))
                .withHeader("content-type", equalTo("application/json; charset=UTF-8"))
                .withRequestBody(containing("amountInitial"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(String.valueOf(ResponseEntity.ok().body(accountDto)))
                )
        );

        stubFor(delete(urlEqualTo("/api/cuentas/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(String.valueOf(new MessageDto("Cuenta eliminada con éxito.")))
                )
        );

    }

    @AfterClass
    public static void tearDown() {
        wireMockServer.stop();
    }

}
