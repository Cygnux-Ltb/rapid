package io.rapid.core;

import java.util.HashMap;
import java.util.Map;

public class ErrorSchema {
    private static String defaultLanguage = "CN";
    private static final Map<String, ErrorMsg> map = new HashMap<>();
    private static boolean isBuilt = false;

    public static void build() throws Exception {
        // system error start from 1
        createMsg(1, "CN", "系统错误，请检查日志");
        createMsg(1, "EN", "System error, please check server log");

        createMsg(2, "CN", "用户名或密码错误");
        createMsg(2, "EN", "User name or Password is invalid");

        createMsg(3, "CN", "对不起，该用户未被激活");
        createMsg(3, "EN", "Sorry, User is not active");

        // trading error start from 200
        createMsg(201, "CN", "该参数不能为空");
        createMsg(201, "EN", "Below required field is empty");

        createMsg(202, "CN", "请求参数错误");
        createMsg(202, "EN", "Something wrong with parameter");

        createMsg(203, "CN", "策略未定义");
        createMsg(203, "EN", "Strategy is not defined in strategyFields map");

        createMsg(204, "CN", "参数比较时异常");
        createMsg(204, "EN", "Field comparison exception");

        createMsg(205, "CN", "订单已存在");
        createMsg(205, "EN", "Order id already exists");

        createMsg(206, "CN", "可用资金超限");
        createMsg(206, "EN",
                "This order is over the limit of max buying power");

        createMsg(207, "CN", "Iceberg策略的显示数量不能为空或0");
        createMsg(207, "EN",
                "Display quantity can not be empty or 0 for Iceberg strategy");

        createMsg(208, "CN", "超出每天的交易限量");
        createMsg(208, "EN", "Daily allowed number of orders exceeded limit");

        createMsg(209, "CN", "Sniper策略的价格不能为空或0");
        createMsg(209, "EN", "Price can not be empty or 0 for Sniper strategy");

        createMsg(210, "CN", "止损价格不能为空或0");
        createMsg(210, "EN", "Stop Loss price can't be empty or 0");

        createMsg(211, "CN", "账号不存在");
        createMsg(211, "EN", "Account doesn't exist");

        createMsg(212, "CN", "账号和用户不匹配");
        createMsg(212, "EN", "Account and User not match");

        createMsg(213, "CN", "结束时间已过期");
        createMsg(213, "EN", "End time is expired");

        createMsg(214, "CN", "结束时间早于开始时间");
        createMsg(214, "EN", "End time is before start time");

        createMsg(215, "CN", "不能找到证券号");
        createMsg(215, "EN", "Can't find symbol in ref-data");

        createMsg(216, "CN", "参数必须大于0");
        createMsg(216, "EN", "Field must be greater than 0");

        createMsg(217, "CN", "参数必须是数字");
        createMsg(217, "EN", "Field must be an integer");

        createMsg(218, "CN", "不能通过数量份额验证确定标的");
        createMsg(218, "EN",
                "Can not determine symbol for quantity lot size validation");

        createMsg(219, "CN", "参数没有取约数");
        createMsg(219, "EN", "Field is not in round lot");

        createMsg(220, "CN", "参数超出范围[0, 100]");
        createMsg(220, "EN", "Field is out of range of [0, 100]");

        createMsg(221, "CN", "止损价不能大于限价");
        createMsg(221, "EN",
                "Stop loss price can not be more aggressive than limit price");

        createMsg(222, "CN", "止损价不能大于限价");
        createMsg(222, "EN", "Field not defined in registry");

        createMsg(223, "CN", "用户不存在");
        createMsg(223, "EN", "User doesn't exist");

        createMsg(224, "CN", "订单依然处于等待组装/修改/取消");
        createMsg(224, "EN",
                "Order is pending on instruction/amendment/cancellation");

        createMsg(225, "CN", "可能导致过量成交");
        createMsg(225, "EN", "This would cause overfilled");

        createMsg(226, "CN", "找不到可修改的订单");
        createMsg(226, "EN", "Can't find order to amend");

        createMsg(227, "CN", "订单被中断");
        createMsg(227, "EN", "Order already terminated");

        createMsg(228, "CN", "找不到订单");
        createMsg(228, "EN", "Cant find this order id");

        createMsg(229, "CN", "账号等待平仓该证券");
        createMsg(229, "EN",
                "Account has pending close position action on symbol");

        createMsg(230, "CN", "没有字段定义");
        createMsg(230, "EN", "Can't find field definition for");

        createMsg(231, "CN", "仅接受时间类型HH:mm:ss或yyyy/MM/dd HH:mm:ss; 不接受该类型");
        createMsg(231, "EN",
                "Accepts only HH:mm:ss or yyyy/MM/dd HH:mm:ss; Unknown date format");

        createMsg(232, "CN", "不能转换");
        createMsg(232, "EN", "Can't convert this Field to Class");

        createMsg(233, "CN", "数据转换错误，只接受格式 为");
        createMsg(233, "EN", "Date covert error: should be in format of");

        createMsg(234, "CN", "不支持订单类型");
        createMsg(234, "EN", "Fix OrdType not supported");

        createMsg(235, "CN", "不能转换订单类型");
        createMsg(235, "EN", "Can't convert to FIX OrdType");

        createMsg(236, "CN", "Fix不能处理标签");
        createMsg(236, "EN", "Fix side not handled");

        createMsg(237, "CN", "UTC时间转换错误");
        createMsg(237, "EN", "UTC date convert error");

        createMsg(238, "CN", "找不到可取消的订单");
        createMsg(238, "EN", "Can't find order to cancel");

        createMsg(239, "CN", "订单已完成");
        createMsg(239, "EN", "Order already completed");

        createMsg(240, "CN", "订单还在处理中");
        createMsg(240, "EN", "Order already completed");

        createMsg(241, "CN", "策略没有注册");
        createMsg(241, "EN", "Strategy hasn't been registered");

        createMsg(242, "CN", "会导致成交超过订单数量");
        createMsg(242, "EN", "This would have overfilled parent order");

        createMsg(243, "CN", "超过订单的限价");
        createMsg(243, "EN", "Price is not permitted by parentOrder");

        createMsg(244, "CN", "该对象未提供策略名");
        createMsg(244, "EN", "Bean doesn't provide a strategy name");

        createMsg(245, "CN", "下游券商接口错误");
        createMsg(245, "EN", "Something wrong with downstream adaptor");

        createMsg(246, "CN", "标的对应的标记表不存在");
        createMsg(246, "EN", "Can't find tick table for");

        // application error start from 5000
        // Group: 5000 -> 5099
        createMsg(5000, "CN", "组已存在");
        createMsg(5000, "EN", "Group already exists");

        createMsg(5001, "CN", "请先删除该组的关联信息");
        createMsg(5001, "EN",
                "Please delete the related info for this group first");

        // ExchangeAccount: 5100 -> 5199
        createMsg(5100, "CN", "创建证券交易账号失败");
        createMsg(5100, "EN", "Failed to create new ExchangeAccount");

        createMsg(5101, "CN", "请先删除关联的交易子账号");
        createMsg(5101, "EN",
                "Please delete the related SubAccount(s) first");

        // User & Account: 5200 -> 5299
        createMsg(5200, "CN", "该用户已存在");
        createMsg(5200, "EN", "User already exists");

        createMsg(5201, "CN", "该用户对应的账号已存在");
        createMsg(5201, "EN", "Account of user already exists");

        createMsg(5202, "CN", "该用户不存在");
        createMsg(5202, "EN", "User doesn't exist");

        createMsg(5203, "CN", "该用户对应的账号不存在");
        createMsg(5203, "EN", "Account of user doesn't exist");

        createMsg(5204, "CN", "请先删除该用户对应的交易子账号");
        createMsg(5204, "EN",
                "Please delete the SubAccount(s) of this user");

        // Pool & SubPool: 5300 -> 5399
        createMsg(5300, "CN", "标的池中标的数量不能大于总池中的数量");
        createMsg(5300, "EN",
                "Quantity of instrument in SubPool can't be bigger than that in total Pool");

        // SubAccount: 5400 -> 5499
        createMsg(5400, "CN", "子账号已经存在");
        createMsg(5400, "EN", "SubAccount already exists");

        createMsg(5401, "CN", "无效的子账号");
        createMsg(5401, "EN", "Invalid SubAccount");

        createMsg(5402, "CN", "超出限定标的数量");
        createMsg(5402, "EN", "Quantity is over the limit");

        createMsg(5403, "CN", "请先删除关联的标的池");
        createMsg(5403, "EN", "Please delete its' SubPool(s) first");

        // Instrument: 5500 -> 5599
        createMsg(5500, "CN", "该标的已经存在");
        createMsg(5500, "EN", "The instrument already exists");

        // Permission: 5600 -> 5699
        createMsg(5600, "CN", "没有权限查询所有用户信息");
        createMsg(5600, "EN", "No permission to query all user information");

        isBuilt = true;
    }

    private static String getKey(int code, String language) {
        return language + code;
    }

    private static void createMsg(int code, String language, String msg)
            throws Exception {
        ErrorMsg errorMsg = new ErrorMsg(code, language, msg);
        ErrorMsg existing = map.put(getKey(code, language), errorMsg);
        if (null != existing)
            throw new Exception("Duplicate error message: " + errorMsg);
    }

    public static String getMsg(int code, String language) {
        if (!isBuilt) {
            try {
                build();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ErrorMsg errorMsg = map.get(getKey(code, language));
        if (null != errorMsg)
            return errorMsg.message();

        return null;
    }

    public static String getMsg(int code) {
        if (!isBuilt) {
            try {
                build();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return getMsg(code, defaultLanguage);
    }

    public static String getDefaultLanguage() {
        return defaultLanguage;
    }

    public static void setDefaultLanguage(String defaultLanguage) {
        ErrorSchema.defaultLanguage = defaultLanguage;
    }

}