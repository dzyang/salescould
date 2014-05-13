package com.example.utils;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Sodino E-mail:sodinoopen@hotmail.com
 * @version Time：2011-8-20 下午08:41:25
 */
public class ChinaCityUtil {
	public static final String CHINA = "中国";
	public static final int TYPE_PROVINCE = 1;
	public static final int TYPE_CITY = 2;
	public static final int TYPE_REGION = 3;

	public static Hashtable<String, Hashtable<String, String[]>> initChinaCitysHashtable() {
		Hashtable<String, Hashtable<String, String[]>> tmpProvince = new Hashtable<String, Hashtable<String, String[]>>();
		// 从小地区开始构建
		Hashtable<String, String[]> tmpCity = null;
		// 北京市
		tmpCity = new Hashtable<String, String[]>();
		tmpCity.put("北京市", new String[] { "昌平区", "朝阳区", "崇文区", "大兴区", "东城区", "房山区", "丰台区", "海淀区",
				"怀柔区", "门头沟", "密云区", "平谷区", "石景山区", "顺义区", "通州区", "西城区", "宣武区", "延庆县" });
		tmpProvince.put("北京市", tmpCity);
		// 安徽省
		tmpCity = new Hashtable<String, String[]>();
		tmpCity.put("安庆市", new String[] { "大观区", "宜秀区", "迎江区" });
		tmpCity.put("蚌埠市", new String[] { "蚌山区", "龙子湖区", "禹会区" });
		tmpCity.put("亳州市", new String[] { "谯城区" });
		tmpCity.put("巢湖市", new String[] { "居巢区" });
		tmpCity.put("滁州市", new String[] { "琅琊区", "南谯区" });
		tmpCity.put("阜阳市", new String[] { "太和县", "颖东区", "颖泉区", "颖州区" });
		tmpCity.put("合肥市", new String[] { "包河区", "庐阳区", "蜀山区", "瑶海区" });
		tmpCity.put("淮北市", new String[] { "杜集区", "相山区" });
		tmpCity.put("淮南市", new String[] { "大通区", "田家庵区" });
		tmpCity.put("黄山市", new String[] { "屯溪区", "休宁县" });
		tmpCity.put("六安市", new String[] { "金安区", "裕安区" });
		tmpCity.put("马鞍山市", new String[] { "花山区", "金家庄区", "雨山区" });
		tmpCity.put("宿州市", new String[] { "埇桥区" });
		tmpCity.put("铜陵市", new String[] { "铜官山区 狮子山区" });
		tmpCity.put("芜湖市", new String[] { "镜湖区", "鸠江区", "南陵县", "芜湖县", "弋江区" });
		tmpCity.put("宣城市", new String[] { "泾县", "宣州区" });
		tmpCity.put("池州市", new String[] { "贵池区" });
		tmpProvince.put("安徽省", tmpCity);
		// 重庆市
		tmpCity = new Hashtable<String, String[]>();
		tmpCity.put("重庆市", new String[] { "巴南区", "北碚区", "璧山县", "长寿县", "大渡口区", "大足县", "垫江县", "涪陵区",
				"合川区", "江北区", "江津区", "九龙坡区", "南岸区", "南川区", "綦江县", "黔江区", "荣昌县", "沙坪坝区", "铜梁县",
				"万州区", "永川区", "渝北区", "渝中区", "云阳县", "忠县" });
		tmpProvince.put("重庆市", tmpCity);
		// 福建省
		tmpCity = new Hashtable<String, String[]>();
		tmpCity.put("福州市", new String[] { "仓山区", "长乐市", "福清市", "鼓楼区", "晋安区", "连江县", "马尾区", "闽侯县",
				"台江区" });
		tmpCity.put("龙岩市", new String[] { "新罗区" });
		tmpCity.put("南平市", new String[] { "光泽县", "建瓯市", "建阳市", "邵武市", "顺昌县", "松溪县", "武夷山市", "延平区",
				"政和县" });
		tmpCity.put("宁德市", new String[] { "福安市", "福鼎市", "蕉城区", "屏南县", "寿宁县", "柘荣县", "周宁县" });
		tmpCity.put("莆田市", new String[] { "城厢区", "涵江区", "荔城区" });
		tmpCity.put("泉州市", new String[] { "安溪县", "德化县", "丰泽区", "惠安县", "晋江市", "鲤城区", "洛江区", "南安市",
				"石狮市", "永春县" });
		tmpCity.put("三明市", new String[] { "梅列区", "三元区" });
		tmpCity.put("厦门市", new String[] { "海沧区", "湖里区", "集美区", "思明区", "同安区", "翔安区" });
		tmpCity.put("漳州市", new String[] { "龙文区", "芗城区" });
		tmpProvince.put("福建省", tmpCity);
		// 甘肃省
		tmpCity = new Hashtable<String, String[]>();
		tmpCity.put("白银市", new String[] { "白银区" });
		tmpCity.put("嘉峪关市", new String[] {});
		tmpCity.put("酒泉市", new String[] { "敦煌市肃州区" });
		tmpCity.put("兰州市", new String[] { "安宁区", "城关区", "七里河区", "西固区" });
		tmpCity.put("平凉市", new String[] { "崆峒区" });
		tmpCity.put("庆阳市", new String[] { "庆城县", "西峰区" });
		tmpCity.put("天水市", new String[] { "麦积区", "秦州区" });
		tmpCity.put("武威市", new String[] { "凉州区" });
		tmpCity.put("张掖市", new String[] { "甘州区" });
		tmpCity.put("定西市", new String[] { "安定区" });
		tmpCity.put("陇南市", new String[] { "武都区" });
		tmpProvince.put("甘肃省", tmpCity);
		// 广东省
		tmpCity = new Hashtable<String, String[]>();
		tmpCity.put("东莞市", new String[] { "东莞市" });
		tmpCity.put("佛山市", new String[] { "禅城区", "高明区", "南海区", "顺德区三水区" });
		tmpCity.put("广州市", new String[] { "白云区", "从化市", "番禺区", "海珠区", "花都区", "黄浦区", "南沙区", "萝岗区",
				"荔湾区", "天河区", "越秀区", "增城市" });
		tmpCity.put("河源市", new String[] { "东源县", "龙川县", "源城区" });
		tmpCity.put("惠州市", new String[] { "博罗县", "惠城区", "惠东县", "惠阳区" });
		tmpCity.put("江门市", new String[] { "江海区", "蓬江区", "新会区" });
		tmpCity.put("茂名市", new String[] { "茂港区", "茂南区" });
		tmpCity.put("梅州市", new String[] { "梅州市市区" });
		tmpCity.put("清远市", new String[] { "连州市", "清新县", "英德市清城区" });
		tmpCity.put("汕头市", new String[] { "潮南区", "潮阳区", "澄海区", "全平区", "龙湖区" });
		tmpCity.put("汕尾市", new String[] { "城区", "海丰区", "陆丰区", "陆河县" });
		tmpCity.put("韶关市", new String[] { "浈江区", "乐昌市", "南雄市", "仁化县", "乳源瑶族自治县", "始兴县", "武江区",
				"浈江区曲江区	" });
		tmpCity.put("深圳市", new String[] { "宝安区", "福田区", "龙岗区", "罗湖区", "南山区", "盐田区" });
		tmpCity.put("阳江市", new String[] { "江城区", "阳春市", "阳东县", "阳西县" });
		tmpCity.put("云浮市", new String[] { "云城区" });
		tmpCity.put("湛江市", new String[] { "赤坎区", "麻章区", "坡头区", "霞山区" });
		tmpCity.put("肇庆市", new String[] { "鼎湖区", "端州区" });
		tmpCity.put("中山市", new String[] { "中山市" });
		tmpCity.put("珠海市", new String[] { "斗门区", "金湾区", "香洲区" });
		tmpProvince.put("广东省", tmpCity);
		// 广西壮族自治区
		tmpCity = new Hashtable<String, String[]>();
		tmpCity.put("百色市", new String[] { "凌云县", "平果县", "田东县", "田阳县", "右江区", "靖西县" });
		tmpCity.put("北海市", new String[] { "海城区", "合浦县", "银海区" });
		tmpCity.put("崇左市", new String[] { "江洲区", "龙州县", "宁明县", "凭祥市" });
		tmpCity.put("防城港市", new String[] { "防城区", "港口区" });
		tmpCity.put("贵港市", new String[] { "港北区", "港南区", "覃塘区", "桂平市" });
		tmpCity.put("桂林市", new String[] { "叠彩区", "七星区", "象山区", "秀峰区", "雁山区" });
		tmpCity.put("河池市", new String[] { "金城江区", "宜州区" });
		tmpCity.put("贺州市", new String[] { "八步区钟山县" });
		tmpCity.put("来宾市", new String[] { "兴宾区", "忻城县" });
		tmpCity.put("柳州市", new String[] { "城中区", "柳北区", "柳南区", "鱼峰区柳江县 融安县" });
		tmpCity.put("南宁市", new String[] { "西乡塘区", "江南区", "青秀区", "兴宁区", "良庆区 武鸣县", "横县", "宾阳县" });
		tmpCity.put("钦州市", new String[] { "钦北区", "钦南区" });
		tmpCity.put("梧州市", new String[] { "苍梧县", "长洲区", "蝶山区", "万秀区", "岑溪市" });
		tmpCity.put("玉林市", new String[] { "玉州区", "兴业县", "博白县" });
		tmpProvince.put("广西壮族自治区", tmpCity);
		// 贵州省
		tmpCity = new Hashtable<String, String[]>();
		tmpCity.put("安顺市", new String[] { "西秀区" });
		tmpCity.put("毕节市", new String[] { "毕节地区" });
		tmpCity.put("贵阳市", new String[] { "白云区", "花溪区", "南明区", "乌当区", "小河区", "云岩区" });
		tmpCity.put("六盘水市", new String[] { "水城县", "中山区" });
		tmpCity.put("黔东南苗族侗族自治州", new String[] { "凯里市" });
		tmpCity.put("黔南布依族苗族自治州", new String[] { "都匀市" });
		tmpCity.put("黔西南布依族苗族自治州", new String[] { "兴义市" });
		tmpCity.put("铜仁市", new String[] { "铜仁地区" });
		tmpCity.put("遵义市", new String[] { "红花岗区", "汇川区", "仁怀市" });
		tmpProvince.put("贵州省", tmpCity);
		// 海南省
		tmpCity = new Hashtable<String, String[]>();
		tmpCity.put("海口市", new String[] { "龙华区", "美兰区", "琼山区", "秀英区" });
		tmpCity.put("三亚市", new String[] { "三亚市" });
		tmpProvince.put("海南省", tmpCity);
		// 河北省
		tmpCity = new Hashtable<String, String[]>();
		tmpCity.put("保定市", new String[] { "北市区", "定兴县", "定州市", "高碑店市", "南市区", "清苑县", "新市区", "涿州市" });
		tmpCity.put("沧州市", new String[] { "沧县", "黄骅市", "青县", "新华区", "运河区" });
		tmpCity.put("承德市", new String[] { "双桥区", "鹰手营子矿区", "承德市" });
		tmpCity.put("邯郸市", new String[] { "丛台区", "复兴区", "邯郸县", "邯山区" });
		tmpCity.put("衡水市", new String[] { "桃城区" });
		tmpCity.put("廊坊市", new String[] { "安次区", "霸州市", "固安市", "广阳区", "三河市", "香河县" });
		tmpCity.put("秦皇岛市", new String[] { "北戴河区", "昌黎县", "抚宁县", "海港区" });
		tmpCity.put("石家庄市", new String[] { "长安区", "藁城市", "鹿泉市", "桥东市", "桥西区", "新华区", "裕华区", "正定县" });
		tmpCity.put("唐山市", new String[] { "丰南区", "丰润区", "古治区", "开平区", "路北区", "路南区", "滦县", "唐海县" });
		tmpCity.put("邢台市", new String[] { "桥东区", "桥西区" });
		tmpCity.put("张家口市", new String[] { "桥东区", "桥西区", "下花园区", "宣化区", "宣化县" });
		tmpProvince.put("河北省", tmpCity);
		// 河南省
		tmpCity = new Hashtable<String, String[]>();
		tmpCity.put("安阳市", new String[] { "北关区", "龙安区", "文峰区", "殷都区" });
		tmpCity.put("河南省直辖", new String[] { "济源市" });
		tmpCity.put("鹤壁市", new String[] { "鹤山区", "淇滨区", "山城区" });
		tmpCity.put("焦作市", new String[] { "解放区", "马村区", "孟州区", "山阳区", "中站区" });
		tmpCity.put("开封市", new String[] { "鼓楼区", "金明区", "龙亭区", "顺河回族区", "禹王台区" });
		tmpCity.put("洛阳市", new String[] { "瀍河回族区", "吉利区", "涧西区", "老城区", "洛龙区", "西工区" });
		tmpCity.put("漯河市", new String[] { "郾城区", "源汇区", "召陵区" });
		tmpCity.put("南阳市", new String[] { "邓州市", "宛城区", "卧龙区" });
		tmpCity.put("平顶山市", new String[] { "卫东区", "新华区", "湛河区" });
		tmpCity.put("濮阳市", new String[] { "华龙区" });
		tmpCity.put("三门峡市", new String[] { "三门峡市" });
		tmpCity.put("商丘市", new String[] { "梁园区", "睢阳区", "永城市" });
		tmpCity.put("新乡市", new String[] { "红旗区", "辉县市", "牧野区", "卫滨区", "卫辉市" });
		tmpCity.put("信阳市", new String[] { "固始县", "平桥区", "浉河区" });
		tmpCity.put("许昌市", new String[] { "长葛市", "魏都区", "许昌县" });
		tmpCity.put("郑州市", new String[] { "二七区", "管城回族区", "金水区", "中原区", "惠济区" });
		tmpCity.put("周口市", new String[] { "川汇区" });
		tmpCity.put("驻马店市", new String[] { "驿城区" });
		tmpProvince.put("河南省", tmpCity);
		// 黑龙江省
		tmpCity = new Hashtable<String, String[]>();
		tmpCity.put("大庆市", new String[] { "龙凤区", "让胡路区", "萨尔图区" });
		tmpCity.put("哈尔滨市", new String[] { "道里区", "道外区", "南岗区", "平房区", "香坊区", "松北区" });
		tmpCity.put("黑河市", new String[] { "爱辉区" });
		tmpCity.put("鸡西市", new String[] { "鸡冠区" });
		tmpCity.put("佳木斯市", new String[] { "东风区", "郊区", "前进区", "向阳区s" });
		tmpCity.put("牡丹江市", new String[] { "爱民区", "东安区", "西安区", "阳明区" });
		tmpCity.put("齐齐哈尔市", new String[] { "建华区", "龙沙区", "梅里斯达斡尔族区", "铁锋区" });
		tmpCity.put("双鸭山市", new String[] { "宝山区", "岭东区", "四方台区", "尖山区" });
		tmpCity.put("绥化市", new String[] { "北林区" });
		tmpCity.put("伊春市", new String[] { "伊春区" });
		tmpCity.put("满洲里", new String[] {});
		tmpCity.put("鹤岗市", new String[] { "工农区", "南山区", "向阳区" });
		tmpCity.put("七台河市", new String[] { "桃山区" });
		tmpProvince.put("黑龙江省", tmpCity);
		// 湖北省
		tmpCity = new Hashtable<String, String[]>();
		tmpCity.put("鄂州市", new String[] { "鄂城区", "华容区", "梁子湖区" });
		tmpCity.put("恩施土家族苗族自治州", new String[] { "恩施市" });
		tmpCity.put("黄冈市", new String[] { "黄州区" });
		tmpCity.put("黄石市", new String[] { "黄石港区", "铁山区", "下陆区 西塞山区" });
		tmpCity.put("荆门市", new String[] { "东宝区", "掇刀区" });
		tmpCity.put("荆州市", new String[] { "洪湖市", "荆州区", "沙市区", "石首市", "松滋市" });
		tmpCity.put("十堰市", new String[] { "茅箭区", "张湾区" });
		tmpCity.put("随州市", new String[] { "曾都区" });
		tmpCity.put("天门市", new String[] {});
		tmpCity.put("武汉市", new String[] { "东西湖区", "汉阳区", "洪山区", "江岸区", "江汉区", "江夏区", "硚口区", "青山区",
				"武昌区", "新洲区" });
		tmpCity.put("咸宁市", new String[] { "咸安区" });
		tmpCity.put("襄樊市", new String[] { "樊城区", "襄城区", "襄阳区", "二气开发区" });
		tmpCity.put("孝感市", new String[] { "孝南区" });
		tmpCity.put("宜昌市", new String[] { "伍家岗区", "西陵区" });
		tmpProvince.put("湖北省", tmpCity);
		// 湖南省
		tmpCity = new Hashtable<String, String[]>();
		tmpCity.put("长沙市", new String[] { "长沙县", "芙蓉区", "开福区", "天心区", "雨花区", "岳麓区" });
		tmpCity.put("常德市", new String[] { "鼎城区", "武陵区" });
		tmpCity.put("郴州市", new String[] { "北湖区", "苏仙区" });
		tmpCity.put("衡阳市", new String[] { "石鼓区", "雁峰区", "蒸湘区", "珠晖区" });
		tmpCity.put("怀化市", new String[] { "鹤城区" });
		tmpCity.put("娄底市", new String[] { "娄星区" });
		tmpCity.put("邵阳市", new String[] { "北塔区", "大祥区", "双清区" });
		tmpCity.put("湘潭市", new String[] { "雨湖区", "岳塘区" });
		tmpCity.put("益阳市", new String[] { "赫山区", "资阳区" });
		tmpCity.put("岳阳市", new String[] { "君山区", "岳阳楼区", "云溪区" });
		tmpCity.put("张家界市", new String[] { "武陵源区", "永定区" });
		tmpCity.put("株洲市", new String[] { "荷塘区", "芦淞区", "石峰区", "天元区" });
		tmpCity.put("永州市", new String[] { "冷水滩区" });
		tmpCity.put("湘西土家族苗族自治州", new String[] { "吉首市" });
		tmpProvince.put("湖南省", tmpCity);
		// // 吉林省
		tmpCity = new Hashtable<String, String[]>();
		tmpCity.put("白城市", new String[] { "洮北区" });
		tmpCity.put("白山市", new String[] { "八道江区" });
		tmpCity.put("长春市", new String[] { "朝阳区", "二道区", "宽城区", "绿园区", "南关区" });
		tmpCity.put("吉林市", new String[] { "昌邑区", "船营区", "丰满区", "龙潭区" });
		tmpCity.put("辽源市", new String[] { "龙山区", "西安区" });
		tmpCity.put("四平市", new String[] { "铁东区", "铁西区" });
		tmpCity.put("松原市", new String[] { "宁江区", "前郭尔罗斯蒙古族自治县" });
		tmpCity.put("通化市", new String[] { "东昌区", "二道江区", "梅河口市" });
		tmpCity.put("延边朝鲜族自治州", new String[] { "珲春市", "延吉市" });
		tmpProvince.put("吉林省", tmpCity);
		// 江苏省
		tmpCity = new Hashtable<String, String[]>();
		tmpCity.put("常州市", new String[] { "金坛市", "溧阳市", "戚墅堰区", "天宁区", "武进区", "新北区", "钟楼区" });
		tmpCity.put("淮安市", new String[] { "楚州区", "淮阴区", "金湖县", "涟水县", "清河区", "青浦区", "盱眙县" });
		tmpCity.put("连云港市", new String[] { "东海县", "赣榆县", "灌南县", "灌云县", "海州区", "连云区", "新浦区" });
		tmpCity.put("南京市", new String[] { "白下区", "鼓楼区", "建邺区", "江宁区", "六合区", "浦口区", "栖霞区", "秦淮区",
				"玄武区", "雨花台区", "下关区" });
		tmpCity.put("南通市", new String[] { "崇川区", "港闸区", "海安县", "海门市", "启东市", "如东县", "如皋市", "通州市" });
		tmpCity.put("苏州市", new String[] { "沧浪区", "常熟市", "虎丘区", "金阊区", "昆山市", "平江区", "太仓市", "吴江市",
				"吴中区", "相城区", "张家港市" });
		tmpCity.put("宿迁市", new String[] { "宿城区", "宿豫区" });
		tmpCity.put("泰州市", new String[] { "海陵区", "姜堰市", "兴化市", "高港区", "靖江市" });
		tmpCity.put("无锡市", new String[] { "北塘区", "滨湖区", "崇安区", "惠山区", "江阴市", "南长区", "锡山区", "宜兴区" });
		tmpCity.put("徐州市", new String[] { "丰县", "鼓楼区", "九里区", "邳州市", "泉山区", "新沂市", "云龙区", "铜山县" });
		tmpCity.put("盐城市", new String[] { "滨海县", "大丰市", "东台市", "建湖县", "亭湖区", "盐都县", "盐都新区" });
		tmpCity.put("扬州市", new String[] { "宝应县", "高邮市", "广陵区", "邗江区", "江都市", "维扬区", "仪征区" });
		tmpCity.put("镇江市", new String[] { "丹徒区", "丹阳市", "京口区", "句容市", "润州区", "扬中市" });
		tmpProvince.put("甘肃省", tmpCity);
		// 江西省
		tmpCity = new Hashtable<String, String[]>();
		tmpCity.put("抚州市", new String[] { "临川区" });
		tmpCity.put("赣州市", new String[] { "章贡区" });
		tmpCity.put("景德镇市", new String[] { "昌江区", "珠山区", "乐平市", "昌江区" });
		tmpCity.put("九江市", new String[] { "庐山区", "浔阳区" });
		tmpCity.put("南昌市", new String[] { "东湖区", "南昌县", "青山湖区", "青云谱区", "湾里区", "西湖区", "新建县" });
		tmpCity.put("萍乡市", new String[] { "安源区", "湘东区" });
		tmpCity.put("上饶市", new String[] { "信州区" });
		tmpCity.put("新余市", new String[] { "渝水区" });
		tmpCity.put("宜春市", new String[] { "袁州区" });
		tmpCity.put("鹰潭市", new String[] { "月湖区" });
		tmpCity.put("吉安市", new String[] { "吉州区", "青原区" });
		tmpProvince.put("江西省", tmpCity);
		// 辽宁省
		tmpCity = new Hashtable<String, String[]>();
		tmpCity.put("鞍山市", new String[] { "海城市", "立山区", "千山区", "台安县", "铁东区", "铁西区", "岫岩满族自治县" });
		tmpCity.put("本溪市", new String[] { "本溪满族自治县", "桓仁满族自治县", "明山区", "平山区", "溪湖区" });
		tmpCity.put("朝阳市", new String[] { "北票市", "朝阳县", "建平县", "喀喇沁左翼蒙古族自治县", "凌源市", "龙城区", "双塔区" });
		tmpCity.put("大连市", new String[] { "甘井子区", "金州区", "旅顺口区", "普兰店市", "沙河口区", "瓦房店市", "西岗区",
				"中山区", "庄河市" });
		tmpCity.put("丹东市", new String[] { "东港市", "凤城市", "宽甸满族自治县", "元宝区", "振安区", "振兴区" });
		tmpCity.put("抚顺市", new String[] { "抚顺县", "顺城区", "望花区", "新抚区" });
		tmpCity.put("阜新市", new String[] { "阜新蒙古族自治县", "海州区", "清河门区", "太平区", "细河区", "新邱区", "彰武区" });
		tmpCity.put("葫芦岛市", new String[] { "建昌县", "连山区", "龙港区", "绥中县", "兴城市" });
		tmpCity.put("锦州市", new String[] { "古塔区", "黑山县", "凌海市", "凌河区", "太和区", "义县	" });
		tmpCity.put("辽阳市", new String[] { "白塔区", "灯塔市", "弓长岭区", "宏伟区", "辽阳县", "太子河区", "文圣区" });
		tmpCity.put("盘锦市", new String[] { "大洼县", "盘山县", "双台子区", "兴隆台区" });
		tmpCity.put("沈阳市", new String[] { "大东区", "东陵区", "法库县", "和平区", "皇姑区", "康平县", "辽中县", "沈河区",
				"苏家屯区", "铁西区", "沈北新区", "新民市", "于洪区", "浑南新区" });
		tmpCity.put("铁岭市", new String[] { "昌图县", "调兵山市", "开原市", "清河区", "铁岭县", "银州区" });
		tmpCity.put("营口市", new String[] { "鲅鱼圈区", "大石桥市", "盖州市", "西市区", "站前区", "老边区" });
		tmpProvince.put("辽宁省", tmpCity);
		// 内蒙古自治区
		tmpCity = new Hashtable<String, String[]>();
		tmpCity.put("巴彦淖尔市", new String[] { "临河区" });
		tmpCity.put("包头市", new String[] { "东河区", "昆都仑区", "青山区", "九原区" });
		tmpCity.put("赤峰市", new String[] { "红山区", "松山区" });
		tmpCity.put("鄂尔多斯市", new String[] { "东胜区" });
		tmpCity.put("呼和浩特市", new String[] { "回民区", "新城区", "玉泉区", "赛罕区" });
		tmpCity.put("呼伦贝尔市", new String[] { "海拉尔区" });
		tmpCity.put("通辽市", new String[] { "科尔沁区" });
		tmpCity.put("乌海市", new String[] { "海勃湾区", "海南市", "乌达区" });
		tmpCity.put("乌兰察布市", new String[] { "集宁区" });
		tmpCity.put("锡林郭勒盟", new String[] { "锡林浩特市" });
		tmpCity.put("兴安盟", new String[] { "乌兰浩特市" });
		tmpCity.put("", new String[] {});
		tmpProvince.put("内蒙古自治区", tmpCity);
		// 宁夏回族自治区
		tmpCity = new Hashtable<String, String[]>();
		tmpCity.put("固原市", new String[] { "原州区" });
		tmpCity.put("石嘴山市", new String[] { "大武口区", "惠农区" });
		tmpCity.put("吴忠市", new String[] { "利通区" });
		tmpCity.put("银川市", new String[] { "金凤区", "西夏区", "兴庆区" });
		tmpCity.put("中卫市", new String[] { "沙坡头区" });
		tmpProvince.put("宁夏回族自治区", tmpCity);
		// 青海省
		tmpCity = new Hashtable<String, String[]>();
		tmpCity.put("海西蒙古族藏族自治州", new String[] { "格尔木市" });
		tmpCity.put("西宁市", new String[] { "城北区", "城东区", "城西区", "城中区" });
		tmpProvince.put("青海省", tmpCity);
		// 山东省
		tmpCity = new Hashtable<String, String[]>();
		tmpCity.put("滨州市", new String[] { "滨城区", "博兴县", "惠民县", "无棣县", "邹平县" });
		tmpCity.put("德州市", new String[] { "德城区", "齐河县", "禹城市" });
		tmpCity.put("东营市", new String[] { "东营区", "广饶县" });
		tmpCity.put("菏泽市", new String[] { "曹县", "单县", "巨野县", "牡丹区", "郓城县" });
		tmpCity.put("济南市", new String[] { "长清区", "槐荫区", "历城区", "历下区", "平阴县", "市中区", "天桥区", "章丘市" });
		tmpCity.put("济宁市", new String[] { "金乡县", "梁山县", "曲阜市", "市中区", "泗水县", "任城区" });
		tmpCity.put("莱芜市", new String[] { "钢城区", "莱城区" });
		tmpCity.put("聊城市", new String[] { "东阿县", "东昌府区", "临清市", "阳谷县" });
		tmpCity.put("临沂市", new String[] { "河东区", "兰山区", "罗庄区", "沂水县" });
		tmpCity.put("青岛市", new String[] { "城阳区", "黄岛区", "即墨市", "胶南市", "胶州市", "莱西市", "崂山区", "李沧区",
				"平度市", "市北区", "市南区", "四方区" });
		tmpCity.put("日照市", new String[] { "东港区", "莒县", "五莲县" });
		tmpCity.put("泰安市", new String[] { "岱岳区", "泰山区", "新泰市" });
		tmpCity.put("威海市", new String[] { "环翠区", "乳山市", "文登市" });
		tmpCity.put("潍坊市", new String[] { "昌乐县", "昌邑市", "坊子区", "高密市", "寒亭区", "奎文区", "青州市", "潍城区",
				"寿光市", "诸城市" });
		tmpCity.put("烟台市", new String[] { "福山区", "海阳市", "莱山区", "莱阳市", "莱州市", "龙口市", "牟平区", "蓬莱市",
				"栖霞市", "招远市", "芝罘区", "开发区" });
		tmpCity.put("枣庄市", new String[] { "市中区", "滕州市", "山亭区", "薛城区", "峄城区" });
		tmpCity.put("淄博市", new String[] { "博山区", "临淄区", "沂源县", "张店区", "周村区", "淄川区" });
		tmpProvince.put("山东省", tmpCity);
		// 山西省
		tmpCity = new Hashtable<String, String[]>();
		tmpCity.put("长治市", new String[] { "城区" });
		tmpCity.put("大同市", new String[] { "城区", "矿区", "南郊区" });
		tmpCity.put("晋城市", new String[] { "城区", "泽州县" });
		tmpCity.put("晋中市", new String[] { "榆次区" });
		tmpCity.put("临汾市", new String[] { "侯马市", "尧都区" });
		tmpCity.put("吕梁市", new String[] { "离石区", "柳林县" });
		tmpCity.put("朔州市", new String[] { "朔州市" });
		tmpCity.put("太原市", new String[] { "古交市", "尖草坪区", "晋源区", "万柏林区", "小店区", "杏花岭区", "迎泽区" });
		tmpCity.put("忻州市", new String[] { "忻府区" });
		tmpCity.put("阳泉市", new String[] { "城区", "郊区", "矿区" });
		tmpCity.put("运城市", new String[] { "河津市", "盐湖区" });
		tmpProvince.put("山西省", tmpCity);
		// 陕西省
		tmpCity = new Hashtable<String, String[]>();
		tmpCity.put("安康市", new String[] { "汉滨区" });
		tmpCity.put("宝鸡市", new String[] { "金台区", "渭滨区" });
		tmpCity.put("汉中市", new String[] { "汉台区", "南郑县" });
		tmpCity.put("商洛市", new String[] { "商州区" });
		tmpCity.put("铜川市", new String[] { "王益区", "耀州区", "印台区" });
		tmpCity.put("渭南市", new String[] { "临渭区" });
		tmpCity.put("西安市", new String[] { "灞桥区", "碑林区", "长安区", "莲湖区", "临潼区", "未央区", "新城区", "阎良区",
				"雁塔区", "西高经济技术产业开发区" });
		tmpCity.put("咸阳市", new String[] { "秦都区", "渭城区", "兴平市", "杨陵区" });
		tmpCity.put("延安市", new String[] { "宝塔区" });
		tmpCity.put("榆林市", new String[] { "榆阳区" });
		tmpProvince.put("陕西省", tmpCity);
		// 上海市
		tmpCity = new Hashtable<String, String[]>();
		tmpCity.put("上海市", new String[] { "宝山区", "长宁区", "崇明县", "奉贤区", "虹口区", "黄浦区", "嘉定区", "金山区",
				"静安区", "卢湾区", "闵行区", "南汇区", "浦东新区", "普陀区", "青浦区", "松江区", "徐汇区", "杨浦区", "闸北区" });
		tmpProvince.put("上海市", tmpCity);
		// 四川省
		tmpCity = new Hashtable<String, String[]>();
		tmpCity.put("巴中市", new String[] { "巴州区" });
		tmpCity.put("成都市", new String[] { "成华区", "金牛区", "锦江区", "龙泉驿区", "青白江区", "青羊区", "双流县", "温江区",
				"武侯区", "新都区", "郫县" });
		tmpCity.put("达州市", new String[] { "通川区" });
		tmpCity.put("德阳市", new String[] { "广汉市", "旌阳区", "什邡市" });
		tmpCity.put("广元市", new String[] { "市中区", "利州区" });
		tmpCity.put("广安市", new String[] { "广安区" });
		tmpCity.put("乐山市", new String[] { "市中区" });
		tmpCity.put("凉山彝族自治州", new String[] { "西昌市" });
		tmpCity.put("泸州市", new String[] { "江阳区", "龙马潭区", "纳溪区" });
		tmpCity.put("眉山市", new String[] { "东坡区" });
		tmpCity.put("绵阳市", new String[] { "涪城区", "江油市", "盐亭县", "游仙区" });
		tmpCity.put("内江市", new String[] { "东兴区", "市中区" });
		tmpCity.put("南充市", new String[] { "高坪区", "嘉陵区", "顺庆区" });
		tmpCity.put("攀枝花市", new String[] { "东区" });
		tmpCity.put("遂宁市", new String[] { "船山区" });
		tmpCity.put("雅安市", new String[] { "雨城区" });
		tmpCity.put("宜宾市", new String[] { "翠屏区" });
		tmpCity.put("资阳市", new String[] { "雁江区" });
		tmpCity.put("自贡市", new String[] { "大安区", "贡井区", "自流井区" });
		tmpProvince.put("四川省", tmpCity);
		// 天津市
		tmpCity = new Hashtable<String, String[]>();
		tmpCity.put("天津市", new String[] { "宝坻区", "北辰区", "大港区", "东丽区", "汉沽区", "和平区", "河北区", "河东区",
				"河西区", "红桥区", "蓟县", "津南区", "静海县", "南开区", "宁河县", "塘沽区", "武清区", "西青区" });
		tmpProvince.put("天津市", tmpCity);
		// 西藏自治区
		tmpCity = new Hashtable<String, String[]>();
		tmpCity.put("拉萨市", new String[] { "城关区" });
		tmpCity.put("日喀则地区", new String[] { "日喀则市" });
		tmpCity.put("林芝地区", new String[] { "林芝县" });
		tmpProvince.put("西藏自治区", tmpCity);
		// 云南省
		tmpCity = new Hashtable<String, String[]>();
		tmpCity.put("保山市", new String[] { "隆阳区" });
		tmpCity.put("楚雄彝族自治州", new String[] { "楚雄市" });
		tmpCity.put("大理白族自治州", new String[] { "大理市" });
		tmpCity.put("德宏傣族景颇族自治州", new String[] { "潞西市", "瑞丽市" });
		tmpCity.put("迪庆藏族自治州", new String[] { "香格里拉县" });
		tmpCity.put("红河哈尼族彝族自治州", new String[] { "个旧市", "开远市", "蒙自县", "弥勒县" });
		tmpCity.put("昆明市", new String[] { "官渡区", "盘龙区", "五华区", "西山区" });
		tmpCity.put("丽江市", new String[] { "古城区" });
		tmpCity.put("临沧市", new String[] { "思茅区" });
		tmpCity.put("曲靖市", new String[] { "麒麟区", "宣威市" });
		tmpCity.put("文山壮族苗族自治州", new String[] { "文山县" });
		tmpCity.put("西双版纳傣族自治州", new String[] { "景洪市", "勐海县" });
		tmpCity.put("玉溪市", new String[] { "红塔区" });
		tmpCity.put("昭通市", new String[] { "昭阳区" });
		tmpCity.put("", new String[] {});
		tmpProvince.put("云南省", tmpCity);
		// 浙江省
		tmpCity = new Hashtable<String, String[]>();
		tmpCity.put("杭州市", new String[] { "滨江区，淳安县", "富阳市", "拱墅区", "建德市", "江干区", "临安市", "上城区",
				"桐庐县", "西湖区", "下城区", "萧山区", "余杭区" });
		tmpCity.put("湖州市", new String[] { "长兴县", "德清县", "南浔区", "吴兴区" });
		tmpCity.put("嘉兴市", new String[] { "海宁市", "海盐县", "平湖市", "桐乡市", "南湖区", "秀洲区" });
		tmpCity.put("金华市", new String[] { "东阳市", "金东区", "兰溪市", "浦江县", "武义县", "婺城区", "义乌市", "永康市" });
		tmpCity.put("丽水市", new String[] { "缙云县", "莲都区", "龙泉市", "青田县", "松阳县", "遂昌县", "云和县" });
		tmpCity.put("宁波市", new String[] { "北仑区", "慈溪市", "奉化市", "海曙区", "江北区", "江东区", "宁海县", "象山县",
				"鄞州区", "余姚市", "镇海区" });
		tmpCity.put("衢州市", new String[] { "常山县", "江山市", "开化县", "柯城区", "龙游县", "衢江区" });
		tmpCity.put("绍兴市", new String[] { "上虞市", "绍兴县", "嵊州市", "新昌县", "越城区", "诸暨市" });
		tmpCity.put("台州市", new String[] { "黄岩区", "椒江区", "临海市", "路桥区", "三门县", "天台县", "温岭市", "仙居县",
				"玉环县" });
		tmpCity.put("温州市", new String[] { "苍南县", "乐清市", "龙湾区", "鹿城区", "瓯海区", "平阳区", "瑞安市", "永嘉市" });
		tmpCity.put("舟山市", new String[] { "定海区", "普陀区" });
		tmpProvince.put("浙江省", tmpCity);
		// 新疆维吾尔自治区
		tmpCity = new Hashtable<String, String[]>();
		tmpCity.put("阿克苏地区", new String[] { "阿克苏市", "库车县" });
		tmpCity.put("阿勒泰地区", new String[] { "阿勒泰市" });
		tmpCity.put("巴音郭楞蒙古自治州", new String[] { "库尔勒市", "轮台县", "焉耆回族自治县" });
		tmpCity.put("博尔塔拉蒙古自治州", new String[] { "博乐市" });
		tmpCity.put("昌吉回族自治州", new String[] { "昌吉市" });
		tmpCity.put("哈密地区", new String[] { "哈密市" });
		tmpCity.put("喀什地区", new String[] { "喀什市" });
		tmpCity.put("克拉玛依市", new String[] { "克拉玛依区", "独子山区" });
		tmpCity.put("克孜勒苏柯尔克孜自治州", new String[] { "阿图什市" });
		tmpCity.put("石河子市", new String[] {});
		tmpCity.put("乌鲁木齐市", new String[] { "米泉\\石化", "沙依巴克区", "水磨沟区", "天山区", "新市区", "经济开发区" });
		tmpCity.put("伊犁哈萨克自治州", new String[] { "奎屯市", "伊宁市" });
		tmpCity.put("塔城地区", new String[] { "乌苏市" });
		tmpProvince.put("新疆维吾尔自治区", tmpCity);
		return tmpProvince;
	}

