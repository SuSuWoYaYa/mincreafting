package com.cuisanzhang.mincreafting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hesuxiang on 17/10/2.
 */

public class TutorialListData {

//    https://raw.githubusercontent.com/wiki/hesuxiang/mincreafting/wiki/img

    public static final  int TUTORIAL_CODE_XINSHOUJIAOCHEN = 0;
    public static final  int TUTORIAL_CODE_HUANGJINGJIAOCHEN = 1;
    public static final  int TUTORIAL_CODE_JINGJIEZHINAN = 2;
    public static final  int TUTORIAL_CODE_BUILD = 3;
    public static final  int TUTORIAL_CODE_TIAOZHAN = 4;
    public static final  int TUTORIAL_CODE_CAIKUANGJISHU = 5;
    public static final  int TUTORIAL_CODE_ZHONGZHIJIAOCHENG = 6;
    public static final  int TUTORIAL_CODE_SHUAGUAIJIAOCHENG = 7;
    public static final  int TUTORIAL_CODE_FUMOSHAOLIAN = 8;
    public static final  int TUTORIAL_CODE_CHUJIHONGSHI = 9;
    public static final  int TUTORIAL_CODE_HONGSHIJINGJIE = 10;
    public static final  int TUTORIAL_CODE_GAOJIJISHU = 11;
    public static final  int TUTORIAL_CODE_MC163 = 12;
    public static final  int TUTORIAL_CODE_INTERNET = 13;

    public static List<String> getTutorialNamesByCode(int TutorialCode){

        switch (TutorialCode){
            case TUTORIAL_CODE_XINSHOUJIAOCHEN:
                return new ArrayList<String>(Arrays.asList(tutorial_xinshoujiaochen_names));
            case TUTORIAL_CODE_HUANGJINGJIAOCHEN:
                return new ArrayList<String>(Arrays.asList(tutorial_huangjingjieshao_names));
            case TUTORIAL_CODE_JINGJIEZHINAN:
                return new ArrayList<String>(Arrays.asList(tutorial_jingjiezhinan_names));
            case TUTORIAL_CODE_BUILD:
                return new ArrayList<String>(Arrays.asList(tutorial_build_names));
            case TUTORIAL_CODE_TIAOZHAN:
                return new ArrayList<String>(Arrays.asList(tutorial_tiaozhan_names));
            case TUTORIAL_CODE_CAIKUANGJISHU:
                return new ArrayList<String>(Arrays.asList(tutorial_caikuangjishu_names));
            case TUTORIAL_CODE_ZHONGZHIJIAOCHENG:
                return new ArrayList<String>(Arrays.asList(tutorial_zhongzhijiaocheng_names));
            case TUTORIAL_CODE_SHUAGUAIJIAOCHENG:
                return new ArrayList<String>(Arrays.asList(tutorial_shuaguaijiaocheng_names));
            case TUTORIAL_CODE_FUMOSHAOLIAN:
                return new ArrayList<String>(Arrays.asList(tutorial_fumoheshaolian_names));
            case TUTORIAL_CODE_CHUJIHONGSHI:
                return new ArrayList<String>(Arrays.asList(tutorial_chujihongshi_names));
            case TUTORIAL_CODE_HONGSHIJINGJIE:
                return new ArrayList<String>(Arrays.asList(tutorial_hongshijingjie_names));
            case TUTORIAL_CODE_GAOJIJISHU:
                return new ArrayList<String>(Arrays.asList(tutorial_gaojijishu_names));
            case TUTORIAL_CODE_MC163:
                return new ArrayList<String>(Arrays.asList(tutorial_mc163_names));
            case TUTORIAL_CODE_INTERNET:
                return new ArrayList<String>(Arrays.asList(tutorial_internet_names));
            default:
                break;
        }
        return new ArrayList<String>(Arrays.asList(tutorial_xinshoujiaochen_names));
    }

    public static ArrayList<String> getTutorialFilesByCode(int TutorialCode){
        switch (TutorialCode){
            case TUTORIAL_CODE_XINSHOUJIAOCHEN:
                return new ArrayList<String>(Arrays.asList(tutorial_xinshoujiaochen_files));
            case TUTORIAL_CODE_HUANGJINGJIAOCHEN:
                return new ArrayList<String>(Arrays.asList(tutorial_huangjingjieshao_files));
            case TUTORIAL_CODE_JINGJIEZHINAN:
                return new ArrayList<String>(Arrays.asList(tutorial_jingjiezhinan_files));
            case TUTORIAL_CODE_BUILD:
                return new ArrayList<String>(Arrays.asList(tutorial_build_files));
            case TUTORIAL_CODE_TIAOZHAN:
                return new ArrayList<String>(Arrays.asList(tutorial_tiaozhan_files));
            case TUTORIAL_CODE_CAIKUANGJISHU:
                return new ArrayList<String>(Arrays.asList(tutorial_caikuangjishu_files));
            case TUTORIAL_CODE_ZHONGZHIJIAOCHENG:
                return new ArrayList<String>(Arrays.asList(tutorial_zhongzhijiaocheng_files));
            case TUTORIAL_CODE_SHUAGUAIJIAOCHENG:
                return new ArrayList<String>(Arrays.asList(tutorial_shuaguaijiaocheng_files));
            case TUTORIAL_CODE_FUMOSHAOLIAN:
                return new ArrayList<String>(Arrays.asList(tutorial_fumoheshaolian_files));
            case TUTORIAL_CODE_CHUJIHONGSHI:
                return new ArrayList<String>(Arrays.asList(tutorial_chujihongshi_files));
            case TUTORIAL_CODE_HONGSHIJINGJIE:
                return new ArrayList<String>(Arrays.asList(tutorial_hongshijingjie_files));
            case TUTORIAL_CODE_GAOJIJISHU:
                return new ArrayList<String>(Arrays.asList(tutorial_gaojijishu_files));
            case TUTORIAL_CODE_MC163:
                return new ArrayList<String>(Arrays.asList(tutorial_mc163_files));
            case TUTORIAL_CODE_INTERNET:
                return new ArrayList<String>(Arrays.asList(tutorial_internet_files));
            default:
                break;
        }
        return new ArrayList<String>(Arrays.asList(tutorial_xinshoujiaochen_files));
    }



    public static String[] tutorial_xinshoujiaochen_names = {
            "新手指南",
            "第二天 ",
            "第三天",
            "饥饿管理",
            "不该做的事",
            "提示与技巧 "
    };


    public static String[] tutorial_xinshoujiaochen_files = {
            "html/wiki/day1.html",
            "html/wiki/day2.html",
            "html/wiki/day3.html",
            "html/wiki/jierguanli.html",
            "html/wiki/shouldnotdoing.html",
            "html/wiki/tishiyujiqiao.html"
    };


    public static String[] tutorial_huangjingjieshao_names = {
            "日夜交替",
            "降雨",
            "降雪",
            "雷暴",
            "液体流动机制",
            "高度",
            "区块",
            "虚空",
            "树木",

            "浮岛",
            "山丘",
            "海滩",
            "盆地",
            "海洋",
            "湖泊",
            "洞穴",
            "峡谷",
            "废弃矿井",
            "巨型蘑菇",
            "涌泉",
            "生苔的巨石",
            "冰刺",
            "化石",
            "宝藏",
            "珊瑚礁",
            "冰山",
            "荧石堆",

            "沉船",
            "水下遗迹",

            "沙漠水井",
            "沼泽小屋",
            "林地府邸",
            "雪屋",
            "村庄",
            "地牢",
            "要塞",
            "下界",
            "下界要塞",
            "末路之地",
            "征服海底遗迹",
            "征服沙漠神殿",
            "征服丛林神庙",
            "征服末地城",
            "边境之地",
            "去边境之地",
            "世界界限",
            "世界边界",
            "生物群系",
            "信标金字塔",
            "多人联机",
            "繁殖",
            "配方书",
            "经验值",
            "钓鱼",
            "僵尸围城",
            "潜影盒",
            "提示与技巧",
            "节省时间的小窍门",
    };


