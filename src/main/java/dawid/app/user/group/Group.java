package dawid.app.user.group;

import javax.persistence.*;

@Entity
@Table(name = "group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "group_id")
    private int groupID;

    @Column(name= "name")
    private String name;

    @Column(name= "common_hysical_activities")
    private String CommonPhysicalActivities;
    @Column(name= "common_city")
    private String commonCity;
    @Column(name= "common_free_time")
    private String commonFreeTime;

    public String getCommonPhysicalActivities() {
        return CommonPhysicalActivities;
    }

    public void setCommonPhysicalActivities(String commonPhysicalActivities) {
        CommonPhysicalActivities = commonPhysicalActivities;
    }

    public String getCommonCity() {
        return commonCity;
    }

    public void setCommonCity(String commonCity) {
        this.commonCity = commonCity;
    }

    public String getFreeTime() {
        return commonFreeTime;
    }

    public void setFreeTime(String freeTime) {
        this.commonFreeTime = freeTime;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
