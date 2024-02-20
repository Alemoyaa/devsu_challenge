package karate.movement;

import com.devsu.llc.microaccountancy.dto.response.MessageDto;
import com.devsu.llc.microaccountancy.dto.response.MovementDto;
import com.devsu.llc.microaccountancy.enums.TypeMovement;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.intuit.karate.junit4.Karate;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@RunWith(Karate.class)
public class MovementTests {

    private static final int PORT_NUMBER = 8881;

    private static final WireMockServer wireMockServer = new WireMockServer(WireMockConfiguration.options().port(PORT_NUMBER));

    @BeforeClass
    public static void setUp() {
        wireMockServer.start();

        configureFor("localhost", PORT_NUMBER);

        LocalDate localDate = LocalDate.of(2024, 2, 19);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());


        MovementDto movementDto = new MovementDto(1, date, TypeMovement.INGRESO, 177.1, 755.0, 2);
        List<MovementDto> movementDtoList = List.of(movementDto, new MovementDto(2, date, TypeMovement.EGRESO, 177.1, 755.0, 2));


        stubFor(get(urlEqualTo("/api/movimientos"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(String.valueOf(ResponseEntity.ok().body(movementDtoList)))
                )
        );

        stubFor(get(urlEqualTo("/api/movimientos/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(String.valueOf(ResponseEntity.ok().body(movementDto)))
                )
        );

        stubFor(post(urlEqualTo("/api/movimientos"))
                .withHeader("content-type", equalTo("application/json; charset=UTF-8"))
                .withRequestBody(containing("customerId"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(String.valueOf(ResponseEntity.ok().body(movementDto)))
                )
        );

        stubFor(delete(urlEqualTo("/api/movimientos/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(String.valueOf(new MessageDto("Movimiento eliminado con éxito.")))
                )
        );

    }

    @AfterClass
    public static void tearDown() {
        wireMockServer.stop();
    }

}
