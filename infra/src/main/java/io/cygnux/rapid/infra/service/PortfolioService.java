package io.cygnux.rapid.infra.service;

import io.cygnux.rapid.infra.component.ApplicationConfiguration;
import io.cygnux.rapid.infra.dto.resp.PortfolioInstrumentsRsp;
import io.cygnux.rapid.infra.dto.resp.PortfoliosRsp;
import io.cygnux.rapid.infra.persistence.dao.PortfolioDao;
import io.cygnux.rapid.infra.persistence.dao.PortfolioInstrumentDao;
import io.cygnux.rapid.infra.persistence.dao.UserDao;
import io.cygnux.rapid.infra.persistence.entity.PortfolioEntity;
import io.cygnux.rapid.infra.persistence.entity.PortfolioInstrumentEntity;
import io.cygnux.rapid.infra.persistence.entity.UserEntity;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static io.cygnux.rapid.infra.persistence.JpaExecutor.insert;
import static io.cygnux.rapid.infra.persistence.JpaExecutor.insertIfNotExist;
import static io.cygnux.rapid.infra.service.PortfolioService.PortfolioConst.PORTFOLIO_FIRST_CODE;
import static io.cygnux.rapid.infra.service.PortfolioService.PortfolioConst.PORTFOLIO_FIRST_NAME;
import static io.cygnux.rapid.infra.service.PortfolioService.PortfolioConst.PORTFOLIO_SECOND_CODE;
import static io.cygnux.rapid.infra.service.PortfolioService.PortfolioConst.PORTFOLIO_SECOND_NAME;
import static io.mercury.common.util.StringSupport.nonEmpty;

@Service
public class PortfolioService {

    private static final Logger log = Log4j2LoggerFactory.getLogger(StrategyService.class);

    public interface PortfolioConst {
        String PORTFOLIO_FIRST_CODE = "FIRST";
        String PORTFOLIO_FIRST_NAME = "优先";
        String PORTFOLIO_SECOND_CODE = "SECOND";
        String PORTFOLIO_SECOND_NAME = "次优";
    }

    @Resource
    private UserDao userDao;

    @Resource
    private PortfolioDao portfolioDao;

    @Resource
    private PortfolioInstrumentDao portfolioInstrumentDao;

    @Resource
    private ApplicationConfiguration configuration;

    @PostConstruct
    public void init() {
        for (UserEntity user : userDao.findAll()) {
            if (0 == portfolioDao.countByUseridAndPortfolioCode(user.getUserid(), PORTFOLIO_FIRST_CODE)) {
                log.info("Creating portfolio [FIRST] with userid -> {}", user.getUserid());
                insert(portfolioDao, new PortfolioEntity().setUserid(user.getUserid())
                        .setPortfolioCode(PORTFOLIO_FIRST_CODE)
                        .setPortfolioName(PORTFOLIO_FIRST_NAME));
            }
            if (0 == portfolioDao.countByUseridAndPortfolioCode(user.getUserid(), PORTFOLIO_SECOND_CODE)) {
                log.info("Creating portfolio [SECOND] with userid -> {}", user.getUserid());
                insert(portfolioDao, new PortfolioEntity().setUserid(user.getUserid())
                        .setPortfolioCode(PORTFOLIO_SECOND_CODE)
                        .setPortfolioName(PORTFOLIO_SECOND_NAME));
            }
        }
    }

    /**
     * 新增或修改
     *
     * @param userId         int
     * @param portfolioCode  String
     * @param portfolioName  String
     * @param instrumentCode String
     * @return boolean
     */
    @Transactional
    public boolean putPortfolio(int userId, @Nonnull String portfolioCode,
                                @Nullable String portfolioName,
                                @Nullable String instrumentCode) {
        if (nonEmpty(portfolioName) && nonEmpty(instrumentCode))
            return insertOrUpdatePortfolio(userId, portfolioCode, portfolioName) &&
                   insertOrUpdatePortfolioInstrument(userId, portfolioCode, instrumentCode);
        if (nonEmpty(portfolioName))
            return insertOrUpdatePortfolio(userId, portfolioCode, portfolioName);
        if (nonEmpty(instrumentCode))
            return insertOrUpdatePortfolioInstrument(userId, portfolioCode, instrumentCode);
        return false;
    }

