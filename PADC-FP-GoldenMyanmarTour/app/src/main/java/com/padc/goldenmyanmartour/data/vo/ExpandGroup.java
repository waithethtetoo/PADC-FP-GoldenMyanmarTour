package com.padc.goldenmyanmartour.data.vo;

import java.util.ArrayList;


/**
 * Created by WT on 9/15/2016.
 */
public class ExpandGroup {
    private String name;
    private ArrayList<DestinationVO> destinationVOs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<DestinationVO> getDestinationVOs() {
        return destinationVOs;
    }

    public void setDestinationVOs(ArrayList<DestinationVO> destinationVOs) {
        this.destinationVOs = destinationVOs;
    }
}
