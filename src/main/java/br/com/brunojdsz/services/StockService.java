package br.com.brunojdsz.services;

import br.com.brunojdsz.api.DailyStockData;
import br.com.brunojdsz.api.StockData;
import br.com.brunojdsz.api.StockRequest;
import br.com.brunojdsz.api.StockResponse;
import br.com.brunojdsz.settings.ApiSettings;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.function.Function;
import org.slf4j.Logger;
import org.springframework.web.client.RestTemplate;

public class StockService implements Function<StockRequest, StockResponse> {


    private static final Logger logger = LoggerFactory.getLogger(StockService.class);

    private RestTemplate restTemplate;

    @Value("${TWELVE_DATA_API_KEY:none}")
    String apiKey;

    public StockService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public StockResponse apply(StockRequest stockRequest) {

        StockData data = restTemplate
                .getForObject(ApiSettings.TWELVE_DATA_BASE_URL + "?symbol={0}&interval=1day&outputsize=1&format=JSON&apikey={1}",
                        StockData.class,
                        stockRequest.company(),
                        apiKey);

        DailyStockData latestData = data.getValues().get(0);
        return new StockResponse(Float.parseFloat(latestData.getClose()));
    }
}