    private boolean insertOrUpdatePortfolio(int userId,
                                            @Nonnull String portfolioCode,
                                            @Nullable String portfolioName) {
        return insertIfNotExist(portfolioDao,
                () -> portfolioDao
                              .queryPortfolio(userId, portfolioCode) == null,
                new PortfolioEntity()
                        .setUserid(userId)
                        .setPortfolioCode(portfolioCode)
                        .setPortfolioName(portfolioName));
    }

    private boolean insertOrUpdatePortfolioInstrument(int userId,
                                                      @Nonnull String portfolioCode,
                                                      @Nullable String instrumentCode) {
        return insertIfNotExist(portfolioInstrumentDao,
                () -> portfolioInstrumentDao
                              .queryInstrument(userId, portfolioCode, instrumentCode) == null,
                new PortfolioInstrumentEntity()
                        .setUserid(userId)
                        .setPortfolioCode(portfolioCode)
                        .setInstrumentCode(instrumentCode));
    }


    /**
     * 获取用户全部投资标的池
     *
     * @param userId int
     * @return List<String>
     */
    public PortfoliosRsp getPortfolios(int userId) {
        return new PortfoliosRsp().setUserid(userId)
                .setPortfolioList(portfolioDao.queryPortfolioList(userId)
                        .stream()
                        .map(entity -> new PortfoliosRsp.PortfolioObj()
                                .setPortfolioCode(entity.getPortfolioCode())
                                .setPortfolioName(entity.getPortfolioName()))
                        .toList());
    }

    /**
     * 获取用户指定投资标的池
     *
     * @param userId        int
     * @param portfolioCode String
     * @return PortfolioDTO
     */
    public PortfolioInstrumentsRsp getPortfolioInstruments(int userId, String portfolioCode) {
        PortfolioEntity portfolio = portfolioDao.queryPortfolio(userId, portfolioCode);
        List<PortfolioInstrumentEntity> entities = portfolioInstrumentDao.queryInstrumentList(userId, portfolioCode);
        List<PortfolioInstrumentsRsp.PortfolioInstrumentObj> instrumentCodes = new ArrayList<>();
        PortfolioInstrumentsRsp rsp = new PortfolioInstrumentsRsp()
                .setUserid(userId)
                .setPortfolioCode(portfolioCode)
                .setPortfolioName(portfolio.getPortfolioName());
        for (PortfolioInstrumentEntity entity : entities)
            instrumentCodes.add(fromEntity(entity));
        return rsp.setInstruments(instrumentCodes);
    }

    private PortfolioInstrumentsRsp.PortfolioInstrumentObj fromEntity(PortfolioInstrumentEntity entity) {
        return new PortfolioInstrumentsRsp.PortfolioInstrumentObj().setInstrumentCode(entity.getInstrumentCode())
                // TODO 名称映射
                .setInstrumentName(entity.getInstrumentCode())
                // TODO 填充相关字段
                .setLastPrice(1100.0D)
                .setChange(2.3D)
                .setNetPos(7531.4D)
                .setNetPnl(1000);
    }


    /**
     * 删除指定投资标的池
     *
     * @param userId        int
     * @param portfolioCode String
     * @return boolean
     */
    @Transactional
    public boolean deletePortfolioOrInstruments(int userId, @Nonnull String portfolioCode, @Nullable String instruments) {
        if (nonEmpty(instruments))
            return portfolioInstrumentDao.deleteInstruments(userId, portfolioCode,
                    Stream.of(instruments.split(",")).toList()) > 0;
        else if (portfolioDao.deletePortfolio(userId, portfolioCode) > 0)
            return portfolioInstrumentDao.deleteAllInstruments(userId, portfolioCode) > 0;
        return false;
    }

}
