package br.com.brunojdsz.tools;

import br.com.brunojdsz.api.DailyShareQuote;
import br.com.brunojdsz.api.DailyStockData;
import br.com.brunojdsz.api.StockData;
import br.com.brunojdsz.api.StockResponse;
import br.com.brunojdsz.services.StockService;
import br.com.brunojdsz.settings.ApiSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class StockTools {

    private static final Logger logger = LoggerFactory.getLogger(StockTools.class);

    private RestTemplate restTemplate;

    @Value("${TWELVE_DATA_API_KEY:none}")
    String apiKey;

    public StockTools(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Tool(description = "Latest stock prices")
    public StockResponse getLatestStockPrices(@ToolParam(description = "Name of company") String company){
        logger.info("Get stock prices for: {}", company);

        StockData data = restTemplate
                .getForObject(ApiSettings.TWELVE_DATA_BASE_URL + "?symbol={0}&interval=1day&outputsize=1&format=JSON&apikey={1}",
                        StockData.class,
                        company,
                        apiKey);

        DailyStockData latestData = data.getValues().get(0);

        logger.info("Get stock prices ({}) -> {}", company, latestData);
        return new StockResponse(Float.parseFloat(latestData.getClose()));
    }

    @Tool(description = "Historical daily stock prices")
    public List<DailyShareQuote> getHistoricalStockPrices(@ToolParam(description = "Name of company") String company, @ToolParam(description = "Search period in days") int days){
        logger.info("Get historical stock prices {} for: {}", company, days);

        StockData data = restTemplate
                .getForObject(ApiSettings.TWELVE_DATA_BASE_URL + "?symbol={0}&interval=1day&outputsize={1}&format=JSON&apikey={2}",
                        StockData.class,
                        company,
                        days,
                        apiKey);

        return data.getValues().stream()
                .map(d -> new DailyShareQuote(company, Float.parseFloat(d.getClose()), d.getDatetime()))
                .toList();
    }
}