    public static String[] tutorial_huangjingjieshao_files = {
            "html/wiki/riyejiaoti.html",
            "html/wiki/jiangyu.html",
            "html/wiki/jiangxue.html",
            "html/wiki/leibao.html",
            "html/wiki/yetiliudong.html",
            "html/wiki/gaodu.html",
            "html/wiki/qukuai.html",
            "html/wiki/xukong.html",
            "html/wiki/shumu.html",

            "html/wiki/fudao.html", //浮岛
            "html/wiki/shanqiu.html", //山丘
            "html/wiki/haitang.html", //海滩
            "html/wiki/pengdi.html", //盆地
            "html/wiki/haiyang.html", //海洋
            "html/wiki/hubo.html", //湖泊
            "html/wiki/dongxue.html", //洞穴
            "html/wiki/xiagu.html", //峡谷
            "html/wiki/feiqikuangjing.html", //废弃矿井
            "html/wiki/juxingmogu.html", //巨型蘑菇
            "html/wiki/yongquang.html",// 涌泉
            "html/wiki/shengtaidejushi.html",// 生苔的巨石
            "html/wiki/bingchi.html", //冰刺
            "html/wiki/huashi.html", //化石
            "html/wiki/baozhang.html", //宝藏
            "html/wiki/shanghujiao.html", //珊瑚礁
            "html/wiki/bingshang.html", //冰山
            "html/wiki/yingshidui.html", //荧石堆

            "html/wiki/chenchuan.html",
            "html/wiki/shuixiayiji.html",


            "html/wiki/shamoshuijing.html",
            "html/wiki/zhaozhexiaowu.html",
            "html/wiki/lindifudi.html",
            "html/wiki/xuewu.html",
            "html/wiki/cunzhuang.html",
            "html/wiki/dilao.html",
            "html/wiki/yaosai.html",
            "html/wiki/xiajie.html",
            "html/wiki/xiajieyaosai.html",
            "html/wiki/moluzhidi.html",
            "html/wiki/zhengfuhaidiyiji.html",
            "html/wiki/zhengfushamoshendian.html",
            "html/wiki/zhengfuchonglingshenmiao.html",
            "html/wiki/zhengfumodicheng.html",
            "html/wiki/bianjingzhidi.html",
            "html/wiki/qubianjingzhidi.html",
            "html/wiki/shijiejixian.html",
            "html/wiki/shijiebianjie.html",
            "html/wiki/shenwuqunxi.html",
            "html/wiki/xinbiaojingzhita.html",
            "html/wiki/duorenliangji.html",
            "html/wiki/fangzhi.html",
            "html/wiki/peifangshu.html",
            "html/wiki/jingyangzhi.html",
            "html/wiki/diaoyu.html",
            "html/wiki/jiangshiweicheng.html",
            "html/wiki/qianyinghe.html",
            "html/wiki/tips.html",
            "html/wiki/jieshengshijiandexiaoqiaomen.html",
    };



    public static String[] tutorial_jingjiezhinan_names = {
            "双持",
            "进度列表",
            "成就列表",
            "成就指南",
            "拓殖",
            "战斗",
            "创建村庄",
            "家具",
            "马",
            "怎么找矿洞",
            "使用地图",
            "测量距离",
            "测量单位",
            "下界生存",
            "下界快速旅行",
            "末地生存",
            "支柱跳跃",
            "狼",
            "交易",
            "村庄机制",
            "防卫僵尸围城",
//            "虚空",
            "挂机池",
            "在服务器上游玩",
            "PvP",
            "多人游戏中的PvP基地",
            "服务器监狱",
    };

    public static String[] tutorial_jingjiezhinan_files = {
            "html/wiki/shuangchi.html",
            "html/wiki/jindu.html",
            "html/wiki/chenjiu.html",
            "html/wiki/get.html",
            "html/wiki/kuozhi.html",
            "html/wiki/fire.html",
            "html/wiki/build_town.html",
            "html/wiki/house_things.html",
            "html/wiki/horse.html",
            "html/wiki/how_to_find_kuandong.html",
            "html/wiki/use_map.html",
            "html/wiki/cheliangjuli.html",
            "html/wiki/cheliangdangwei.html",
            "html/wiki/xiajieshengchun.html",
            "html/wiki/xiajiekuaishulvxing.html",
            "html/wiki/modishengchun.html",
            "html/wiki/zhizhutiaoyue.html",
            "html/wiki/wolf.html",
            "html/wiki/trading.html",
            "html/wiki/chunzhuangjizhi.html",
            "html/wiki/fangzhijiangshiweicheng.html",
//            "html/wiki/xukong.html",
            "html/wiki/guajichi.html",
            "html/wiki/zaifuwuqishangyouwan.html",
            "html/wiki/pvp.html",
            "html/wiki/duorenyouxizhogndepvpjidi.html",
            "html/wiki/fuwuqijianyu.html",
    };
//


    public static String[] tutorial_build_names = {
            "建筑",
            "导航",
            "庇护所",
            "庇护所类型",

            "最佳居住生物群系",
            "给工程添加美感",
            "空气闸",
//            "建筑术语
//            "大都市 bigcity",__;;垃圾教程不收
            "安全的家园",
            "防御系统",
            "电梯",
            "停猪场",
            "常见的屋顶类型和屋顶指南",
            "暗门",
            "建造居住地",
            "水下建房子",
//        "墙壁和桥墩
            "墙壁类型",
            "水闸",
            "水车 水道",
            "无限水的水井",
            "码头",
    };

    public static String[] tutorial_build_files = {
            "html/wiki/build.html",
            "html/wiki/navigate.html",
            "html/wiki/safe_place.html",
            "html/wiki/house_type.html",

            "html/wiki/beast_place.html",
            "html/wiki/geigongchengtianjiameigan.html",
            "html/wiki/kongqizha.html",
//        3建筑术语
//        4大都市 bigcity.html",__;;垃圾教程不收
            "html/wiki/safetyhome.html",
            "html/wiki/fangyu.html",
            "html/wiki/dianti.html",
            "html/wiki/tingzhuchang.html",
            "html/wiki/wudingleixing.html",
            "html/wiki/anmeng.html",
            "html/wiki/jiangzhaojuzhudi.html",
            "html/wiki/shuixiafangzhi.html",
//        16墙壁和桥墩
            "html/wiki/qiangbi.html",
            "html/wiki/shuizha.html",
            "html/wiki/shuiche.html",
            "html/wiki/wuxiangshuijing.html",
            "html/wiki/matou.html",
    };




    public static String[] tutorial_tiaozhan_names = {

            "完成冒险",
            "在冒险模式中生存",
//            4挑战自定义地图
//            5制作自定义地图
            "极限模式",
//            11如何在一个地区中长期生存下去
            "在无限沙漠中生存",
//            13放牧心得
//            14超极限模式
//            15另类玩法
            "无聊的时候做什么",
            "在超平坦模式中生存",
//            18跑酷
            "在主世界造下界要塞",
    };

    public static String[] tutorial_tiaozhan_files = {

            "html/wiki/maoxian.html",
            "html/wiki/maoxianmoshixiadeshenchun.html",
//            4挑战自定义地图
//            5制作自定义地图
            "html/wiki/jixianmoshi.html",
//            11如何在一个地区中长期生存下去
            "html/wiki/zaiwuxianshamozhongshenchun.html",
//            13放牧心得
//            14超极限模式
//            15另类玩法
            "html/wiki/wuliaodeshihzuoshenme.html",
            "html/wiki/chaopingtangmoshishengchun.html",
//            18跑酷
            "html/wiki/jianzhaoxiajieyaosai.html",
    };
//



    public static String[] tutorial_caikuangjishu_names = {
            "采矿技术",
            "圆石生产器",
            "钻石",
            "黑曜石生产器",
    };

