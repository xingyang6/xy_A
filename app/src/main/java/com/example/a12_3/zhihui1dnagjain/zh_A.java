package com.example.a12_3.zhihui1dnagjain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.a12_3.R;

import java.util.ArrayList;
import java.util.List;

public class zh_A extends AppCompatActivity {
    public static int sp1;
    public static int sp2;
    public static int sp3;
    public static int yp1;
    public static List<Integer> news_1_img=new ArrayList<>();
    public static List<String> news_1_count=new ArrayList<>();
    public static List<String> news_1_title=new ArrayList<>();
    public static List<String> news_1_pingl_name=new ArrayList<>();
    public static List<String> news_1_pingl_data=new ArrayList<>();
    public static List<String> news_1_pingl_count=new ArrayList<>();
    public static List<String> news_1_pingl_name_1=new ArrayList<>();
    public static List<String> news_1_pingl_data_1=new ArrayList<>();
    public static List<String> news_1_pingl_count_1=new ArrayList<>();

     public static List<Integer> news_2_img=new ArrayList<>();
    public static List<String> news_2_count=new ArrayList<>();
    public static List<String> news_2_title=new ArrayList<>();
    public static List<String> news_2_pingl_name=new ArrayList<>();
    public static List<String> news_2_pingl_data=new ArrayList<>();
    public static List<String> news_2_pingl_count=new ArrayList<>();
    public static List<String> news_2_pingl_name_2=new ArrayList<>();
    public static List<String> news_2_pingl_data_2=new ArrayList<>();
    public static List<String> news_2_pingl_count_2=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zh_);
        initdata();
    }

    public static void initdata() {
        sp1=0;
        sp2=0;
        sp3=0;
        yp1=0;
        news_1_img.add(R.drawable.news_1);
        news_1_img.add(R.drawable.xdd);
        news_2_img.add(R.drawable.news_2_img);
        news_2_img.add(R.drawable.news_2_img_2);

        news_1_count.add("“我们想做点什么？我们还能再多做点什么？”这一强烈意愿表达了YC和创显两个党支部共同的心声。经过数次的探讨交流，大家理清了工作思路，策划出精彩脚本，决定以直播的形式赋能党组织开启新时代智慧党建新模式，打造党建智慧新阵地。\n" +
                " \n" +
                "印象中的党建活动常以线下座谈会或参观红色教育基地的形式进行，一次党建活动需要前期进行人员组织和活动方案规划，但线下的活动仅限于党员的时间安排和到场人员数量，随着线上活动的普及，同样的组织者（主播+助理），同样的活动规划（直播脚本），运用线上直播的形式也可以同时让身在各处的人们同时参加党建活动。\n" +
                " \n" +
                "直播前的活动规划小组 \n" +
                "5G时代“党建文化+直播”主题党日我们这样过\n" +
                "9月3日中午12时30分， YCx创显科教抗战胜利75周年纪念日主题党日活动“新时代云讲习所”在腾讯直播全网首发展示， 这是一场融合了“VR+MR直播+4D实景上云+社群运营+智慧党建+内容策划”的生动有趣的党建日活动，我们用技术展示并回顾历程，用实际行动自我勉励“吾辈应不忘初心，牢记使命”！\n" +
                " \n" +
                "从开播的200个人到26000人，在手机屏前以创新的体验重温了党员走过的路，感受到党赋予人民的力量！\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "如何把这些感人的故事让更多的党员群众熟悉并在社会传扬开来，如何突破线上的人员限制，“党建创意内容制作+文化直播传播”无疑是个好办法。它既能实时呈现党员们的所思所想所做，又能打破地域界限让更多的党员共同参与。\n" +
                " \n" +
                "“主题党日直播”真正实现了线上线下双向的交流互动，而且不受时空局限，能多人身临其境共同“参加”一次主题党日。\n");
        news_1_count.add("习近平总书记指出，互联网是我们面临的“最大变量”，如果党过不了互联网这一关，就过不了长期执政这一关。因此，新时代党建如何创新是每个组织都需要思考的问题。\n" +
                " \n" +
                "党建小程序 探索互联网时代党建新模式\n" +
                "“鄂市污染物在线监控党建云”小程序是以微信为载体，依托成熟的云端平台进行设计和搭建，形成的新媒体轻应用程序。此款小程序重点着力扩大基层党组织覆盖面，开创了新的党组织设置方式和组织活动方式，进一步提高党的领导水平和执政能力。\n" +
                "鄂市相关领导表示：“鄂市污染物在线监控党建云小程序，从理论规划到实际落地，始终坚持提升党的执政能力建设和先进性建设。自上线以来，以打造党内党内生活新模式、交流互动新平台、学习教育新课堂、管理服务新帮手、智慧党建新体系为宗旨。号召更多的党员同志们使用这个党员‘百宝箱’。此次小程序正式上线，感谢微盛的大力支持。接下来，希望我们会有进一步地深度合作。”\n" +
                "扫码体验小程序\n" +
                "近年来，智慧党建作为一种结合了移动互联网、数据分析、云计算、社交网络等手段的党建模式，已经成为提高党的执政能力，巩固党的执政基础的新平台、新模式、新形态。而党建小程序，成为智慧党建必备的基础应用。\n" +
                "加强基层党的建设，交流方式的平等化\n" +
                "“鄂市污染物在线监控党建云”小程序正式上线， 设立了党建的主体内容，三会一课信息动态，党组织各项学习内容，党组织活动，党建党史等。打破了党员与组织之间、党员与党员之间交流的时空界限，改变了以往小范围、集中式、层层传递的灌输式教育模式，能够让党的声音快速直达基层支部一线，上级党组织的精神能迅速贯彻到党员中，使党员能随时随地学习，履行接受教育的权利和义务。\n");
        news_2_count.add("智能化时代，互联网技术在各行各业生根发芽，影响着人们衣食住行的各个方面。各地党建工作也在积极融入“互联网 ”的潮流，开启“智慧”时代。\n" +
                "“党建手机app、党建微信公众号、可视化党建管理平台……这些智慧平台，不仅让基层党建更加活跃起来，还有效解决了很多居民关心的社区管理问题。”\n" +
                " \n" +
                "在第三届人民网内容科技大赛中，来自济宁市任城区的“党群e家信息化平台”项目，为解决党员管理难等基层党建老大难问题打开了有效破解新窗口，促进了党建工作提质增效。\n" +
                "智慧平台推动党建工作高效运行\n" +
                "今年1月16日，任城区安全生产和疫情防控工作视频会议召开,依托党群e家信息化平台的可视化板块，实现了区、镇、村三级同步培训学习。\n" +
                "据介绍，该区推出的党群e家信息化平台及“365”管理服务模式，通过搭建可视化党建管理平台、党建手机app平台、党建微信公众号平台、党建门户网站平台、党建新视线电视平台、党建大数据分析应用平台，各平台互为补充，推动党建工作的全域覆盖、线上线下相互融合促进。同时，通过构建“区—镇(街道)—村(社区)”三级基层党建工作体系和管理网络，让党建信息化有人来抓、有人来管、有人来干。\n" +
                " \n" +
                "党群e家信息化平台全纪实系统\n" +
                "通过信息化平台和区镇村三级管理服务，实现监督管理可视化、党群服务便捷化、党务工作智能化、学习交流互动化、组织活动纪实化，让党的建设和组织工作高效运行。\n" +
                "基层党建智慧化发展驶上“快车道”\n" +
                "提高党建工作数字化、智慧化水平，是各级党组织适应信息时代新形势和党员队伍新变化的重要任务。《中共中央关于加强党的政治建设的意见》中明确提出，要积极运用互联网、大数据等新兴技术，创新党组织活动内容方式，推进“智慧党建”。各地各部门也在积极运用新技术,探索党建工作数字化、智能化的新阵地，让基层党建智慧化发展驶上“快车道”。\n" +
                "在云南省丽江市古城区西安街道，“智慧党建”平台系统与“智慧西安”的结合，让基层党员和辖区居民通过“智慧西安”手机App或微信小程序实施反馈问题。\n" +
                "在广西贺州，智慧党建“一云三中心”平台，将信息技术融入党员教育管理全过程，并将扶贫、就业、大数据等部门资源整合进平台。\n" +
                "在吉林全域，“新时代e支部”专门为基层党支部量身打造交流平台，对党员学习、组织生活、业务工作等数据信息进行综合分析，实施“星级”管理，使基层支部党建工作有责任、有规矩、有温度、有活力、接地气。\n" +
                "实践不停歇，创新无止境。智慧科技与党建工作的深度融合，正在推动基层党建不断探索创新，为“智慧党建”开辟新的试验田。\n");
        news_2_count.add("为进一步加强党务共建与政企交流，深入创新党建工作,2020年12月21日，温州机场集团机关直属第一党支部、第二党支部与国航股份有限公司温州分公司机关党支部共同参加了由浙江省空港贵宾服务有限公司第四党支部牵头组织的赴正泰集团开展党务共建暨主题党日活动。\n" +
                "一行人先后参观了正泰集团创新体验中心，并观看了企业党建宣传片。在听取了正泰集团党建负责人对公司发展历程和智慧党建工作的介绍后，大家深刻感受到正泰集团在党建引领下求真务实的工作作风和开拓创新的企业文化。\n" +
                "活动结束，大家纷纷表示正泰集团党建工作的开展扎实有效，亮点纷呈，有许多值得学习之处。此次党务共建暨主题党日活动也让大家感受到满满的正能量，启发了下步工作思路。\n");
        news_1_title.add("技术赋能“党建文化+直播”智慧党建新模式");
        news_1_title.add("创新党建工作，小程序助力新时代智慧党建");
        news_2_title.add("科技激发新活力 “智慧党建”正青春");
        news_2_title.add("体验企业智慧党建，开展主题党日活动");

        news_1_pingl_name.add("text01");
        news_1_pingl_name.add("text02");
        news_1_pingl_name_1.add("text03");
        news_1_pingl_name_1.add("text04");
        news_2_pingl_name.add("用户1");
        news_2_pingl_name.add("用户2");
        news_2_pingl_name_2.add("用户3");
        news_2_pingl_name_2.add("用户4");

        news_1_pingl_data.add("2021-09-10");
        news_1_pingl_data.add("2021-08-10");
        news_2_pingl_data.add("2021-05-02");
        news_2_pingl_data.add("2021-03-20");
        news_1_pingl_data_1.add("2020-9-20");
        news_1_pingl_data_1.add("2020-10-22");
        news_2_pingl_data_2.add("2019-9-20");
        news_2_pingl_data_2.add("2019-10-22");

        news_1_pingl_count.add("支持");
        news_1_pingl_count.add("不错呀");
        news_2_pingl_count.add("66666");
        news_2_pingl_count.add("支持智慧党建");
        news_1_pingl_count_1.add("变化很快呀");
        news_1_pingl_count_1.add("再接再厉");
        news_2_pingl_count_2.add("很实用");
        news_2_pingl_count_2.add("方便多了");




    }
}