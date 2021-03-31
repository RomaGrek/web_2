package entity;


import java.util.ArrayList;
import java.util.List;

public class ListInformation {
    private List<Information> informationList;

    public ListInformation() {
        this(new ArrayList<>());
    }

    public ListInformation(List<Information> informationList) {
        this.informationList = informationList;
    }

    public List<Information> getListInformation() {
        return informationList;
    }

    public void setInformationList(List<Information> informationList) {
        this.informationList = informationList;
    }



}