    public static String[] tutorial_caikuangjishu_files = {
            "html/wiki/caikuanjishu.html",
            "html/wiki/yuanshishengchangqi.html",
            "html/wiki/zhuanshi.html",
            "html/wiki/heiyaoshishenchangqi.html",
    };
//
//



    public static String[] tutorial_zhongzhijiaocheng_names = {

            "自动化农场",
            "仙人掌种植",
            "刷蛋糕",
            "农作物种植",
            "可可豆种植",
            "鸡蛋，羽毛",
            "花朵种植",
//鱼
            "刷冰",
            "肉-家畜养殖",
            "蘑菇种植",
            "刷唱片",
            "地狱疣种植",
            "西瓜和南瓜种植",
            "刷雪",
            "甘蔗种植",
            "树木种植",
            "藤蔓种植",
            "方块和物品复制",

    };

    public static String[] tutorial_zhongzhijiaocheng_files = {

            "html/wiki/zhidonghuanongchang.html",
            "html/wiki/xianrenzhangzhongzhi.html",
            "html/wiki/shuadanggao.html",
            "html/wiki/nongzhuowuzhongzhi.html",
            "html/wiki/kekedouzhongzhi.html",
            "html/wiki/jidan_yumao.html",
            "html/wiki/huaduozhongzhi.html",
//鱼
            "html/wiki/shuabing.html",
            "html/wiki/jiachuyangzhi.html",
            "html/wiki/moguzhongzhi.html",
            "html/wiki/shuachangpian.html",
            "html/wiki/diyuyouzhongzhi.html",
            "html/wiki/xiguahenanguazhongzhi.html",
            "html/wiki/shuaxue.html",
            "html/wiki/ganzhezhongzhi.html",
            "html/wiki/shumuzhongzhi.html",
            "html/wiki/mantengzhongzhi.html",
            "html/wiki/fangkuaiwuping_copy.html",
    };




    public static String[] tutorial_shuaguaijiaocheng_names = {
            "刷怪塔",
            "怪物磨床",
            "密集式饲养",
            "刷怪箱陷阱",
//            "动物
            "高压爬行者陷阱",
            "末影人陷阱",
            "守卫者陷阱",
            "铁傀儡陷阱",
            "岩浆怪陷阱",
            "史莱姆陷阱",
            "鱿鱼陷阱",
            "女巫陷阱",
            "凋灵陷阱",
            "凋灵骷髅陷阱",
            "僵尸猪人陷阱",
    };

    public static String[] tutorial_shuaguaijiaocheng_files = {

            "html/wiki/shuaguaita.html",
            "html/wiki/guaiwumochuang.html",
            "html/wiki/mijishiciyang.html",
            "html/wiki/shuaguaixiangxiangjin.html",
//            "html/wiki/动物
            "html/wiki/gaoyapaxingzhe.html",
            "html/wiki/moyingrenxiangjing.html",
            "html/wiki/shouweizhexiangjin.html",
            "html/wiki/tiekuileixiangjin.html",
            "html/wiki/yangjiangguaixiangjin.html",
            "html/wiki/shilaimuxiangjin.html",
            "html/wiki/youyuxiangjin.html",
            "html/wiki/nvwuxiangjin.html",
            "html/wiki/diaolingxiangjin.html",
            "html/wiki/diaolingkulouxiangjin.html",
            "html/wiki/jiangshizhurenxiangjin.html",
    };

//


    public static String[] tutorial_fumoheshaolian_names = {
            "附魔",
            "附魔机制",
            "铁砧机制",
            "自动化烧炼",
            "选择一种燃料",
    };

    public static String[] tutorial_fumoheshaolian_files = {
            "html/wiki/fumo.html",
            "html/wiki/fumojizhi.html",
            "html/wiki/tiezhangjizhi.html",
            "html/wiki/zhidonghuashaolian.html",
            "html/wiki/xuanzheyizhongrangliao.html",
    };
//



    public static String[] tutorial_chujihongshi_names = {
            "红石图例",
            "飞行技术",
            "命令详解(命令大全)",
            "命令方块",
            "漏斗",
            "机械装置",
            "密码门",
            "自动门",
            "红石机械",
            "红石音乐",
            "雪傀儡防御炮",
            "TNT大炮",
            "活板门利用",
            "陷阱",
            "陷阱设计",
            "鲁布·戈德堡机械",
            "红石闪光灯",
            "基本逻辑门",
            "初级红石电路",
            "红石电路",
            "时钟电路",
            "逻辑电路",
            "记忆电路",
            "传输电路",
            "脉冲电路",
            "杂项电路",
            "物品电梯‎",
            "方块更新感应器",
            "比较器更新感应器",
            "阳光传感器",
            "昼夜探测器",
    };
    public static String[] tutorial_chujihongshi_files = {
            "html/wiki/hongshituli.html",
            "html/wiki/feixingjishu.html",
            "html/wiki/mingling.html",
            "html/wiki/minglingfangkuai.html",
            "html/wiki/loudou.html",
            "html/wiki/jixiezhuangzhi.html",
            "html/wiki/mimameng.html",
            "html/wiki/zhidongmeng.html",
            "html/wiki/hongshijixie.html",
            "html/wiki/hongshiyingyue.html",
            "html/wiki/xuekuileifangyupao.html",
            "html/wiki/tntdapao.html",
            "html/wiki/huobanmengliyong.html",
            "html/wiki/xianjing.html",
            "html/wiki/xianjingsheji.html",
            "html/wiki/lubu_gedebaojixie.html",
            "html/wiki/hongshishanguangdeng.html",
            "html/wiki/jibenluojimeng.html",
            "html/wiki/chujichongshidianlu.html",
            "html/wiki/hongshidianlu.html",
            "html/wiki/shizhongdianlu.html",
            "html/wiki/luojidianlu.html",
            "html/wiki/jiyidianlu.html",
            "html/wiki/chuangshudianlu.html",
            "html/wiki/maichongdianlu.html",
            "html/wiki/zhaxiandianlu.html",
            "html/wiki/wupingdianti.html",
            "html/wiki/fangkuaigengxingganyingqi.html",
            "html/wiki/bijiaoqigengxingganyinqi.html",
            "html/wiki/yangguangchuangganqi.html",
            "html/wiki/zhouyetangcheqi.html",
    };




    public static String[] tutorial_hongshijingjie_names = {
            "火车站",
            "矿车",
            "矿车启动器",
            "矿车储存",
            "高级红石电路",
            "计算器",
            "命令统计值",
            "时钟",
            "猪随机产生器",
            "摩尔斯码",
            "红石电报机",
            "红石时钟电报机",
            "无延迟中继器",
            "利用活塞",
            "活塞电路",
            "半连接性",
            "Zero - tick pistons(0刻活塞)",
    };

    public static String[] tutorial_hongshijingjie_files = {
            "html/wiki/huochezhang.html",
            "html/wiki/kuangche.html",
            "html/wiki/kuangcheqidongqi.html",
            "html/wiki/kuanchechunchu.html",
            "html/wiki/gaojihongshidianlu.html",
            "html/wiki/jisuanqi.html",
            "html/wiki/minglingtongjizhi.html",
            "html/wiki/shizhong.html",
            "html/wiki/zhushuijichangshenqi.html",
            "html/wiki/moershima.html",
            "html/wiki/hongshidianbaoji.html",
            "html/wiki/hongshishizhongdianbaoji.html",
            "html/wiki/wuyanshizhongjiqi.html",
            "html/wiki/liyonghuoshai.html",
            "html/wiki/huoshaidianlu.html",
            "html/wiki/banlianjiexing.html",
            "html/wiki/lingkehuoshai.html",
    };




