package vn.sogo.lmscms.dao.interfacedao;

import java.util.List;

import vn.sogo.lmscms.model.ActivityDetails;
import vn.sogo.lmscms.model.CUDReturnMessage;
import vn.sogo.lmscms.model.CanDoInfo;
import vn.sogo.lmscms.model.CourseActivity;
import vn.sogo.lmscms.model.CourseInfo;
import vn.sogo.lmscms.model.LessonInUnit;
import vn.sogo.lmscms.model.LessonInfo;
import vn.sogo.lmscms.model.QuestionAnswer;
import vn.sogo.lmscms.model.QuizQuestion;
import vn.sogo.lmscms.model.TrainerCourseInfo;
import vn.sogo.lmscms.model.UnitInCourse;
import vn.sogo.lmscms.model.UnitInfo;
import vn.sogo.lmscms.model.request.ExcuteTrainerCourse;
import vn.sogo.lmscms.model.request.ExcuteUnitCourse;

public interface ICourseDao {
	List<CourseInfo> getAllCourse() throws Exception;
	List<CourseActivity> GetCourseActivity(Integer courseId) throws Exception;
	List<UnitInCourse> GetUnitInCourse(Integer courseId) throws Exception;
	List<LessonInUnit> GetLessonInUnit(Integer unitId) throws Exception;
	ActivityDetails GetActivityDetail(Integer activityId) throws Exception;
	List<QuizQuestion> GetQuizQuestion(Integer activityId) throws Exception;
	List<QuestionAnswer> GetQuestionAnswer(Integer questionId) throws Exception;
	List<CanDoInfo>   GetCanDoInUnit(Integer unitId ) throws Exception;
	List<CanDoInfo>   GetCanDoInLesson(Integer lessionId ) throws Exception;
	List<TrainerCourseInfo> GetTrainerInCourse(Integer courseId) throws Exception;
	UnitInfo GetUnitInfoByUnitId(Integer unitId)throws Exception;
	LessonInfo GetLessonInfoByLessonId(Integer lessonId)  throws Exception;
	List<ActivityDetails> GetActivityInLesson(Integer lessonId) throws Exception;
	List<UnitInfo> GetAllUnit(Integer CourseId) throws Exception;
	CUDReturnMessage ExcuteTrainerCourse(ExcuteTrainerCourse model)throws Exception;
	CUDReturnMessage ExcuteUnitCourse(ExcuteUnitCourse model)throws Exception;
}
