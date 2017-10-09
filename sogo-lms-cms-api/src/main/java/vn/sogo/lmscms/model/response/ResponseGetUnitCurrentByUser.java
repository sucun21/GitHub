package vn.sogo.lmscms.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by VinhLe on 4/20/2017.
 */
public class ResponseGetUnitCurrentByUser implements Serializable/* extends BaseResponse*/ {

    private Integer courseTraineeId;
    private Integer traineeId;
    private Integer courseId;
    private Integer unitId;
    private String unitTitle;
    private byte[] unitDes;
    private byte[] unitSummary;

    public ResponseGetUnitCurrentByUser() {
    }

    /*public ResponseGetUnitCurrentByUser(boolean isSuccess, String message) {
        super(isSuccess, message);
    }*/

    public ResponseGetUnitCurrentByUser(Integer courseTraineeId, Integer traineeId, Integer courseId, Integer unitId, String unitTitle, byte[] unitDes, byte[] unitSummary) {
        /*super(Boolean.TRUE, DefaultSuccess);*/

        this.courseTraineeId = courseTraineeId;
        this.traineeId = traineeId;
        this.courseId = courseId;
        this.unitId = unitId;
        this.unitTitle = unitTitle;
        this.unitDes = unitDes;
        this.unitSummary = unitSummary;
    }

    @JsonProperty("course_trainee_id")
    public Integer getCourseTraineeId() {
        return courseTraineeId;
    }

    public void setCourseTraineeId(int courseTraineeId) {
        this.courseTraineeId = courseTraineeId;
    }

    @JsonProperty("trainee_id")
    public Integer getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(int traineeId) {
        this.traineeId = traineeId;
    }

    @JsonProperty("course_id")
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @JsonProperty("unit_id")
    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    @JsonProperty("unit_title")
    public String getUnitTitle() {
        return unitTitle;
    }

    public void setUnitTitle(String unitTitle) {
        this.unitTitle = unitTitle;
    }

    @JsonProperty("unit_des")
    public byte[] getUnitDes() {
        return unitDes;
    }

    public void setUnitDes(byte[] unitDes) {
        this.unitDes = unitDes;
    }

    @JsonProperty("unit_summary")
    public byte[] getUnitSummary() {
        return unitSummary;
    }

    public void setUnitSummary(byte[] unitSummary) {
        this.unitSummary = unitSummary;
    }
}