    public static String[] tutorial_gaojijishu_names = {
            "水域更新 1.13",
            "安装快照",
            "mods",
            "Mods安装",
            "制作Mods",
            "提高帧率",
            "安装 Forge Mod",
            "加载资源包",
            "制作资源包",
            "地图下载",
            "挑战自定义地图",
            "音效索引",
            "恢复损坏的地图数据",
            "将游戏数据保存到 Dropbox 云端",
            "将游戏数据保存到(仅限地图数据） Dropbox 云端",
            "在闪存盘中运行及保存Minecraft",
            "更新Java到最新版本",
//            "携带版",
            "架设服务器",
            "服务器启动脚本",
            "FreeBSD 启动脚本",
            "OpenBSD 启动脚本",
            "Ubuntu 启动脚本",
            "使用 Hamachi 进行局域网连接",
            "架设Forge服务器",
            "虚拟硬盘服务器",
            "使用ngrok搭建服务器",
    };
    public static String[] tutorial_gaojijishu_files = {
            "html/wiki/mcwiki1.13.html",
            "html/wiki/anzhuankuaizhao.html",
            "html/wiki/mods.html",
            "html/wiki/modsanzhuang.html",
            "html/wiki/makemods.html",
            "html/wiki/tigaozhenlv.html",
            "html/wiki/anzhuangforgemod.html",
            "html/wiki/jiazaizhiyuanbao.html",
            "html/wiki/zhizuozhiyuangbao.html",
            "html/wiki/dituxiazai.html",
            "html/wiki/tiaozhanzhidingyiditu.html",
            "html/wiki/xingxiaosuoying.html",
            "html/wiki/huifushunhuaideditushuju.html",
            "html/wiki/save_game_to_dropbox.html",
            "html/wiki/save_game_to_dropbox_only_map.html",
            "html/wiki/zaishancunpanzhongyunxingjibaocunyouxi.html",
            "html/wiki/update_java_to_last.html",
//            "html/wiki/xiedaiban.html",
            "html/wiki/jiashefuwuqi.html",
            "html/wiki/fuwuqiqidongjiaoben.html",
            "html/wiki/freebsd_qidongjiaoben.html",
            "html/wiki/openbsd_qidongjiaoben.html",
            "html/wiki/ubuntu_qidongjiaoben.html",
            "html/wiki/shiyonghamachijinxinjuyuwanglianjie.html",
            "html/wiki/jiasheforgefuwuqi.html",
            "html/wiki/xuniyingpanfuwuqi.html",
            "html/wiki/shiyongngrokdajianfuwuqi.html",
    };




