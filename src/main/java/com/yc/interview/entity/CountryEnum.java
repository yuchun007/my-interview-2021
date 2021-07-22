package com.yc.interview.entity;

public enum CountryEnum {
    /**
     * 各国编号，与名称
     */
    ONE(1,"齐"),
    TWO(2,"楚"),
    THREE(3,"燕"),
    FOUR(4,"赵"),
    FIVE(5,"魏"),
    SIX(6,"韩");

    private Integer recode;


    private String reName;

    CountryEnum(Integer recode, String reName) {
        this.recode = recode;
        this.reName = reName;
    }

    public Integer getRecode() {
        return recode;
    }

    public void setRecode(Integer recode) {
        this.recode = recode;
    }

    public String getReName() {
        return reName;
    }

    public void setReName(String reName) {
        this.reName = reName;
    }

    public static CountryEnum forEach_CountryEnum(Integer num){
        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum countryEnum : values){
            if (countryEnum.getRecode().equals(num)){
                return countryEnum;
            }
        }
        return null;
    }
}
