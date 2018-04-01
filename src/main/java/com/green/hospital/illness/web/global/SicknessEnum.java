package com.green.hospital.illness.web.global;

public enum SicknessEnum {
    SICK_COLD(1, "感冒"),
    SICK_AERATION(2, "痛风"),
    SICK_PNEUMONIA(3, "肺炎"),
    SICK_MEASLES(4, "麻疹"),
    SICK_HEADACHE(5, "头痛"),
    SICK_AMYGDALITIS(6, "扁桃体炎"),
    ;

    private Integer id;
    private String desc;

    public Integer getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    SicknessEnum(Integer id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public static SicknessEnum getEnum(Integer id){
        for(SicknessEnum se : SicknessEnum.values()){
            if(se.id == id){
                return se;
            }
        }
        return null;
    }
}
