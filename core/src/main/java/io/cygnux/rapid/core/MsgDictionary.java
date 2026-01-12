package io.cygnux.rapid.core;

import io.mercury.common.collections.MutableMaps;
import io.mercury.common.lang.StringConstant;
import org.eclipse.collections.api.map.MutableMap;

import javax.annotation.Nonnull;
import java.util.concurrent.atomic.AtomicReference;

public final class MsgDictionary {

    private static final AtomicReference<String> DEFAULT_LANGUAGE = new AtomicReference<>("cn");

    private static final MutableMap<String, ErrorMsg> MAP = MutableMaps.newUnifiedMap();

    static {
        // system error start from 1
        newMsg(1, "cn", "系统错误，请检查日志");
        newMsg(1, "en", "System error, please check server log");

        newMsg(2, "cn", "用户名或密码错误");
        newMsg(2, "en", "User name or Password is invalid");

        newMsg(3, "cn", "对不起，该用户未被激活");
        newMsg(3, "en", "Sorry, User is not active");

        // trading error start from 200
        newMsg(201, "cn", "该参数不能为空");
        newMsg(201, "en", "Below required field is empty");

        newMsg(202, "cn", "请求参数错误");
        newMsg(202, "en", "Something wrong with parameter");

        newMsg(203, "cn", "策略未定义");
        newMsg(203, "en", "Strategy is not defined in strategyFields map");

        newMsg(204, "cn", "参数比较时异常");
        newMsg(204, "en", "Field comparison exception");

        newMsg(205, "cn", "订单已存在");
        newMsg(205, "en", "Order id already exists");

        newMsg(206, "cn", "可用资金超限");
        newMsg(206, "en", "This order is over the limit of max buying power");

        newMsg(207, "cn", "Iceberg策略的显示数量不能为空或0");
        newMsg(207, "en", "Display quantity can not be empty or 0 for Iceberg strategy");

        newMsg(208, "cn", "超出每天的交易限量");
        newMsg(208, "en", "Daily allowed number of orders exceeded limit");

        newMsg(209, "cn", "Sniper策略的价格不能为空或0");
        newMsg(209, "en", "Price can not be empty or 0 for Sniper strategy");

        newMsg(210, "cn", "止损价格不能为空或0");
        newMsg(210, "en", "Stop Loss price can't be empty or 0");

        newMsg(211, "cn", "账号不存在");
        newMsg(211, "en", "Account doesn't exist");

        newMsg(212, "cn", "账号和用户不匹配");
        newMsg(212, "en", "Account and User not match");

        newMsg(213, "cn", "结束时间已过期");
        newMsg(213, "en", "End time is expired");

        newMsg(214, "cn", "结束时间早于开始时间");
        newMsg(214, "en", "End time is before start time");

        newMsg(215, "cn", "不能找到证券号");
        newMsg(215, "en", "Can't find symbol in ref-data");

        newMsg(216, "cn", "参数必须大于0");
        newMsg(216, "en", "Field must be greater than 0");

        newMsg(217, "cn", "参数必须是数字");
        newMsg(217, "en", "Field must be an integer");

        newMsg(218, "cn", "不能通过数量份额验证确定标的");
        newMsg(218, "en", "Can not determine symbol for quantity lot size validation");

        newMsg(219, "cn", "参数没有取约数");
        newMsg(219, "en", "Field is not in round lot");

        newMsg(220, "cn", "参数超出范围[0, 100]");
        newMsg(220, "en", "Field is out of range of [0, 100]");

        newMsg(221, "cn", "止损价不能大于限价");
        newMsg(221, "en", "Stop loss price can not be more aggressive than limit price");

        newMsg(222, "cn", "止损价不能大于限价");
        newMsg(222, "en", "Field not defined in registry");

        newMsg(223, "cn", "用户不存在");
        newMsg(223, "en", "User doesn't exist");

        newMsg(224, "cn", "订单依然处于等待组装/修改/取消");
        newMsg(224, "en", "Order is pending on instruction/amendment/cancellation");

        newMsg(225, "cn", "可能导致过量成交");
        newMsg(225, "en", "This would cause overfilled");

        newMsg(226, "cn", "找不到可修改的订单");
        newMsg(226, "en", "Can't find order to amend");

        newMsg(227, "cn", "订单被中断");
        newMsg(227, "en", "Order already terminated");

        newMsg(228, "cn", "找不到订单");
        newMsg(228, "en", "Cant find this order id");

        newMsg(229, "cn", "账号等待平仓该证券");
        newMsg(229, "en", "Account has pending close position action on symbol");

        newMsg(230, "cn", "没有字段定义");
        newMsg(230, "en", "Can't find field definition for");

        newMsg(231, "cn", "仅接受时间类型HH:mm:ss或yyyy/MM/dd HH:mm:ss; 不接受该类型");
        newMsg(231, "en", "Accepts only HH:mm:ss or yyyy/MM/dd HH:mm:ss; Unknown date format");

        newMsg(232, "cn", "不能转换");
        newMsg(232, "en", "Can't convert this Field to Class");

        newMsg(233, "cn", "数据转换错误，只接受格式 为");
        newMsg(233, "en", "Date covert error: should be in format of");

        newMsg(234, "cn", "不支持订单类型");
        newMsg(234, "en", "Fix OrdType not supported");

        newMsg(235, "cn", "不能转换订单类型");
        newMsg(235, "en", "Can't convert to FIX OrdType");

        newMsg(236, "cn", "Fix不能处理标签");
        newMsg(236, "en", "Fix side not handled");

        newMsg(237, "cn", "UTC时间转换错误");
        newMsg(237, "en", "UTC date convert error");

        newMsg(238, "cn", "找不到可取消的订单");
        newMsg(238, "en", "Can't find order to cancel");

        newMsg(239, "cn", "订单已完成");
        newMsg(239, "en", "Order already completed");

        newMsg(240, "cn", "订单还在处理中");
        newMsg(240, "en", "Order already completed");

        newMsg(241, "cn", "策略没有注册");
        newMsg(241, "en", "Strategy hasn't been registered");

        newMsg(242, "cn", "会导致成交超过订单数量");
        newMsg(242, "en", "This would have overfilled parent order");

        newMsg(243, "cn", "超过订单的限价");
        newMsg(243, "en", "Price is not permitted by parentOrder");

        newMsg(244, "cn", "该对象未提供策略名");
        newMsg(244, "en", "Bean doesn't provide a strategy name");

        newMsg(245, "cn", "下游券商接口错误");
        newMsg(245, "en", "Something wrong with downstream adaptor");

        newMsg(246, "cn", "标的对应的标记表不存在");
        newMsg(246, "en", "Can't find tick table for");

        // application error start from 5000
        // Group: 5000 -> 5099
        newMsg(5000, "cn", "组已存在");
        newMsg(5000, "en", "Group already exists");

        newMsg(5001, "cn", "请先删除该组的关联信息");
        newMsg(5001, "en", "Please delete the related info for this group first");

        // ExchangeAccount: 5100 -> 5199
        newMsg(5100, "cn", "创建证券交易账号失败");
        newMsg(5100, "en", "Failed to create new ExchangeAccount");

        newMsg(5101, "cn", "请先删除关联的交易子账号");
        newMsg(5101, "en", "Please delete the related SubAccount(s) first");

        // User & Account: 5200 -> 5299
        newMsg(5200, "cn", "该用户已存在");
        newMsg(5200, "en", "User already exists");

        newMsg(5201, "cn", "该用户对应的账号已存在");
        newMsg(5201, "en", "Account of user already exists");

        newMsg(5202, "cn", "该用户不存在");
        newMsg(5202, "en", "User doesn't exist");

        newMsg(5203, "cn", "该用户对应的账号不存在");
        newMsg(5203, "en", "Account of user doesn't exist");

        newMsg(5204, "cn", "请先删除该用户对应的交易子账号");
        newMsg(5204, "en", "Please delete the SubAccount(s) of this user");

        // Pool & SubPool: 5300 -> 5399
        newMsg(5300, "cn", "标的池中标的数量不能大于总池中的数量");
        newMsg(5300, "en", "Quantity of instrument in SubPool can't be bigger than that in total Pool");

        // SubAccount: 5400 -> 5499
        newMsg(5400, "cn", "子账号已经存在");
        newMsg(5400, "en", "SubAccount already exists");

        newMsg(5401, "cn", "无效的子账号");
        newMsg(5401, "en", "Invalid SubAccount");

        newMsg(5402, "cn", "超出限定标的数量");
        newMsg(5402, "en", "Quantity is over the limit");

        newMsg(5403, "cn", "请先删除关联的标的池");
        newMsg(5403, "en", "Please delete its' SubPool(s) first");

        // Instrument: 5500 -> 5599
        newMsg(5500, "cn", "该标的已经存在");
        newMsg(5500, "en", "The instrument already exists");

        // Permission: 5600 -> 5699
        newMsg(5600, "cn", "没有权限查询所有用户信息");
        newMsg(5600, "en", "No permission to query all user information");

    }

    private static String getKey(String language, int code) {
        return language + code;
    }

    private static void newMsg(int code, String language, String msg) {
        var errorMsg = new ErrorMsg(code, language, msg);
        MAP.put(getKey(language, code), errorMsg);
    }

    @Nonnull
    public static String getMessage(String language, int code) {
        var errorMsg = MAP.get(getKey(language, code));
        if (null != errorMsg)
            return errorMsg.message();
        return StringConstant.NULL_STRING;
    }

    @Nonnull
    public static String getMessage(int code) {
        return getMessage(DEFAULT_LANGUAGE.get(), code);
    }

    public static String getDefaultLanguage() {
        return DEFAULT_LANGUAGE.get();
    }

    public static void setDefaultLanguage(String defaultLanguage) {
        DEFAULT_LANGUAGE.set(defaultLanguage);
    }

    public record ErrorMsg(int code, String language, String message) {

        @Nonnull
        @Override
        public String toString() {
            return language + ":" + code + ":" + message;
        }

    }


}