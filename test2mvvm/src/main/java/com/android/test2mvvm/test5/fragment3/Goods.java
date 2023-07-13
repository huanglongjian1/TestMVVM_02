package com.android.test2mvvm.test5.fragment3;

public class Goods {
    String dianzan;
    String describe;
    boolean dz;

    public boolean isDz() {
        return dz;
    }

    public void setDz(boolean dz) {
        this.dz = dz;
    }

    public Goods(String dianzan, String describe) {
        this.dianzan = dianzan;
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "dianzan='" + dianzan + '\'' +
                ", describe='" + describe + '\'' +
                '}';
    }

    public String getDianzan() {
        return dianzan;
    }

    public void setDianzan(String dianzan) {
        this.dianzan = dianzan;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