    public static String[] tutorial_mc163_names = {
            "新手教程-初期生存必备的物品 ",
            "新手教程-石质工具 ",
            "新手教程-如何自己种植庄稼 ",
            "新手教程-动物的介绍 ",
            "新手教程-教你如何识别矿石 ",
            "新手教程-铁质工具 ",
            "新手教程-常见的一些怪物 ",
            "新手教程-钻石工具 ",
            "新手教程-如何钓鱼 ",
            "新手教程-鞍如何获得 ",
            "新手教程-皮革防具 ",
            "新手教程-如何创建地狱门 ",
            "新手教程-常用的工具 ",
            "新手教程-常用的防具：铁套 ",
            "新手教程-钻石防具 ",
            "新手教程-自然建筑:村庄 ",
            "新手教程-村民介绍 ",
            "新手教程-自然建筑:地牢 ",
            "新手教程-自然建筑:林地府邸 ",
            "新手教程-自然建筑:沙漠神殿 ",
            "新手教程-手游如何查看坐标 ",
            "新手教程-自然建筑:丛林神庙",
            "新手教程-附魔 重要教程, 新手必看",
            "新手教程-附魔的用处和效果",
            "新手教程-各种工具可附魔的列表",
            "新手教程-如何修复工具 ",
            "新手教程-常用指令大全",
            "新手教程-如何炼药",
            "新手教程-药水大全",
            "新手教程-那些主世界特殊的怪物",
            "新手教程-自然建筑:海底遗迹",
            "新手教程-羊驼的驯服和介绍 ",
            "新手教程-生物群系的介绍",
            "新手教程-下界的介绍",
            "新手教程-下界那些凶残的怪物",
            "新手教程-自然建筑:下界要塞",
            "新手教程-末路之地(最终地图介绍) ",
            "新手教程-终章:击杀末影龙",
            "[终末之诗]来，你们要的汉化版",
            "进阶教程-如何使用升级地图",
            "进阶教程-如何使用红石",
            "进阶教程-如何复活末影龙 ",
            "进阶教程-如何繁殖村民",
            "进阶教程-如何生成凋零",
            "进阶教程-如何使用观察者",
            "进阶教程-如何获得萝卜钓竿",
            "进阶教程-信标的作用以及使用方法",
            "进阶教程-各种红石元件的使用说明",
            "进阶教程-如何快速寻找到各种自然建筑",
            "进阶教程-红石比较器的用法",
            "进阶教程-各种铁轨、矿车的用途",
            "进阶教程-红石常用的小技巧",
            "进阶教程-如何安装马鞍和马铠",
            "进阶教程-前往末地城和末地船",
            "进阶教程-鞘翅的用处",
            "进阶教程-刷怪塔",
            "进阶教程-终章：快速击杀末影龙",
            "红石教程-简单实用的半自动西瓜、南瓜、甘蔗机",
            "红石教程-简单的2X2自动门",
            "红石教程-自动西瓜南瓜机",
            "红石教程-极简的全自动西瓜南瓜机",
            "红石教程-简单的全自动甘蔗机",
            "红石教程-自动熔炉",
            "红石教程-高速红石自动熔炉",
            "红石教程-超简单红石大炮(熊孩子必备)",
            "红石教程-半自动地狱疣装置",
            "红石教程-几种从下往上的红石线摆法",
            "红石教程-自动物品电梯",
            "红石教程-半自动简易农场(可堆叠)",
            "红石教程-诸葛连弩(就问你怕不怕)",
            "红石教程-自动孵鸡机",
            "红石教程-简单的商店制作",
            "红石教程-简易的上升电梯",
            "红石教程-高级商店、抽奖机制作",
            "红石教程-自动鸡肉机",
            "红石教程-自动蘑菇机",
            "红石教程-多轨道型发车系统",
            "红石教程-极简单的自动仙人掌收集器",
            "红石教程-自动刷雪机",
            "红石教程-物品分类机",
            "红石教程-自动刷铁机",
            "红石教程-飞行器",
            "红石教程-史莱姆农场",
            "红石教程-两层隐藏楼梯",
            "红石教程-高速电梯",
            "红石教程-刷充能铁轨机",
            "红石教程-选层电梯",
            "红石教程-固定式空对地轰炸机",
            "红石教程-3X3无痕隐藏门",
            "红石教程-简单的密码门",
            "红石教程-八合一红石商店",
            "红石教程-展示框密码门",
            "红石教程-拉杆式有序密码门",
            "红石教程-红石各种线路的讲解",
            "红石教程-自动水电梯",
            "红石教程-活塞伸缩桥",
            "红石教程-2X2内吸门",
            "红石教程-工作台熔炉转换装置",
            "红石教程-快速拉杆开关门",
            "红石教程-极简高速电梯(修改版)",
            "红石教程-平地隐藏箱子(玩家投稿)",
            "红石教程-滚动式密码门(玩家投稿)",
            "命令方块-命令方块的介绍和使用",
            "命令方块-简单的命令方块门",
            "命令方块-字幕制作",
            "命令方块-命令方块商店",
            "命令方块-命令方块对话",
            "建筑教程-建筑风格介绍",
            "建筑教程-现代装饰:喷泉",
            "建筑教程-现代装饰:道路",
            "建筑教程-现代装饰:路灯",
            "建筑教程-现代装饰:个性围墙",
            "建筑教程-现代装饰:花坛",
            "建筑教程-现代装饰:小汽车",
            "建筑教程-现代装饰:赛车",
            "建筑教程-现代装饰:直升机",
            "建筑教程-现代装饰:落地灯",
            "建筑教程-现代装饰:茶几",
            "建筑教程-现代装饰:吊灯",
            "建筑教程-现代装饰:大货车",
            "建筑教程-现代装饰:消防车",
            "建筑教程-现代装饰:沙滩",
            "建筑教程-现代装饰:喷泉装饰",
            "建筑教程-现代装饰:小型花坛",
            "建筑教程-中式装饰:古风池塘",
            "建筑教程-其他建筑:树屋",

            "建筑教程-现代装饰:简约卧室",
            "建筑教程-现代装饰:简约厨房",
            "建筑教程-树的装饰:枯萎的树木",
            "建筑教程-现代装饰:简单的客厅内饰1",
            "建筑教程-树的装饰:折断的树",
            "建筑教程-树的装饰:空洞的树桩",
            "建筑教程-树的装饰:大型树根",
            "建筑教程—现代装饰:洗浴间",
            "红石教程-平地隐藏楼梯",
            "红石教程-半自动刷石机",
            "红石教程-滚动式密码门",
            "红石教程-高速电梯(改良版)",
            "红石教程-平地隐藏箱子",
            "红石教程-简单的3X3下拉粘液块门",
            "红石教程-自动烤鸡机",
            "红石教程-简单的有序密码门",
            "红石教程-半自动开关地狱门(网易1.2.5版本)",
            "红石教程-自动物品电梯(网易1.2.5版本)",
            "红石教程-全自动甘蔗机(网易1.2.5版本)",
            "红石教程-全自动西瓜南瓜机(网易1.2.5版本)",
            "高级教程-全自动鸡肉机",


            "生存课1—刷怪笼改造",
            "生存课2—必学之刷铁机",
            "生存课3—刷石机、活塞式半自动农场和可可豆树！",
            "生存课4——改进过的高效全自动瓜田和甘蔗田！",
            "生存课5—村民工程！",

            "summon指令用法教程 召唤生物附属NBT指令",
            "summon指令标签大全",
            "Setblock指令标签",
            "scoreboard指令(详解)",
            "计算器相关 - 加法器 和 减法器",
            "计算器相关 - 负数减法器的实现",
            "计算器相关 - 负数加减法器|支持负数",
            "计算器相关 - 正负数乘法器与除法器",
            "我的世界龙蛋收集方法",
            "究竟什么是方块更新检测器",
            "漫谈延迟理论 ",
            "实体方块 ",
            "东南法则到底是什么 ",
            "自动化生产I 加工 生产方块 ",
            "自动化生产II 钓鱼、交易、生物资源 ",
            "粉红色花朵旗帜 ",
            "教你如何做一个上锁的箱子",
            "主城区域保护，你的主城由我来守护！",
            "基础指令详细教程","用选择器做出领地效果",
            "建筑教学-高级火柴盒改建~！",
            "呱叽收藏的光影包~",
            "从MC开始的java入门 p1.download!",
            "从MC开始的java入门 p2.setting!",
            "从MC开始的java入门 p3.package",
            "从MC开始的java入门 p4.HelloWorld",
            "从MC开始的java入门 p5.mainmod",
            "从MC开始的java入门 p6.clientServer",
            "从MC开始的java入门 p7.plusType",
            "从MC开始的java入门 p8.oopSchema",
            "从MC开始的java入门 p9.objectMethod",
            "红石教程-做一个高端的全自动双功能刷鸡机",
            "红石教程-原版MC红石实现3D展示台",
            "红石教程-毫无破绽,必中的红石陷阱",
            "如何做个彩虹信标","如何制造夜视药水",
            "教你做一个能坐的椅子",
            "教你制作一棵爆炸树",
            "爆炸树教程 ",
            "{全版本通用}两种游荡式刷怪塔【服务器可用】",

            "如何在我的世界中度过第一天",
            "我的世界怪物大盘点",
            "我的世界挖矿须知笔记",
            "《我的世界》矿石的介绍",
            "从零开始速造一个实用的家",
            "合成教学：我的世界合成介绍与用途.",
            "我的世界矿石的介绍",
            "食物介绍：饥饿值、饱和度与营养值",
            "我的世界生态环境介绍",
            "我的世界武器制造大全",
            "《我的世界》日常生物盘点",
            "不同环境下的村落介绍",
            "《我的世界》农作物及其用途",
            "《我的世界》染料完全介绍",
            "《我的世界》附魔属性介绍",
            "快速获取附魔武器：村民交易的商品",
            "《我的世界》烟花的介绍",
            "快速收集末地城宝藏攻略",
            "简易钓鱼机",
            "更加真实的矿车：自动变轨装置攻略",
            "MC中的计算器！简单串行乘法器",
            "随机数生成器",
            "一键命令方块基础",
            "红石矿车高速熔炉攻略",
            "远程捕捉村民",
            "减轻你的包袱！打包机攻略",
            "通往下界的捷径：用水和熔岩造下界传送门",
            "墙后的暗门：4×4活塞门教程",
            "《我的世界》红石刷怪塔教程",
            "单片隐藏顶灯小教程",
            "《我的世界》刷怪箱陷阱教程",
            "无限圆石轻松采集：红石刷石机",
            "制作需要钥匙的红石门",
            "自动烤肉机",
            "不挖矿就能无限刷铁",
            "全品种刷轨机",
            "无限造冰装置",
            "半自动造雪机",
            "全自动养鸡屠鸡场",
            "半自动可可豆农场",
            "全自动仙人掌塔",

//            42
            "《我的世界》铁砧的用途",
//43
            "全面的附魔环境：高等级附魔台介绍",
//44
            "植物完全指南：普通植物的妙用",
//45
            "《我的世界》特殊生物的战斗方法",
//46
            "《我的世界》趣味物品的使用",
//47
            "《我的世界》盔甲指南",
//48
            "《我的世界》合成配方",
//49
            "轻松收获甘蔗的机器",
//50
            "冰上秀航技：冰上赛船",
//51
            "竞技的乐趣：PVP角斗场",
//52
            "建筑的方向：初建一个房子",
//53
            "地面的惊喜：平地隐藏楼梯",
//54
            "房子的秘密：隐形的活塞门",
//55
            "航天的梦想：飞机模型制作",
//56
            "《我的世界》自动收瓜机",
//57
            "高效垂直挖矿技巧",
            //58
            "解决饥饿！《我的世界》无需合成的食物",
    };
    public static String[] tutorial_mc163_files = {

            "html/mcbbs/mcbbs1.html",
            "html/mcbbs/mcbbs2.html",
            "html/mcbbs/mcbbs3.html",
            "html/mcbbs/mcbbs4.html",
            "html/mcbbs/mcbbs5.html",
            "html/mcbbs/mcbbs6.html",
            "html/mcbbs/mcbbs7.html",
            "html/mcbbs/mcbbs8.html",
            "html/mcbbs/mcbbs9.html",

            "html/mcbbs/mcbbs10.html",
            "html/mcbbs/mcbbs11.html",
            "html/mcbbs/mcbbs12.html",
            "html/mcbbs/mcbbs13.html",
            "html/mcbbs/mcbbs14.html",
            "html/mcbbs/mcbbs15.html",
            "html/mcbbs/mcbbs16.html",
            "html/mcbbs/mcbbs17.html",
            "html/mcbbs/mcbbs18.html",
            "html/mcbbs/mcbbs19.html",

            "html/mcbbs/mcbbs20.html",
            "html/mcbbs/mcbbs21.html",
            "html/mcbbs/mcbbs22.html",
            "html/mcbbs/mcbbs23.html",
            "html/mcbbs/mcbbs24.html",
            "html/mcbbs/mcbbs25.html",
            "html/mcbbs/mcbbs26.html",
            "html/mcbbs/mcbbs27.html",
            "html/mcbbs/mcbbs28.html",
            "html/mcbbs/mcbbs29.html",

            "html/mcbbs/mcbbs30.html",
            "html/mcbbs/mcbbs31.html",
            "html/mcbbs/mcbbs32.html",
            "html/mcbbs/mcbbs33.html",
            "html/mcbbs/mcbbs34.html",
            "html/mcbbs/mcbbs35.html",
            "html/mcbbs/mcbbs36.html",
            "html/mcbbs/mcbbs37.html",
            "html/mcbbs/mcbbs38.html",
            "html/mcbbs/mcbbs39.html",

            "html/mcbbs/mcbbs40.html",
            "html/mcbbs/mcbbs41.html",
            "html/mcbbs/mcbbs42.html",
            "html/mcbbs/mcbbs43.html",
            "html/mcbbs/mcbbs44.html",
            "html/mcbbs/mcbbs45.html",
            "html/mcbbs/mcbbs46.html",
            "html/mcbbs/mcbbs47.html",
            "html/mcbbs/mcbbs48.html",
            "html/mcbbs/mcbbs49.html",

            "html/mcbbs/mcbbs50.html",
            "html/mcbbs/mcbbs51.html",
            "html/mcbbs/mcbbs52.html",
            "html/mcbbs/mcbbs53.html",
            "html/mcbbs/mcbbs54.html",
            "html/mcbbs/mcbbs55.html",
            "html/mcbbs/mcbbs56.html",
            "html/mcbbs/mcbbs57.html",
            "html/mcbbs/mcbbs58.html",
            "html/mcbbs/mcbbs59.html",

            "html/mcbbs/mcbbs60.html",
            "html/mcbbs/mcbbs61.html",
            "html/mcbbs/mcbbs62.html",
            "html/mcbbs/mcbbs63.html",
            "html/mcbbs/mcbbs64.html",
            "html/mcbbs/mcbbs65.html",
            "html/mcbbs/mcbbs66.html",
            "html/mcbbs/mcbbs67.html",
            "html/mcbbs/mcbbs68.html",
            "html/mcbbs/mcbbs69.html",

            "html/mcbbs/mcbbs70.html",
            "html/mcbbs/mcbbs71.html",
            "html/mcbbs/mcbbs72.html",
            "html/mcbbs/mcbbs73.html",
            "html/mcbbs/mcbbs74.html",
            "html/mcbbs/mcbbs75.html",
            "html/mcbbs/mcbbs76.html",
            "html/mcbbs/mcbbs77.html",
            "html/mcbbs/mcbbs78.html",
            "html/mcbbs/mcbbs79.html",

            "html/mcbbs/mcbbs80.html",
            "html/mcbbs/mcbbs81.html",
            "html/mcbbs/mcbbs82.html",
            "html/mcbbs/mcbbs83.html",
            "html/mcbbs/mcbbs84.html",
            "html/mcbbs/mcbbs85.html",
            "html/mcbbs/mcbbs86.html",
            "html/mcbbs/mcbbs87.html",
            "html/mcbbs/mcbbs88.html",
            "html/mcbbs/mcbbs89.html",

            "html/mcbbs/mcbbs90.html",
            "html/mcbbs/mcbbs91.html",
            "html/mcbbs/mcbbs92.html",
            "html/mcbbs/mcbbs93.html",
            "html/mcbbs/mcbbs94.html",
            "html/mcbbs/mcbbs95.html",
            "html/mcbbs/mcbbs96.html",
            "html/mcbbs/mcbbs97.html",
            "html/mcbbs/mcbbs98.html",
            "html/mcbbs/mcbbs99.html",

            "html/mcbbs/mcbbs100.html",
            "html/mcbbs/mcbbs101.html",
            "html/mcbbs/mcbbs102.html",
            "html/mcbbs/mcbbs103.html",
            "html/mcbbs/mcbbs104.html",
            "html/mcbbs/mcbbs105.html",
            "html/mcbbs/mcbbs106.html",
            "html/mcbbs/mcbbs107.html",
            "html/mcbbs/mcbbs108.html",
            "html/mcbbs/mcbbs109.html",
            "html/mcbbs/mcbbs110.html",
            "html/mcbbs/mcbbs111.html",
            "html/mcbbs/mcbbs112.html",
            "html/mcbbs/mcbbs113.html",
            "html/mcbbs/mcbbs114.html",
            "html/mcbbs/mcbbs115.html",
            "html/mcbbs/mcbbs116.html",
            "html/mcbbs/mcbbs117.html",
            "html/mcbbs/mcbbs118.html",
            "html/mcbbs/mcbbs119.html",

            "html/mcbbs/mcbbs120.html",
            "html/mcbbs/mcbbs121.html",
            "html/mcbbs/mcbbs122.html",
            "html/mcbbs/mcbbs123.html",
            "html/mcbbs/mcbbs124.html",
            "html/mcbbs/mcbbs125.html",
            "html/mcbbs/mcbbs126.html",


            "html/mcbbs/mcbbs127.html",
            "html/mcbbs/mcbbs128.html",
            "html/mcbbs/mcbbs129.html",
            "html/mcbbs/mcbbs130.html",
            "html/mcbbs/mcbbs131.html",
            "html/mcbbs/mcbbs132.html",
            "html/mcbbs/mcbbs133.html",
            "html/mcbbs/mcbbs134.html",
            "html/mcbbs/mcbbs135.html",
            "html/mcbbs/mcbbs136.html",
            "html/mcbbs/mcbbs137.html",
            "html/mcbbs/mcbbs138.html",
            "html/mcbbs/mcbbs139.html",
            "html/mcbbs/mcbbs140.html",
            "html/mcbbs/mcbbs141.html",
            "html/mcbbs/mcbbs142.html",
            "html/mcbbs/mcbbs143.html",
            "html/mcbbs/mcbbs144.html",
            "html/mcbbs/mcbbs145.html",
            "html/mcbbs/mcbbs146.html",
            "html/mcbbs/mcbbs147.html",

            "html/mcbbs/mcbbs_apple1.html",
            "html/mcbbs/mcbbs_apple2.html",
            "html/mcbbs/mcbbs_apple3.html",
            "html/mcbbs/mcbbs_apple4.html",
            "html/mcbbs/mcbbs_apple5.html",

            "html/mcbbs/mcbbs_other_1.html",
            "html/mcbbs/mcbbs_other_2.html",
            "html/mcbbs/mcbbs_other_3.html",
            "html/mcbbs/mcbbs_other_4.html",
            "html/mcbbs/mcbbs_other_5.html",
            "html/mcbbs/mcbbs_other_6.html",
            "html/mcbbs/mcbbs_other_7.html",
            "html/mcbbs/mcbbs_other_8.html",
            "html/mcbbs/mcbbs_other_9.html",
            "html/mcbbs/mcbbs_other_10.html",

            "html/mcbbs/mcbbs_other_11.html",
            "html/mcbbs/mcbbs_other_12.html",
            "html/mcbbs/mcbbs_other_13.html",
            "html/mcbbs/mcbbs_other_14.html",
            "html/mcbbs/mcbbs_other_15.html",
            "html/mcbbs/mcbbs_other_16.html",
            "html/mcbbs/mcbbs_other_17.html",
            "html/mcbbs/mcbbs_other_18.html",
            "html/mcbbs/mcbbs_other_19.html",
            "html/mcbbs/mcbbs_other_20.html",

            "html/mcbbs/mcbbs_other_21.html",
            "html/mcbbs/mcbbs_other_22.html",
            "html/mcbbs/mcbbs_other_23.html",
            "html/mcbbs/mcbbs_other_24.html",
            "html/mcbbs/mcbbs_other_25.html",
            "html/mcbbs/mcbbs_other_26.html",
            "html/mcbbs/mcbbs_other_27.html",
            "html/mcbbs/mcbbs_other_28.html",
            "html/mcbbs/mcbbs_other_29.html",
            "html/mcbbs/mcbbs_other_30.html",

            "html/mcbbs/mcbbs_other_31.html",
            "html/mcbbs/mcbbs_other_32.html",
            "html/mcbbs/mcbbs_other_33.html",
            "html/mcbbs/mcbbs_other_34.html",
            "html/mcbbs/mcbbs_other_35.html",
            "html/mcbbs/mcbbs_other_36.html",
            "html/mcbbs/mcbbs_other_37.html",
            "html/mcbbs/mcbbs_other_38.html",
            "html/mcbbs/mcbbs_other_39.html",
            "html/mcbbs/mcbbs_other_40.html",

            "html/mc163/mc163_1.html",
            "html/mc163/mc163_2.html",
            "html/mc163/mc163_3.html",
            "html/mc163/mc163_4.html",
            "html/mc163/mc163_5.html",
            "html/mc163/mc163_6.html",
            "html/mc163/mc163_7.html",
            "html/mc163/mc163_8.html",
            "html/mc163/mc163_9.html",

            "html/mc163/mc163_10.html",
            "html/mc163/mc163_11.html",
            "html/mc163/mc163_12.html",
            "html/mc163/mc163_13.html",
            "html/mc163/mc163_14.html",
            "html/mc163/mc163_15.html",
            "html/mc163/mc163_16.html",
            "html/mc163/mc163_17.html",
            "html/mc163/mc163_18.html",
            "html/mc163/mc163_19.html",

            "html/mc163/mc163_20.html",
            "html/mc163/mc163_21.html",
            "html/mc163/mc163_22.html",
            "html/mc163/mc163_23.html",
            "html/mc163/mc163_24.html",
            "html/mc163/mc163_25.html",
            "html/mc163/mc163_26.html",
            "html/mc163/mc163_27.html",
            "html/mc163/mc163_28.html",
            "html/mc163/mc163_29.html",

            "html/mc163/mc163_30.html",
            "html/mc163/mc163_31.html",
            "html/mc163/mc163_32.html",
            "html/mc163/mc163_33.html",
            "html/mc163/mc163_34.html",
            "html/mc163/mc163_35.html",
            "html/mc163/mc163_36.html",
            "html/mc163/mc163_37.html",
            "html/mc163/mc163_38.html",
            "html/mc163/mc163_39.html",

            "html/mc163/mc163_40.html",
            "html/mc163/mc163_41.html",
            "html/mc163/mc163_42.html",
            "html/mc163/mc163_43.html",
            "html/mc163/mc163_44.html",
            "html/mc163/mc163_45.html",
            "html/mc163/mc163_46.html",
            "html/mc163/mc163_47.html",
            "html/mc163/mc163_48.html",
            "html/mc163/mc163_49.html",

            "html/mc163/mc163_50.html",
            "html/mc163/mc163_51.html",
            "html/mc163/mc163_52.html",
            "html/mc163/mc163_53.html",
            "html/mc163/mc163_54.html",
            "html/mc163/mc163_55.html",
            "html/mc163/mc163_56.html",
            "html/mc163/mc163_57.html",
            "html/mc163/mc163_58.html",

    };




