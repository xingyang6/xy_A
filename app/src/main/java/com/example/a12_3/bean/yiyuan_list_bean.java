package com.example.a12_3.bean;

public class yiyuan_list_bean {
    /**
     * id : 10
     * hospitalName : 大连市专科医院
     * brief : 大连市专科创建于 1952 年 6 月 1 日，经过几代儿医人的艰苦 奋斗，已成长为一所学科门类齐全、师资力量雄厚、医疗技术精湛、诊疗设备先 进的辽宁省规模最大的综合性儿童医院。担负着辽东半岛 18 岁以下儿童的医疗、 预防、康复、保健任务
     * level : 4
     * imgUrl : http://118.190.154.52:7777/profile/upload/image/2021 /05/08/e5d369ab-6f50-4f06-9b8d-515ddd15d887.jpg
     */

    private int id;
    private String hospitalName;
    private String brief;
    private int level;
    private String imgUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
