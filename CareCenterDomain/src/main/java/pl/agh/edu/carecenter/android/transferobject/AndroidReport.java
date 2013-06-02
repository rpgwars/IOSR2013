package pl.agh.edu.carecenter.android.transferobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * User: piotrpaul
 * Date: 16.05.2013
 * Time: 00:51
 */
public class AndroidReport {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer activityId;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    private Integer carePlanId;

    public Integer getCarePlanId() {
        return carePlanId;
    }

    public void setCarePlanId(Integer carePlanId) {
        this.carePlanId = carePlanId;
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd,HH:00", timezone="CET")
    private Date dateOfReport;

    public Date getDateOfReport() {
        return dateOfReport;
    }

    public void setDateOfReport(Date dateOfReport) {
        this.dateOfReport = dateOfReport;
    }

    private String remarks;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    private Boolean isDone;
    @JsonProperty("isDone")
    public Boolean getDone() {
        return isDone;
    }
    @JsonProperty("isDone")
    public void setDone(Boolean done) {
        isDone = done;
    }
}
