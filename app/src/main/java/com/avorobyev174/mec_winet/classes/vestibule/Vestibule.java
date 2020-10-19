package com.avorobyev174.mec_winet.classes.vestibule;

import com.avorobyev174.mec_winet.classes.floor.Floor;
import com.avorobyev174.mec_winet.classes.house.House;
import com.avorobyev174.mec_winet.classes.section.Section;

import java.io.Serializable;

public class Vestibule implements Serializable {
    private int number;
    private Floor floor;
    private Section section;
    private House house;

    public Floor getFloor() {
        return floor;
    }

    public int getNumber() {
        return number;
    }

    public String getFullNumber() {
        return "Тамбур " + getNumber();
    }

    public String getShortNumber() {
        return "Т" + getNumber();
    }

    public House getHouse() {
        return house;
    }

    public Section getSection() {
        return section;
    }

    public Vestibule(int number, House house, Section section, Floor floor) {
        this.number = number;
        this.house = house;
        this.section = section;
        this.floor = floor;
    }

}
