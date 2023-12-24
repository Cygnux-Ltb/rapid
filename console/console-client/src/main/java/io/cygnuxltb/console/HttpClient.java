package io.cygnuxltb.console;

import io.cygnuxltb.protocol.http.ServiceURI;
import io.cygnuxltb.protocol.http.response.BarDTO;
import io.cygnuxltb.protocol.http.response.OrderDTO;
import io.cygnuxltb.protocol.http.response.ParamDTO;
import io.cygnuxltb.protocol.http.response.PnlDTO;
import io.cygnuxltb.protocol.http.response.ProductDTO;
import io.cygnuxltb.protocol.http.response.StrategyDTO;
import io.mercury.common.http.PathParams.PathParam;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.sys.SysProperties;
import org.slf4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public final class HttpClient extends BaseHttpClient {

    private static final Logger log = Log4j2LoggerFactory.getLogger(BaseHttpClient.class);

    private String sysUri;
    private String barUri;
    private String pnlUri;
    private String orderUri;
    private String strategyUri;


    private HttpClient() {
        var prop = new Properties();
        try {
            if (SysProperties.USER_HOME == null) {
                prop.load(HttpClient.class.getResourceAsStream("config.properties"));
            } else {
                FileInputStream fileInputStream = new FileInputStream(
                        STR."\{SysProperties.USER_HOME}/config/config.properties");
                prop.load(fileInputStream);
            }
            var baseUrl = prop.getProperty("baseUrl", "http://127.0.0.1:8088/");
            this.sysUri = baseUrl + ServiceURI.SYS;
            this.barUri = baseUrl + ServiceURI.BAR;
            this.pnlUri = baseUrl + ServiceURI.PNL;
            this.orderUri = baseUrl + ServiceURI.ORDER;
            this.strategyUri = baseUrl + ServiceURI.STRATEGY;
        } catch (IOException e) {
            log.error("read config file has IOException -> {}", e.getMessage(), e);
        }
    }

    public List<BarDTO> getBars(int sysId, String instrumentCode, int tradingDay) {
        return sendGetRequest(BarDTO.class, barUri,
                new PathParam("sysId", sysId),
                new PathParam("instrumentId", instrumentCode),
                new PathParam("tradingDay", tradingDay));
    }


    public List<ProductDTO> getAllSysInfo() {
        return sendGetRequest(ProductDTO.class, sysUri);
    }

    public List<ProductDTO> getSysInfoById(int sysId) {
        return sendGetRequest(ProductDTO.class, sysUri,
                new PathParam("sysId", Integer.toString(sysId)));
    }

    public List<StrategyDTO> getStrategyById(int sysId) {
        return sendGetRequest(StrategyDTO.class, STR."\{sysUri}/strategy",
                new PathParam("sysId", Integer.toString(sysId)));
    }


    public List<OrderDTO> getOrdersByInit(String tradingDay, int strategyId) {
        return sendGetRequest(OrderDTO.class, STR."\{orderUri}/init",
                new PathParam("tradingDay", tradingDay),
                new PathParam("strategyId", strategyId));
    }

    public boolean putOrders(OrderDTO order) {
        return sendPutRequest(order, orderUri);
    }


    public List<PnlDTO> getPnlDaily(String tradingDay, Integer strategyId) {
        return sendGetRequest(PnlDTO.class, pnlUri,
                new PathParam("tradingDay", tradingDay),
                new PathParam("strategyId", strategyId.toString()));
    }

    public boolean putPnlDaily(PnlDTO pnl) {
        return sendPutRequest(pnl, pnlUri);
    }


    public List<StrategyDTO> getStrategyById(Integer strategyId) {
        return sendGetRequest(StrategyDTO.class, strategyUri,
                new PathParam("strategyId", strategyId.toString()));
    }

    public List<ParamDTO> getParamByStrategyId(Integer strategyId) {
        return sendGetRequest(ParamDTO.class, STR."\{strategyUri}/param",
                new PathParam("strategyId", strategyId.toString()));
    }

    public boolean putParamByStrategyId(Integer strategyId, ParamDTO param) {
        return sendPutRequest(param, STR."\{strategyUri}/param",
                new PathParam("strategyId", strategyId.toString()));
    }


}
