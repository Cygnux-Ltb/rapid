let api = [];
api.push({
    alias: 'api',
    order: '1',
    desc: '用户服务',
    link: '用户服务',
    list: []
})
api[0].list.push({
    order: '1',
    desc: '用户登陆',
});
api[0].list.push({
    order: '2',
    desc: '用户注册, 当前不支持新用户注册',
});
api.push({
    alias: 'StrategyController',
    order: '2',
    desc: '策略服务',
    link: '策略服务',
    list: []
})
api[1].list.push({
    order: '1',
    desc: '获取全部策略',
});
api[1].list.push({
    order: '2',
    desc: '获取策略',
});
api[1].list.push({
    order: '3',
    desc: '根据策略名称获取策略相关参数',
});
api[1].list.push({
    order: '4',
    desc: '添加策略参数 (内部接口)',
});
api.push({
    alias: 'BarController',
    order: '3',
    desc: '历史行情服务',
    link: '历史行情服务',
    list: []
})
api[2].list.push({
    order: '1',
    desc: '获取1分钟BAR',
});
api[2].list.push({
    order: '2',
    desc: '更新BAR [內部接口]',
});
api.push({
    alias: 'PnlController',
    order: '4',
    desc: 'PNL服务(盈亏)',
    link: 'pnl服务(盈亏)',
    list: []
})
api[3].list.push({
    order: '1',
    desc: '查询PNL (查询盈亏)',
});
api[3].list.push({
    order: '2',
    desc: '更新PNL (内部接口, 策略引擎调用)',
});
api[3].list.push({
    order: '3',
    desc: '查询结算PNL (查询结算盈亏)',
});
api.push({
    alias: 'DictionaryController',
    order: '5',
    desc: '辞典',
    link: '辞典',
    list: []
})
api[4].list.push({
    order: '1',
    desc: '&lt;br&gt;Page1: &lt;br&gt;/- &lt;br&gt;/-1.交易执行 &lt;br&gt;/--(1).策略下拉列表 -&gt; 策略服务:获取全部策略 &lt;br&gt;/--(2).证券代码下拉列表 -&gt; 交易标的查询接口:获取可交易标的 &lt;br&gt;/--(3).委托按钮 -&gt; 订单服务接口:创建订单[前端调用:开仓,平仓,一键平仓都通过此接口] &lt;br&gt;--------------------------------------------------------------------------------- &lt;br&gt;/-2.持仓 &lt;br&gt;/--(1).列表 -&gt; 仓位查询接口:查询当前持仓 &lt;br&gt;--------------------------------------------------------------------------------- &lt;br&gt;/-3.当日委托 &lt;br&gt;/--(1).列表 -&gt; 订单服务接口:查询Order &lt;br&gt;--------------------------------------------------------------------------------- &lt;br&gt;/-4.当日成交 &lt;br&gt;/--(1).列表 -&gt; 订单服务接口:查询Order &lt;br&gt;================================================================================= &lt;br&gt;================================================================================= &lt;br&gt;Page2: &lt;br&gt;/- &lt;br&gt;/-1.目标池 &lt;br&gt;/--(1).策略名称 -&gt; 投资组合接口:获取用户投资组合 &lt;br&gt;/--(2).添加股票 -&gt; 交易标的接口: &lt;br&gt;/--(3).股票名称代码 -&gt; 交易标的查询接口 &lt;br&gt;/--(4).现价 -&gt; 交易标的查询接口:获取最新价格 &lt;br&gt;/--(5).持仓 -&gt; 仓位查询接口:查询当前持仓 &lt;br&gt;/--(6).涨幅 -&gt; 交易标的查询接口:获取结算信息 &lt;br&gt;/--(7).操作 -&gt; 订单服务接口:创建订单[前端调用:开仓,平仓,一键平仓都通过此接口] &lt;br&gt;--------------------------------------------------------------------------------- &lt;br&gt;/-2.添加策略 &lt;br&gt;/--(1).添加策略 -&gt; 策略服务: &lt;br&gt;--------------------------------------------------------------------------------- &lt;br&gt;/-3.当前订单:一键清仓 -&gt; 订单服务接口-&gt;创建订单[前端调用:开仓,平仓,一键平仓都通过此接口] &lt;br&gt;--------------------------------------------------------------------------------- &lt;br&gt;/-4.当前订单 &lt;br&gt;/--(1).名称 -&gt; 订单服务接口:获取订单最新状态 &lt;br&gt;/--(2).持仓 -&gt; 仓位查询接口:查询当前持仓 &lt;br&gt;/--(3).盈亏 -&gt; PNL服务接口:查询结算PNL &lt;br&gt;/--(4).操作作 -&gt; 订单服务接口:创建订单[前端调用:开仓,平仓,一键平仓都通过此接口] &lt;br&gt;================================================================================= &lt;br&gt;================================================================================= &lt;br&gt;Page3: &lt;br&gt;/- &lt;br&gt;/-1.技术指标 &lt;br&gt;/--(1). &lt;br&gt;/-2.左上页面 &lt;br&gt;/--(1).策略服务:根据策略名称获取策略相关参数 &lt;br&gt;/-2.左下页面 &lt;br&gt;/--(1).投资组合接口:获取用户投资组合[股票池/目标池都通过此接口]',
});
api.push({
    alias: 'CommandController',
    order: '6',
    desc: '系统指令服务[X]',
    link: '系统指令服务[x]',
    list: []
})
api[5].list.push({
    order: '1',
    desc: '更新参数 [内部接口]',
});
api[5].list.push({
    order: '2',
    desc: '安全更新参数',
});
api.push({
    alias: 'ProductController',
    order: '7',
    desc: '产品服务',
    link: '产品服务',
    list: []
})
api[6].list.push({
    order: '1',
    desc: '获取全部产品',
});
api[6].list.push({
    order: '2',
    desc: '获取指定产品信息',
});
api[6].list.push({
    order: '3',
    desc: '产品初始化',
});
api.push({
    alias: 'StatusController',
    order: '8',
    desc: '交易系统状态服务',
    link: '交易系统状态服务',
    list: []
})
api[7].list.push({
    order: '1',
    desc: '获取全部策略状态',
});
api[7].list.push({
    order: '2',
    desc: '发送状态指令',
});
api[7].list.push({
    order: '3',
    desc: '更新状态',
});
api.push({
    alias: 'PositionController',
    order: '9',
    desc: '仓位服务',
    link: '仓位服务',
    list: []
})
api[8].list.push({
    order: '1',
    desc: '查询当前持仓',
});
api.push({
    alias: 'InstrumentController',
    order: '10',
    desc: '交易标的接口',
    link: '交易标的接口',
    list: []
})
api[9].list.push({
    order: '1',
    desc: '获取结算信息',
});
api[9].list.push({
    order: '2',
    desc: '获取最新价格',
});
api[9].list.push({
    order: '3',
    desc: '更新最新价格 (內部接口)',
});
api[9].list.push({
    order: '4',
    desc: '获取交易费用',
});
api[9].list.push({
    order: '5',
    desc: '获取可交易标的',
});
api.push({
    alias: 'PortfolioController',
    order: '11',
    desc: '投资组合服务 (股票池/目标池)',
    link: '投资组合服务_(股票池/目标池)',
    list: []
})
api[10].list.push({
    order: '1',
    desc: '获取用户投资组合 [股票池/目标池都通过此接口]',
});
api.push({
    alias: 'SimulateController',
    order: '12',
    desc: '模拟测试',
    link: '模拟测试',
    list: []
})
api[11].list.push({
    order: '1',
    desc: '',
});
api.push({
    alias: 'OrderController',
    order: '13',
    desc: '订单服务',
    link: '订单服务',
    list: []
})
api[12].list.push({
    order: '1',
    desc: '查询Order',
});
api[12].list.push({
    order: '2',
    desc: '获取订单最新状态',
});
api[12].list.push({
    order: '3',
    desc: '创建订单 [前端调用: 开仓, 平仓, 一键平仓都通过此接口]',
});
api[12].list.push({
    order: '4',
    desc: '新增订单 [非前端界面调用]',
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