    public static String[] tutorial_internet_names = {


            "我的世界命令教程(国服手机端)",
            "我的世界手机端附魔教程",
            "我的世界命名符彩蛋",
            "我的世界药水代码教程",
            "我的世界怎么打出彩色字体的教程",
            "史上最简单的半自动门",

            "命令进阶-引言",
            "命令进阶-1 基础概念",
            "命令进阶-1.1 坐标、朝向、区块",
            "命令进阶-1.2 时间、顺序",
            "命令进阶-1.3 命令执行",
            "命令进阶-1.4 目标选择器",
            "命令进阶-1.5 命令执行统计",
            "命令进阶-2 常用格式",
            "命令进阶-2.1 JSON文本",
            "命令进阶-2.1.1 JSON文本类型",
            "命令进阶-2.1.2 JSON样式",
            "命令进阶-2.1.3 JSON事件",
            "命令进阶-2.1.4 JSON分项",
            "命令进阶-2.1.5 告示牌及成书",
            "命令进阶-2.1.6 杂项",
            "命令进阶-2.1.A 附录: keybind列表",
            "命令进阶-2.2 NBT及结构",
            "命令进阶-2.2.1 基本类型",
            "命令进阶-2.2.2 NBT实战",
            "命令进阶-3 如何执行命令",
            "命令进阶-3.1 聊天栏",
            "命令进阶-3.2 命令方块简介",
            "命令进阶-3.3 命令函数",
            "命令进阶-4 记分板",
            "命令进阶-4.1 记分板变量",
            "命令进阶-4.2 记分板队伍",
            "命令进阶-4.3 记分板标签",
            "命令进阶-4.4 记分板命令",
            "命令进阶-4.5 记分板实战",
            "命令进阶-4.5.1 循环电路|记分板的基础操作",
            "命令进阶-4.5.2 自定义命令|stats与记分板",
            "命令进阶-4.5.3 击杀率|operation与准则",
            "命令进阶-4.5.4 漂亮的排名|显示与队伍",
            "命令进阶-4.5.5 简单登入系统|function与综合运用",
            "命令进阶-5 基础逻辑",
            "命令进阶-5.1 穷举",
            "命令进阶-5.2 分数处理",
            "命令进阶-5.3 布尔逻辑",
            "命令进阶-5.4 循环",
            "命令进阶-5.5 事件",
            "命令进阶-5.6 标记实体(Marker)",
            "命令进阶-5.7 execute进阶",
            "命令进阶-6 实战",
            "命令进阶-6.1 游戏内物件",
            "命令进阶-6.2 命令系统测试及调试",
            "命令进阶-6.3 更多命令系统例子",
            "命令进阶-6.3.1 熔炉过热系统",
            "命令进阶-6.3.2 毒苹果",
            "命令进阶-6.3.3 下雨检测",
            "命令进阶-6.3.4 替换方块实体",
            "命令进阶-6.3.5 技能系统",
            "命令进阶-6.3.6 皇冠系统",
            "命令进阶-6.3.7 黑洞",
            "命令进阶-6.3.8 登录系统",
            "命令进阶-7 游戏文件的使用",
            "命令进阶-7.1 资源包",
            "命令进阶-7.1.1 材质",
            "命令进阶-7.1.2 模型",
            "命令进阶-7.1.3 声音",
            "命令进阶-7.2 Loot table",
            "命令进阶-7.3 进度(Advancement)",
            "命令进阶-7.3.1 Advancement例子",
            "命令进阶-8.1 如何成为一个成功的CBer",
            "命令进阶-8.3 问问题的技巧",
            "命令进阶-一些来自其他CBer的建言",

            "Forge API制作MOD—设置工作环境(配置环境原来如此简单)",
            "Minecraft MOD制作 从入门到精通",
            "Minecraft MOD制作 第一章:学习Java",
            "Minecraft MOD制作 第二章:计划与头脑风暴",
            "Minecraft MOD制作 第三章:设置工作环境",
            "Minecraft MOD制作 第四章1:您的第一个方块",
            "Minecraft MOD制作 第四章2:为您的方块添加材质" ,
            "Minecraft MOD制作 第四章3:创建一份合成表",
            "Minecraft MOD制作 第四章4:生成矿藏",
            "Minecraft MOD制作 第四章5:添加烧炼与创建物品" ,
            "Minecraft MOD制作 第四章6:创建一组工具",
            "Minecraft MOD制作 第四章7:创建一套盔甲",
            "Minecraft MOD制作 第四章8:添加多材质方块",

            "基本操作与合成玩法",
            "挖矿教程",
            "食物与生命值",
            "种植教程",
            "养殖",
            "鸡蛋收集牧场",
            "战斗及挖矿进阶教程",
            "红石",
            "半自动甘蔗场",
            "远程打击",
            "附魔师",
            "下界要塞",
            "酿造药水",
            "农业进阶教程",
            "钓鱼教程",
            "探索-废弃矿洞",
            "探索-沙漠神庙与丛林神庙",
            "探索-海底遗迹",
            "探索-村庄",
            "马",
            "终末之地 1",
            "终末之地 2",
            "无限水",
            "自动小麦田",
            "简单活塞门和自动门",
            "生存冷门技巧",
            "刷石机",
            "刷钻石技巧",
            "梯田形半自动收割机",
            "红石电路基础",
            "简单易懂实用的刷怪塔",
            "刷怪笼简易改造",
//            "自动西瓜南瓜机制作教学",
            "半自动可可豆收割机",
            "超省材料半自动农田",
            "自动西瓜南瓜机制作",

    };

