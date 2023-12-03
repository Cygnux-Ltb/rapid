package io.cygnuxltb.console.service;

import io.cygnuxltb.console.persistence.dao.ParamDao;
import io.cygnuxltb.console.persistence.entity.TblSysParam;
import io.cygnuxltb.console.service.util.DtoUtil;
import io.cygnuxltb.console.service.util.ValidationRule;
import io.cygnuxltb.protocol.http.response.ParamDTO;
import io.mercury.common.character.Charsets;
import io.mercury.common.lang.Throws;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.serialization.json.JsonParser;
import jakarta.annotation.Resource;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static io.cygnuxltb.console.controller.util.ControllerUtil.illegalBrokerId;
import static io.cygnuxltb.console.controller.util.ControllerUtil.illegalStrategyName;
import static io.cygnuxltb.console.persistence.JpaExecutor.insertOrUpdate;
import static io.cygnuxltb.console.persistence.JpaExecutor.select;

@Service
public final class ParamService {

    private static final Logger log = Log4j2LoggerFactory.getLogger(ParamService.class);

    private static final Map<String, ValidationRule> validationRuleMap = new HashMap<>();

    @Resource
    private ParamDao dao;

    static {
        try (InputStream inputStream = ParamService.class.getResourceAsStream("validation_rules.json")) {
            if (inputStream != null) {
                String json = IOUtils.toString(inputStream, Charsets.UTF8);
                List<ValidationRule> rules = JsonParser.toList(json, ValidationRule.class);
                for (ValidationRule rule : rules)
                    validationRuleMap.put(rule.getParamName(), rule);
            }
        } catch (IOException e) {
            log.error("IOException -> {}", e.getMessage(), e);
        }

    }

    /**
     * @param param ParamEntity
     * @return int
     */
    public int updateParamSafe(TblSysParam param) {
        if (validationStrategyParam(param)) {
            try {
                TblSysParam saved = dao.save(param);
                return 1;
            } catch (Exception e) {
                return -1;
            }
        }
        return -1;
    }

    private boolean validationStrategyParam(TblSysParam param) {
        String paramName = param.getParamName();
        ValidationRule rule = validationRuleMap.get(paramName);
        if (!validationParamName(paramName, rule)) {
            return false;
        }
        String paramValue = param.getParamValue();
        if (!validationRegex(paramValue, rule.getRegex())) {
            return false;
        }
        return validationValue(paramValue, rule);
    }

    /**
     * 如果变量[validationRule]有值,
     * 证明上层caller使用[strategyParamName]从map中成功get到了相应的ValidationRule对象,
     * 所以此InputParam为[ValidationRuleMap]的子集, 返回ture.
     *
     * @param strategyParamName String
     * @param validationRule    ValidationRule
     * @return boolean
     */
    private boolean validationParamName(String strategyParamName, ValidationRule validationRule) {
        return strategyParamName != null && validationRule != null;
    }

    /**
     * 使用正则表达式验证整个参数的StringValue
     *
     * @param paramValue String
     * @param regex      String
     * @return boolean
     */
    private boolean validationRegex(String paramValue, String regex) {
        return Pattern.matches(regex, paramValue);
    }

    /**
     * Validation Value Type and Range
     *
     * @param paramValue String
     * @param rule       ValidationRule
     * @return boolean
     */
    private boolean validationValue(String paramValue, ValidationRule rule) {
        String[] valueArray = paramValue.split(";");
        String valueType = rule.getValueType();
        return switch (valueType) {
            case "Int" -> validationIntValue(valueArray, rule);
            case "Double" -> validationDoubleValue(valueArray, rule);
            default -> false;
        };
    }

    private boolean validationIntValue(String[] valueArray, ValidationRule rule) {
        int maxValue = Integer.parseInt(rule.getMaxValue());
        int minValue = Integer.parseInt(rule.getMinValue());
        for (String paramValue : valueArray) {
            String valueStr = paramValue.split(",")[1];
            int value = Integer.parseInt(valueStr);
            // 大于Rule定于的最大值或小于最小值则返回false
            if (value > maxValue || value < minValue) {
                return false;
            }
        }
        return true;
    }

    private boolean validationDoubleValue(String[] valueArray, ValidationRule rule) {
        double maxValue = Double.parseDouble(rule.getMaxValue());
        double minValue = Double.parseDouble(rule.getMinValue());
        for (String value : valueArray) {
            String valueStr = value.split(",")[1];
            double doubleValue = Double.parseDouble(valueStr);
            // 大于Rule定义的最大值或小于最小值则返回false
            if (doubleValue > maxValue || doubleValue < minValue) {
                return false;
            }
        }
        return true;
    }


    /**
     * @param strategyName String
     * @return List<ParamEntity>
     */
    public List<ParamDTO> getStrategyParams(String strategyName) {
        if (illegalStrategyName(strategyName, log))
            Throws.illegalArgument("getStrategyParams param error -> strategyName");
        return select(TblSysParam.class,
                () -> dao.queryStrategyParam(strategyName))
                .stream().map(DtoUtil::toDto)
                .collect(Collectors.toList());
    }


    public List<ParamDTO> getCtpParams(String brokerId) {
        if (illegalBrokerId(brokerId, log))
            Throws.illegalArgument("getCtpParams param error -> brokerId");
        return select(TblSysParam.class,
                () -> dao.queryTraderParam(brokerId))
                .stream().map(DtoUtil::toDto)
                .collect(Collectors.toList());
    }


    /**
     * @param entity ParamEntity
     * @return boolean
     */
    public boolean putStrategyParam(TblSysParam entity) {
        return insertOrUpdate(dao, entity);
    }

    public static void main(String[] args) {

        String value = "ni,1197.0;pp,77.0;RM,23.8;SR,50.4;ag,30.1;au,1.925;hc,84.7;zn,322.0;";
        String regex = "^([a-zA-Z]{1,2},\\d+(\\.\\d+)?;)*([a-zA-Z]{1,2},\\d+(\\.\\d+)?;?)$";

        System.out.println(Pattern.matches(regex, value));
    }

}
