let api = [];
api.push({
    alias: 'api',
    order: '1',
    desc: '[REST] - 历史行情服务',
    link: '[rest]_-_历史行情服务',
    list: []
})
api[0].list.push({
    order: '1',
    desc: '获取指定交易账户参数',
});
api.push({
    alias: 'AdaptorController',
    order: '2',
    desc: '[UI] - 适配器服务',
    link: '[ui]_-_适配器服务',
    list: []
})
api[1].list.push({
    order: '1',
    desc: '[Adaptor]列表',
});
api[1].list.push({
    order: '2',
    desc: '添加[Adaptor]中转链接',
});
api[1].list.push({
    order: '3',
    desc: '编辑[Adaptor]中转链接',
});
api[1].list.push({
    order: '4',
    desc: '修改[Adaptor]',
});
api[1].list.push({
    order: '5',
    desc: '启用[Adaptor]',
});
api[1].list.push({
    order: '6',
    desc: '禁用[Adaptor]',
});
api[1].list.push({
    order: '7',
    desc: '[Adaptor参数]列表',
});
api[1].list.push({
    order: '8',
    desc: '添加[Adaptor参数]中转链接',
});
api[1].list.push({
    order: '9',
    desc: '编辑[Adaptor参数]中转链接',
});
api[1].list.push({
    order: '10',
    desc: '修改[Adaptor参数]',
});
api.push({
    alias: 'CommandController',
    order: '3',
    desc: '[UI] - 系统指令服务[X]',
    link: '[ui]_-_系统指令服务[x]',
    list: []
})
api[2].list.push({
    order: '1',
    desc: '更新参数 [内部接口]',
});
api[2].list.push({
    order: '2',
    desc: '安全更新参数',
});
api.push({
    alias: 'ConsoleController',
    order: '4',
    desc: '[UI] - 控制台',
    link: '[ui]_-_控制台',
    list: []
})
api[3].list.push({
    order: '1',
    desc: '控制台主页',
});
api.push({
    alias: 'StrategyController',
    order: '5',
    desc: '[UI] - 策略服务',
    link: '[ui]_-_策略服务',
    list: []
})
api[4].list.push({
    order: '1',
    desc: '',
});
api[4].list.push({
    order: '2',
    desc: '',
});
api[4].list.push({
    order: '3',
    desc: '',
});
api[4].list.push({
    order: '4',
    desc: '',
});
api[4].list.push({
    order: '5',
    desc: '',
});
api[4].list.push({
    order: '6',
    desc: '',
});
api[4].list.push({
    order: '7',
    desc: '',
});
api[4].list.push({
    order: '8',
    desc: '',
});
api[4].list.push({
    order: '9',
    desc: '',
});
api[4].list.push({
    order: '10',
    desc: '',
});
api.push({
    alias: 'TradingController',
    order: '6',
    desc: '[UI] - 交易服务',
    link: '[ui]_-_交易服务',
    list: []
})
api[5].list.push({
    order: '1',
    desc: '',
});
api[5].list.push({
    order: '2',
    desc: '',
});
api[5].list.push({
    order: '3',
    desc: '',
});
api[5].list.push({
    order: '4',
    desc: '',
});
api[5].list.push({
    order: '5',
    desc: '',
});
api[5].list.push({
    order: '6',
    desc: '',
});
api[5].list.push({
    order: '7',
    desc: '',
});
api[5].list.push({
    order: '8',
    desc: '',
});
api[5].list.push({
    order: '9',
    desc: '',
});
api[5].list.push({
    order: '10',
    desc: '',
});
api.push({
    alias: 'BarController',
    order: '7',
    desc: '[REST] - 历史行情服务',
    link: '[rest]_-_历史行情服务',
    list: []
})
api[6].list.push({
    order: '1',
    desc: '获取1分钟BAR',
});
api[6].list.push({
    order: '2',
    desc: '更新BAR [內部接口]',
});
api.push({
    alias: 'DictionaryController',
    order: '8',
    desc: '[DOC] - 辞典',
    link: '[doc]_-_辞典',
    list: []
})
api[7].list.push({
    order: '1',
    desc: 'Page1:  /-  /-1.交易执行  /--(1).策略下拉列表 -&gt; 策略服务:获取全部策略  /--(2).证券代码下拉列表 -&gt; 交易标的查询接口:获取可交易标的  /--(3).委托按钮 -&gt; 订单服务接口:创建订单[开仓/平仓/一键平仓接口]  ----------------------------------------------------------------------------  /-2.持仓  /--(1).列表 -&gt; 仓位查询接口:查询当前持仓  ----------------------------------------------------------------------------  /-3.当日委托  /--(1).列表 -&gt; 订单服务接口:查询Order  ----------------------------------------------------------------------------  /-4.当日成交  /--(1).列表 -&gt; 订单服务接口:查询Order  ============================================================================  ============================================================================  Page2:  /-  /-1.目标池  /--(1).策略名称 -&gt; 投资组合接口:获取用户投资组合  /--(2).添加股票 -&gt; 交易标的接口:  /--(3).股票名称代码 -&gt; 交易标的查询接口  /--(4).现价 -&gt; 交易标的查询接口:获取最新价格  /--(5).持仓 -&gt; 仓位查询接口:查询当前持仓  /--(6).涨幅 -&gt; 交易标的查询接口:获取结算信息  /--(7).操作 -&gt; 订单服务接口:创建订单[开仓/平仓/一键平仓接口]  ----------------------------------------------------------------------------  /-2.添加策略  /--(1).添加策略 -&gt; 策略服务:  ----------------------------------------------------------------------------  /-3.当前订单:一键清仓 -&gt; 订单服务接口:创建订单[开仓/平仓/一键平仓接口]  ----------------------------------------------------------------------------  /-4.当前订单  /--(1).名称 -&gt; 订单服务接口:获取订单最新状态  /--(2).持仓 -&gt; 仓位查询接口:查询当前持仓  /--(3).盈亏 -&gt; PNL服务接口:查询结算PNL  /--(4).操作作 -&gt; 订单服务接口:创建订单[开仓/平仓/一键平仓接口]  ============================================================================  ============================================================================  Page3:  /-  /-1.技术指标  /--(1).  /-2.左上页面  /--(1).策略服务:根据策略名称获取策略相关参数  /-2.左下页面  /--(1).投资组合接口:获取用户投资组合[股票池/目标池都通过此接口]',
});
api.push({
    alias: 'InstrumentController',
    order: '9',
    desc: '[REST] - 交易标的服务',
    link: '[rest]_-_交易标的服务',
    list: []
})
api[8].list.push({
    order: '1',
    desc: '获取交易标的',
});
api[8].list.push({
    order: '2',
    desc: '获取交易标的',
});
api[8].list.push({
    order: '3',
    desc: '获取结算信息',
});
api[8].list.push({
    order: '4',
    desc: '获取最新价格',
});
api[8].list.push({
    order: '5',
    desc: '更新最新价格 (內部接口)',
});
api[8].list.push({
    order: '6',
    desc: '获取交易费用',
});
api[8].list.push({
    order: '7',
    desc: '获取可交易标的',
});
api.push({
    alias: 'OrderController',
    order: '10',
    desc: '[REST] - 订单服务',
    link: '[rest]_-_订单服务',
    list: []
})
api[9].list.push({
    order: '1',
    desc: '查询订单',
});
api[9].list.push({
    order: '2',
    desc: '获取订单最新状态列表',
});
api[9].list.push({
    order: '3',
    desc: '委托 [前端调用: 开仓, 平仓, 一键平仓, 手动单笔]',
});
api[9].list.push({
    order: '4',
    desc: '批量委托',
});
api[9].list.push({
    order: '5',
    desc: '[X]新增订单 [非前端界面调用]',
});
api.push({
    alias: 'PnlController',
    order: '11',
    desc: '[REST] - PNL(盈亏)服务',
    link: '[rest]_-_pnl(盈亏)服务',
    list: []
})
api[10].list.push({
    order: '1',
    desc: '查询PNL (查询盈亏)',
});
api[10].list.push({
    order: '2',
    desc: '更新PNL (内部接口, 策略引擎调用)',
});
api[10].list.push({
    order: '3',
    desc: '查询结算PNL (查询结算盈亏)',
});
api.push({
    alias: 'PortfolioController',
    order: '12',
    desc: '[REST] - 投资组合(股票池/目标池)服务',
    link: '[rest]_-_投资组合(股票池/目标池)服务',
    list: []
})
api[11].list.push({
    order: '1',
    desc: '添加投资标的(目标池页面中'添加分组'和'添加股票'调用) (1).创建(或修改)目标池时需要提供 'userid', 'portfolioCode', 'portfolioName' (2).添加投资标的时需要提供 'userid', 'portfolioCode', 'instrumentCode' (3).当提供全部4个参数时, 将创建(或修改)目标池, 并将投资标的加入目标池中',
});
api[11].list.push({
    order: '2',
    desc: '删除指定用户投资组合 (1).删除投资组合(股票池), 需要提供 'userid', 'portfolioCode' (2).删除投资组合(股票池)中的标的, 需要提供 'userid', 'portfolioCode', 'instruments', 其中 'instruments' 为数组类型',
});
api[11].list.push({
    order: '3',
    desc: '获取用户投资组合(股票池/目标池)列表',
});
api[11].list.push({
    order: '4',
    desc: '获取用户投资组合(股票池/目标池)详细信息',
});
api[11].list.push({
    order: '5',
    desc: '获取用户第一投资组合 (首页左侧优先股票池)',
});
api[11].list.push({
    order: '6',
    desc: '获取用户第二投资组合 (首页右侧次优股票池)',
});
api.push({
    alias: 'PositionController',
    order: '13',
    desc: '[REST] - 仓位服务',
    link: '[rest]_-_仓位服务',
    list: []
})
api[12].list.push({
    order: '1',
    desc: '查询当前持仓 ([page1.jpg]-持仓板块)',
});
api[12].list.push({
    order: '2',
    desc: '持仓平仓操作 &lt;p&gt; 传[用户ID]时; 动作为:全部平仓, [一键平仓]功能使用 传[用户ID],[投资标的代码]时; 动作为:平仓所有指定投资标的的仓位 传[用户ID],[投资标的代码],[方向]时; 动作为:平仓指定投资标的和指定方向的仓位 传[用户ID],[投资标的代码],[方向],[数量]时; 动作为:平仓指定数量的指定投资标的和指定方向的仓位和',
});
api.push({
    alias: 'ProductController',
    order: '14',
    desc: '[REST] - 产品服务',
    link: '[rest]_-_产品服务',
    list: []
})
api[13].list.push({
    order: '1',
    desc: '获取全部产品',
});
api[13].list.push({
    order: '2',
    desc: '获取指定产品信息',
});
api[13].list.push({
    order: '3',
    desc: '产品初始化',
});
api.push({
    alias: 'SimulateController',
    order: '15',
    desc: '[REST] - 模拟测试服务',
    link: '[rest]_-_模拟测试服务',
    list: []
})
api[14].list.push({
    order: '1',
    desc: '提交测试请求',
});
api.push({
    alias: 'StatusController',
    order: '16',
    desc: '[REST] - 交易系统状态服务',
    link: '[rest]_-_交易系统状态服务',
    list: []
})
api[15].list.push({
    order: '1',
    desc: '获取全部策略状态',
});
api[15].list.push({
    order: '2',
    desc: '发送状态指令',
});
api[15].list.push({
    order: '3',
    desc: '更新状态',
});
api.push({
    alias: 'StrategyController',
    order: '17',
    desc: '[REST] - 策略服务',
    link: '[rest]_-_策略服务',
    list: []
})
api[16].list.push({
    order: '1',
    desc: '获取全部策略',
});
api[16].list.push({
    order: '2',
    desc: '获取策略',
});
api[16].list.push({
    order: '3',
    desc: '根据策略名称获取策略相关参数',
});
api[16].list.push({
    order: '4',
    desc: '添加策略参数 (内部接口)',
});
api.push({
    alias: 'UserController',
    order: '18',
    desc: '[REST] - 用户服务',
    link: '[rest]_-_用户服务',
    list: []
})
api[17].list.push({
    order: '1',
    desc: '用户登陆',
});
api[17].list.push({
    order: '2',
    desc: '用户注册, 当前不支持新用户注册',
});
document.onkeydown = keyDownSearch;
function keyDownSearch(e) {
    const theEvent = e;
    const code = theEvent.keyCode || theEvent.which || theEvent.charCode;
    if (code === 13) {
        const search = document.getElementById('search');
        const searchValue = search.value;
        let searchArr = [];
        for (let i = 0; i < api.length; i++) {
            let apiData = api[i];
            const desc = apiData.desc;
            if (desc.toLocaleLowerCase().indexOf(searchValue) > -1) {
                searchArr.push({
                    order: apiData.order,
                    desc: apiData.desc,
                    link: apiData.link,
                    alias: apiData.alias,
                    list: apiData.list
                });
            } else {
                let methodList = apiData.list || [];
                let methodListTemp = [];
                for (let j = 0; j < methodList.length; j++) {
                    const methodData = methodList[j];
                    const methodDesc = methodData.desc;
                    if (methodDesc.toLocaleLowerCase().indexOf(searchValue) > -1) {
                        methodListTemp.push(methodData);
                        break;
                    }
                }
                if (methodListTemp.length > 0) {
                    const data = {
                        order: apiData.order,
                        desc: apiData.desc,
                        alias: apiData.alias,
                        link: apiData.link,
                        list: methodListTemp
                    };
                    searchArr.push(data);
                }
            }
        }
        let html;
        if (searchValue === '') {
            const liClass = "";
            const display = "display: none";
            html = buildAccordion(api,liClass,display);
            document.getElementById('accordion').innerHTML = html;
        } else {
            const liClass = "open";
            const display = "display: block";
            html = buildAccordion(searchArr,liClass,display);
            document.getElementById('accordion').innerHTML = html;
        }
        const Accordion = function (el, multiple) {
            this.el = el || {};
            this.multiple = multiple || false;
            const links = this.el.find('.dd');
            links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown);
        };
        Accordion.prototype.dropdown = function (e) {
            const $el = e.data.el;
            let $this = $(this), $next = $this.next();
            $next.slideToggle();
            $this.parent().toggleClass('open');
            if (!e.data.multiple) {
                $el.find('.submenu').not($next).slideUp("20").parent().removeClass('open');
            }
        };
        new Accordion($('#accordion'), false);
    }
}

function buildAccordion(apiData, liClass, display) {
    let html = "";
    if (apiData.length > 0) {
         for (let j = 0; j < apiData.length; j++) {
            html += '<li class="'+liClass+'">';
            html += '<a class="dd" href="' + apiData[j].alias + '.html#header">' + apiData[j].order + '.&nbsp;' + apiData[j].desc + '</a>';
            html += '<ul class="sectlevel2" style="'+display+'">';
            let doc = apiData[j].list;
            for (let m = 0; m < doc.length; m++) {
                html += '<li><a href="' + apiData[j].alias + '.html#_' + apiData[j].order + '_' + doc[m].order + '_' + doc[m].desc + '">' + apiData[j].order + '.' + doc[m].order + '.&nbsp;' + doc[m].desc + '</a> </li>';
            }
            html += '</ul>';
            html += '</li>';
        }
    }
    return html;
}