package com.avorobyev174.mec_winet.classes.floor;

import com.avorobyev174.mec_winet.classes.house.House;
import com.avorobyev174.mec_winet.classes.section.Section;

import java.io.Serializable;

public class Floor implements Serializable {
    private int number;
    private House house;

    public Section getSection() {
        return section;
    }

    private Section section;

    public int getNumber() {
        return number;
    }

    public String getFullNumber() {
        return "Этаж " + getNumber();
    }

    public String getShortNumber() {
        return "Э" + getNumber();
    }

    public House getHouse() {
        return house;
    }

    public Floor(int number, House house, Section section) {
        this.number = number;
        this.house = house;
        this.section = section;
    }

}