	/**
	 * @param table
	 *            地区总表。
	 * @param type
	 *            获取的地区类别。
	 * @param args
	 *            指定的地区参数。
	 * */
	public static String[] findAreaStringArr(Hashtable<String, Hashtable<String, String[]>> table,
			int type, String... args) {
		String[] arr = null;
		if (table == null) {
			return null;
		}
		if (type == TYPE_PROVINCE) {
			arr = getKeyArrayByTable(table);
		} else if (type == TYPE_CITY) {
			String province = null;
			for (String tmp : args) {
				province = tmp;
				if (province != null) {
					break;
				}
			}
			Hashtable<String, String[]> tmpCity = table.get(province);
			arr = getKeyArrayByTable(tmpCity);
		} else if (type == TYPE_REGION) {
			String province = null, city = null;
			for (String tmp : args) {
				if (province == null) {
					province = tmp;
				} else {
					city = tmp;
					break;
				}
			}
			Hashtable<String, String[]> tmpCity = table.get(province);
			arr = tmpCity.get(city);
		}
		return arr;
	}

	
	public static String[] getKeyArrayByTable(Hashtable<String, ? extends Object> table) {
		String[] arr = null;
		Set<String> set = table.keySet();
		String[] tmp = new String[set.size()];
		Iterator<String> iterator = set.iterator();
		int count = 0;
		while (iterator.hasNext()) {
			tmp[count++] = iterator.next().toString();
		}
		arr = tmp;
		return arr;
	}

	public static final List<String> arr2List(String[] arr) {
		List<String> list = null;
		if (arr != null) {
			list = new ArrayList<String>();
			for (int i = 0; i < arr.length; i++) {
				list.add(arr[i]);
			}
		}
		return list;
	}
}