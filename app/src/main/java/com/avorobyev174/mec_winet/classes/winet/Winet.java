package com.avorobyev174.mec_winet.classes.winet;

import android.text.Editable;

import com.avorobyev174.mec_winet.classes.floor.Floor;
import com.avorobyev174.mec_winet.classes.house.House;
import com.avorobyev174.mec_winet.classes.section.Section;
import com.avorobyev174.mec_winet.classes.vestibule.Vestibule;

import java.io.Serializable;
import java.util.UUID;

public class Winet implements Serializable {

    public void setSerNumber(Editable serNumber) {
        this.serNumber = serNumber.toString();
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setComment(Editable comment) {
        this.comment = comment.toString();
    }

    private String serNumber;
    private Floor floor;
    private Section section;
    private House house;
    private Vestibule vestibule;
    private String type;
    private String comment;

    public String getGuid() {
        return guid;
    }

    private String guid;

    public Floor getFloor() {
        return floor;
    }

    public String getSerNumber() {
        return serNumber;
    }

    public House getHouse() {
        return house;
    }

    public Section getSection() {
        return section;
    }

    public Vestibule getVestibule() {
        return vestibule;
    }

    public String getType() {
        return type;
    }

    public String getComment() {
        return comment;
    }


    public Winet(String serNumber, String type, House house, Section section, Floor floor, Vestibule vestibule) {
        this.serNumber = serNumber;
        this.type = type;
        this.house = house;
        this.section = section;
        this.floor = floor;
        this.vestibule = vestibule;
        this.guid = UUID.randomUUID().toString();
    }

}
