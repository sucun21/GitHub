package vn.sogo.lmscms.model;

import java.util.List;

public class QuizQuestion {
	private Integer questionId;
	private Integer questionTypeId;
	private String questionTitle;
	private String questionContent;
	private String rightAnswerIndex;
	private String questionHint;
	private List<QuestionAnswer> listAnswer;
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public Integer getQuestionTypeId() {
		return questionTypeId;
	}
	public void setQuestionTypeId(Integer questionTypeId) {
		this.questionTypeId = questionTypeId;
	}
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public String getQuestionContent() {
		return questionContent;
	}
	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}
	public String getRightAnswerIndex() {
		return rightAnswerIndex;
	}
	public void setRightAnswerIndex(String rightAnswerIndex) {
		this.rightAnswerIndex = rightAnswerIndex;
	}
	public String getQuestionHint() {
		return questionHint;
	}
	public void setQuestionHint(String questionHint) {
		this.questionHint = questionHint;
	}
	public List<QuestionAnswer> getListAnswer() {
		return listAnswer;
	}
	public void setListAnswer(List<QuestionAnswer> listAnswer) {
		this.listAnswer = listAnswer;
	}
}
