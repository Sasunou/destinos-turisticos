package api_destinos_turisticos.servicio;

import api_destinos_turisticos.dto.TasaCambioRespuesta;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class TasaCambioServicio {

    private static final Logger logger =
            LoggerFactory.getLogger(TasaCambioServicio.class);

    private final RestClient restClient;
    private final MeterRegistry meterRegistry;

    public TasaCambioServicio(
            RestClient restClient,
            MeterRegistry meterRegistry) {

        this.restClient = restClient;
        this.meterRegistry = meterRegistry;
    }

    public TasaCambioRespuesta obtenerTasa(String base, String quote) {

        meterRegistry.counter("tasas.consultadas").increment();

        logger.info("Consultando tasa de cambio {} a {}", base, quote);

        try {
            return restClient.get()
                    .uri("/v2/rate/{base}/{quote}", base, quote)
                    .retrieve()
                    .body(TasaCambioRespuesta.class);

        } catch (Exception e) {
            throw new RuntimeException(
                    "No fue posible obtener la tasa de cambio para "
                            + base + "/" + quote
            );
        }
    }
}