package vn.sogo.lmscms.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by VinhLe on 4/22/2017.
 */
public class ResponseGetQuestionByActivity implements Serializable {

    private Integer questionId;
    private Integer questionTypeId;
    private String questionTypeName;
    private Integer activityId;
    private String questionTitle;
    private String questionContent;
    private String rightAnswerIndex;
    private String questionHint;
    private ArrayList<ResponseGetQuestionAnswerByActivity> questionAnswers;
    private Timestamp createDate;

    public ResponseGetQuestionByActivity(Integer questionId, Integer questionTypeId, String questionTypeName, Integer activityId, String questionTitle, String questionContent, String rightAnswerIndex, String questionHint, Timestamp createDate) {
        this(questionId, questionTypeId, questionTypeName, activityId, questionTitle, questionContent, rightAnswerIndex, questionHint, new ArrayList<>(), createDate);
    }

    public ResponseGetQuestionByActivity(Integer questionId, Integer questionTypeId, String questionTypeName, Integer activityId, String questionTitle, String questionContent, String rightAnswerIndex, String questionHint, ArrayList<ResponseGetQuestionAnswerByActivity> questionAnswers, Timestamp createDate) {
        this.questionId = questionId;
        this.questionTypeId = questionTypeId;
        this.questionTypeName = questionTypeName;
        this.activityId = activityId;
        this.questionTitle = questionTitle;
        this.questionContent = questionContent;
        this.rightAnswerIndex = rightAnswerIndex;
        this.questionHint = questionHint;
        this.questionAnswers = questionAnswers;
        this.createDate = createDate;
    }

    @JsonProperty("question_id")
    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    @JsonProperty("question_type_id")
    public Integer getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(Integer questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    @JsonProperty("question_type_name")
    public String getQuestionTypeName() {
        return questionTypeName;
    }

    public void setQuestionTypeName(String questionTypeName) {
        this.questionTypeName = questionTypeName;
    }

    @JsonProperty("activity_id")
    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    @JsonProperty("question_title")
    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    @JsonProperty("question_content")
    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    @JsonProperty("right_answer_index")
    public String getRightAnswerIndex() {
        return rightAnswerIndex;
    }

    public void setRightAnswerIndex(String rightAnswerIndex) {
        this.rightAnswerIndex = rightAnswerIndex;
    }

    @JsonProperty("question_hint")
    public String getQuestionHint() {
        return questionHint;
    }

    public void setQuestionHint(String questionHint) {
        this.questionHint = questionHint;
    }

    @JsonProperty("question_answers")
    public ArrayList<ResponseGetQuestionAnswerByActivity> getQuestionAnswers() {
        return questionAnswers;
    }

    public void setQuestionAnswers(ArrayList<ResponseGetQuestionAnswerByActivity> questionAnswers) {
        this.questionAnswers = questionAnswers;
    }

    @JsonIgnore
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

/*----------------------------------- ChildClass $ResponseGetQuestionAnswerByActivity ---------------------------------------------*/

    public class ResponseGetQuestionAnswerByActivity implements Serializable {
        private Integer answerId;
        private Integer questionId;
        private String answerText;
        private Integer answerIndex;

        public ResponseGetQuestionAnswerByActivity(Integer answerId, Integer questionId, String answerText, Integer answerIndex) {
            this.answerId = answerId;
            this.questionId = questionId;
            this.answerText = answerText;
            this.answerIndex = answerIndex;
        }

        @JsonProperty("answer_id")
        public Integer getAnswerId() {
            return answerId;
        }

        public void setAnswerId(Integer answerId) {
            this.answerId = answerId;
        }

        @JsonProperty("question_id")
        public Integer getQuestionId() {
            return questionId;
        }

        public void setQuestionId(Integer questionId) {
            this.questionId = questionId;
        }

        @JsonProperty("answer_text")
        public String getAnswerText() {
            return answerText;
        }

        public void setAnswerText(String answerText) {
            this.answerText = answerText;
        }

        @JsonProperty("answer_index")
        public Integer getAnswerIndex() {
            return answerIndex;
        }

        public void setAnswerIndex(Integer answerIndex) {
            this.answerIndex = answerIndex;
        }
    }
}