    public static String[] tutorial_internet_files = {

            "html/wangluojiaocheng/costem1.html",
            "html/wangluojiaocheng/costem2.html",
            "html/wangluojiaocheng/costem3.html",
            "html/wangluojiaocheng/costem4.html",
            "html/wangluojiaocheng/costem6.html",
            "html/wangluojiaocheng/costem5.html",


            "html/wangluojiaocheng/command_tutorial1.html",
            "html/wangluojiaocheng/command_tutorial2.html",
            "html/wangluojiaocheng/command_tutorial3.html",
            "html/wangluojiaocheng/command_tutorial4.html",
            "html/wangluojiaocheng/command_tutorial5.html",
            "html/wangluojiaocheng/command_tutorial6.html",
            "html/wangluojiaocheng/command_tutorial7.html",
            "html/wangluojiaocheng/command_tutorial8.html",
            "html/wangluojiaocheng/command_tutorial9.html",
            "html/wangluojiaocheng/command_tutorial10.html",

            "html/wangluojiaocheng/command_tutorial11.html",
            "html/wangluojiaocheng/command_tutorial12.html",
            "html/wangluojiaocheng/command_tutorial13.html",
            "html/wangluojiaocheng/command_tutorial14.html",
            "html/wangluojiaocheng/command_tutorial15.html",
            "html/wangluojiaocheng/command_tutorial16.html",
            "html/wangluojiaocheng/command_tutorial17.html",
            "html/wangluojiaocheng/command_tutorial18.html",
            "html/wangluojiaocheng/command_tutorial19.html",
            "html/wangluojiaocheng/command_tutorial20.html",

            "html/wangluojiaocheng/command_tutorial21.html",
            "html/wangluojiaocheng/command_tutorial22.html",
            "html/wangluojiaocheng/command_tutorial23.html",
            "html/wangluojiaocheng/command_tutorial24.html",
            "html/wangluojiaocheng/command_tutorial25.html",
            "html/wangluojiaocheng/command_tutorial26.html",
            "html/wangluojiaocheng/command_tutorial27.html",
            "html/wangluojiaocheng/command_tutorial28.html",
            "html/wangluojiaocheng/command_tutorial29.html",
            "html/wangluojiaocheng/command_tutorial30.html",

            "html/wangluojiaocheng/command_tutorial31.html",
            "html/wangluojiaocheng/command_tutorial32.html",
            "html/wangluojiaocheng/command_tutorial33.html",
            "html/wangluojiaocheng/command_tutorial34.html",
            "html/wangluojiaocheng/command_tutorial35.html",
            "html/wangluojiaocheng/command_tutorial36.html",
            "html/wangluojiaocheng/command_tutorial37.html",
            "html/wangluojiaocheng/command_tutorial38.html",
            "html/wangluojiaocheng/command_tutorial39.html",
            "html/wangluojiaocheng/command_tutorial40.html",

            "html/wangluojiaocheng/command_tutorial41.html",
            "html/wangluojiaocheng/command_tutorial42.html",
            "html/wangluojiaocheng/command_tutorial43.html",
            "html/wangluojiaocheng/command_tutorial44.html",
            "html/wangluojiaocheng/command_tutorial45.html",
            "html/wangluojiaocheng/command_tutorial46.html",
            "html/wangluojiaocheng/command_tutorial47.html",
            "html/wangluojiaocheng/command_tutorial48.html",
            "html/wangluojiaocheng/command_tutorial49.html",
            "html/wangluojiaocheng/command_tutorial50.html",

            "html/wangluojiaocheng/command_tutorial51.html",
            "html/wangluojiaocheng/command_tutorial52.html",
            "html/wangluojiaocheng/command_tutorial53.html",
            "html/wangluojiaocheng/command_tutorial54.html",
            "html/wangluojiaocheng/command_tutorial55.html",
            "html/wangluojiaocheng/command_tutorial56.html",
            "html/wangluojiaocheng/command_tutorial57.html",
            "html/wangluojiaocheng/command_tutorial58.html",
            "html/wangluojiaocheng/command_tutorial59.html",
            "html/wangluojiaocheng/command_tutorial60.html",

            "html/wangluojiaocheng/command_tutorial61.html",
            "html/wangluojiaocheng/command_tutorial62.html",
            "html/wangluojiaocheng/command_tutorial63.html",
            "html/wangluojiaocheng/command_tutorial64.html",
            "html/wangluojiaocheng/command_tutorial65.html",


            "html/wangluojiaocheng/mcbbs_outsideor.html",
            "html/wangluojiaocheng/mcbbs_djxgame1.html",
            "html/wangluojiaocheng/mcbbs_djxgame2.html",
            "html/wangluojiaocheng/mcbbs_djxgame3.html",
            "html/wangluojiaocheng/mcbbs_djxgame4.html",
            "html/wangluojiaocheng/mcbbs_djxgame5.html",
            "html/wangluojiaocheng/mcbbs_djxgame6.html",
            "html/wangluojiaocheng/mcbbs_djxgame7.html",
            "html/wangluojiaocheng/mcbbs_djxgame8.html",
            "html/wangluojiaocheng/mcbbs_djxgame9.html",
            "html/wangluojiaocheng/mcbbs_djxgame10.html",
            "html/wangluojiaocheng/mcbbs_djxgame11.html",
            "html/wangluojiaocheng/mcbbs_djxgame12.html",

            "html/wangluojiaocheng/jiaochen1.html",
            "html/wangluojiaocheng/jiaochen2.html",
            "html/wangluojiaocheng/jiaochen3.html",
            "html/wangluojiaocheng/jiaochen4.html",
            "html/wangluojiaocheng/jiaochen5.html",
            "html/wangluojiaocheng/jiaochen6.html",
            "html/wangluojiaocheng/jiaochen7.html",
            "html/wangluojiaocheng/jiaochen8.html",
            "html/wangluojiaocheng/jiaochen9.html",
            "html/wangluojiaocheng/jiaochen10.html",
            "html/wangluojiaocheng/jiaochen11.html",
            "html/wangluojiaocheng/jiaochen12.html",
            "html/wangluojiaocheng/jiaochen13.html",
            "html/wangluojiaocheng/jiaochen14.html",
            "html/wangluojiaocheng/jiaochen15.html",
            "html/wangluojiaocheng/jiaochen16.html",
            "html/wangluojiaocheng/jiaochen17.html",
            "html/wangluojiaocheng/jiaochen18.html",
            "html/wangluojiaocheng/jiaochen19.html",
            "html/wangluojiaocheng/jiaochen20.html",
            "html/wangluojiaocheng/jiaochen21.html",
            "html/wangluojiaocheng/jiaochen22.html",
            "html/wangluojiaocheng/jiaochen23.html",
            "html/wangluojiaocheng/jiaochen24.html",
            "html/wangluojiaocheng/jiaochen25.html",
            "html/wangluojiaocheng/jiaochen26.html",
            "html/wangluojiaocheng/jiaochen27.html",
            "html/wangluojiaocheng/jiaochen28.html",
            "html/wangluojiaocheng/jiaochen29.html",
            "html/wangluojiaocheng/jiaochen30.html",
            "html/wangluojiaocheng/jiaochen31.html",
            "html/wangluojiaocheng/jiaochen32.html",
//            "html/wangluojiaocheng/jiaochen33.html",
            "html/wangluojiaocheng/jiaochen34.html",
            "html/wangluojiaocheng/jiaochen35.html",
            "html/wangluojiaocheng/jiaochen36.html",

    };





}
