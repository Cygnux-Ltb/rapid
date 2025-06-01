package io.cygnux.rapid.core;

import io.mercury.common.collections.MutableMaps;
import io.mercury.common.lang.StringConstant;
import lombok.Getter;
import org.eclipse.collections.api.map.MutableMap;

import javax.annotation.Nonnull;
import java.util.concurrent.atomic.AtomicReference;

public final class MsgDictionary {

    private static final AtomicReference<String> DEFAULT_LANGUAGE = new AtomicReference<>("cn");

    private static final MutableMap<String, ErrorMsg> MAP = MutableMaps.newUnifiedMap();

    static {
        // system error start from 1
        createMsg(1, "cn", "系统错误，请检查日志");
        createMsg(1, "en", "System error, please check server log");

        createMsg(2, "cn", "用户名或密码错误");
        createMsg(2, "en", "User name or Password is invalid");

        createMsg(3, "cn", "对不起，该用户未被激活");
        createMsg(3, "en", "Sorry, User is not active");

        // trading error start from 200
        createMsg(201, "cn", "该参数不能为空");
        createMsg(201, "en", "Below required field is empty");

        createMsg(202, "cn", "请求参数错误");
        createMsg(202, "en", "Something wrong with parameter");

        createMsg(203, "cn", "策略未定义");
        createMsg(203, "en", "Strategy is not defined in strategyFields map");

        createMsg(204, "cn", "参数比较时异常");
        createMsg(204, "en", "Field comparison exception");

        createMsg(205, "cn", "订单已存在");
        createMsg(205, "en", "Order id already exists");

        createMsg(206, "cn", "可用资金超限");
        createMsg(206, "en",
                "This order is over the limit of max buying power");

        createMsg(207, "cn", "Iceberg策略的显示数量不能为空或0");
        createMsg(207, "en",
                "Display quantity can not be empty or 0 for Iceberg strategy");

        createMsg(208, "cn", "超出每天的交易限量");
        createMsg(208, "en", "Daily allowed number of orders exceeded limit");

        createMsg(209, "cn", "Sniper策略的价格不能为空或0");
        createMsg(209, "en", "Price can not be empty or 0 for Sniper strategy");

        createMsg(210, "cn", "止损价格不能为空或0");
        createMsg(210, "en", "Stop Loss price can't be empty or 0");

        createMsg(211, "cn", "账号不存在");
        createMsg(211, "en", "Account doesn't exist");

        createMsg(212, "cn", "账号和用户不匹配");
        createMsg(212, "en", "Account and User not match");

        createMsg(213, "cn", "结束时间已过期");
        createMsg(213, "en", "End time is expired");

        createMsg(214, "cn", "结束时间早于开始时间");
        createMsg(214, "en", "End time is before start time");

        createMsg(215, "cn", "不能找到证券号");
        createMsg(215, "en", "Can't find symbol in ref-data");

        createMsg(216, "cn", "参数必须大于0");
        createMsg(216, "en", "Field must be greater than 0");

        createMsg(217, "cn", "参数必须是数字");
        createMsg(217, "en", "Field must be an integer");

        createMsg(218, "cn", "不能通过数量份额验证确定标的");
        createMsg(218, "en",
                "Can not determine symbol for quantity lot size validation");

        createMsg(219, "cn", "参数没有取约数");
        createMsg(219, "en", "Field is not in round lot");

        createMsg(220, "cn", "参数超出范围[0, 100]");
        createMsg(220, "en", "Field is out of range of [0, 100]");

        createMsg(221, "cn", "止损价不能大于限价");
        createMsg(221, "en",
                "Stop loss price can not be more aggressive than limit price");

        createMsg(222, "cn", "止损价不能大于限价");
        createMsg(222, "en", "Field not defined in registry");

        createMsg(223, "cn", "用户不存在");
        createMsg(223, "en", "User doesn't exist");

        createMsg(224, "cn", "订单依然处于等待组装/修改/取消");
        createMsg(224, "en",
                "Order is pending on instruction/amendment/cancellation");

        createMsg(225, "cn", "可能导致过量成交");
        createMsg(225, "en", "This would cause overfilled");

        createMsg(226, "cn", "找不到可修改的订单");
        createMsg(226, "en", "Can't find order to amend");

        createMsg(227, "cn", "订单被中断");
        createMsg(227, "en", "Order already terminated");

        createMsg(228, "cn", "找不到订单");
        createMsg(228, "en", "Cant find this order id");

        createMsg(229, "cn", "账号等待平仓该证券");
        createMsg(229, "en",
                "Account has pending close position action on symbol");

        createMsg(230, "cn", "没有字段定义");
        createMsg(230, "en", "Can't find field definition for");

        createMsg(231, "cn", "仅接受时间类型HH:mm:ss或yyyy/MM/dd HH:mm:ss; 不接受该类型");
        createMsg(231, "en",
                "Accepts only HH:mm:ss or yyyy/MM/dd HH:mm:ss; Unknown date format");

        createMsg(232, "cn", "不能转换");
        createMsg(232, "en", "Can't convert this Field to Class");

        createMsg(233, "cn", "数据转换错误，只接受格式 为");
        createMsg(233, "en", "Date covert error: should be in format of");

        createMsg(234, "cn", "不支持订单类型");
        createMsg(234, "en", "Fix OrdType not supported");

        createMsg(235, "cn", "不能转换订单类型");
        createMsg(235, "en", "Can't convert to FIX OrdType");

        createMsg(236, "cn", "Fix不能处理标签");
        createMsg(236, "en", "Fix side not handled");

        createMsg(237, "cn", "UTC时间转换错误");
        createMsg(237, "en", "UTC date convert error");

        createMsg(238, "cn", "找不到可取消的订单");
        createMsg(238, "en", "Can't find order to cancel");

        createMsg(239, "cn", "订单已完成");
        createMsg(239, "en", "Order already completed");

        createMsg(240, "cn", "订单还在处理中");
        createMsg(240, "en", "Order already completed");

        createMsg(241, "cn", "策略没有注册");
        createMsg(241, "en", "Strategy hasn't been registered");

        createMsg(242, "cn", "会导致成交超过订单数量");
        createMsg(242, "en", "This would have overfilled parent order");

        createMsg(243, "cn", "超过订单的限价");
        createMsg(243, "en", "Price is not permitted by parentOrder");

        createMsg(244, "cn", "该对象未提供策略名");
        createMsg(244, "en", "Bean doesn't provide a strategy name");

        createMsg(245, "cn", "下游券商接口错误");
        createMsg(245, "en", "Something wrong with downstream adaptor");

        createMsg(246, "cn", "标的对应的标记表不存在");
        createMsg(246, "en", "Can't find tick table for");

        // application error start from 5000
        // Group: 5000 -> 5099
        createMsg(5000, "cn", "组已存在");
        createMsg(5000, "en", "Group already exists");

        createMsg(5001, "cn", "请先删除该组的关联信息");
        createMsg(5001, "en",
                "Please delete the related info for this group first");

        // ExchangeAccount: 5100 -> 5199
        createMsg(5100, "cn", "创建证券交易账号失败");
        createMsg(5100, "en", "Failed to create new ExchangeAccount");

        createMsg(5101, "cn", "请先删除关联的交易子账号");
        createMsg(5101, "en",
                "Please delete the related SubAccount(s) first");

        // User & Account: 5200 -> 5299
        createMsg(5200, "cn", "该用户已存在");
        createMsg(5200, "en", "User already exists");

        createMsg(5201, "cn", "该用户对应的账号已存在");
        createMsg(5201, "en", "Account of user already exists");

        createMsg(5202, "cn", "该用户不存在");
        createMsg(5202, "en", "User doesn't exist");

        createMsg(5203, "cn", "该用户对应的账号不存在");
        createMsg(5203, "en", "Account of user doesn't exist");

        createMsg(5204, "cn", "请先删除该用户对应的交易子账号");
        createMsg(5204, "en",
                "Please delete the SubAccount(s) of this user");

        // Pool & SubPool: 5300 -> 5399
        createMsg(5300, "cn", "标的池中标的数量不能大于总池中的数量");
        createMsg(5300, "en",
                "Quantity of instrument in SubPool can't be bigger than that in total Pool");

        // SubAccount: 5400 -> 5499
        createMsg(5400, "cn", "子账号已经存在");
        createMsg(5400, "en", "SubAccount already exists");

        createMsg(5401, "cn", "无效的子账号");
        createMsg(5401, "en", "Invalid SubAccount");

        createMsg(5402, "cn", "超出限定标的数量");
        createMsg(5402, "en", "Quantity is over the limit");

        createMsg(5403, "cn", "请先删除关联的标的池");
        createMsg(5403, "en", "Please delete its' SubPool(s) first");

        // Instrument: 5500 -> 5599
        createMsg(5500, "cn", "该标的已经存在");
        createMsg(5500, "en", "The instrument already exists");

        // Permission: 5600 -> 5699
        createMsg(5600, "cn", "没有权限查询所有用户信息");
        createMsg(5600, "en", "No permission to query all user information");

    }

    private static String getKey(int code, String language) {
        return language + code;
    }

    private static void createMsg(int code, String language, String msg) {
        var errorMsg = new ErrorMsg(code, language, msg);
        MAP.put(getKey(code, language), errorMsg);
    }

    @Nonnull
    public static String getMessage(int code, String language) {
        ErrorMsg errorMsg = MAP.get(getKey(code, language));
        if (null != errorMsg)
            return errorMsg.getMessage();
        return StringConstant.NULL_STRING;
    }

    @Nonnull
    public static String getMessage(int code) {
        return getMessage(code, DEFAULT_LANGUAGE.get());
    }

    public static String getDefaultLanguage() {
        return DEFAULT_LANGUAGE.get();
    }

    public static void setDefaultLanguage(String defaultLanguage) {
        DEFAULT_LANGUAGE.set(defaultLanguage);
    }

    public static final class ErrorMsg {

        @Getter
        private final int code;

        @Getter
        private final String language;

        @Getter
        private final String message;

        ErrorMsg(int code, String language, String message) {
            this.code = code;
            this.language = language;
            this.message = message;
        }

        @Override
        public String toString() {
            return code + ":" + language + ":" + message;
        }

    }


}