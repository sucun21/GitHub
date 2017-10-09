package vn.sogo.lmscms.services.interfaceservice;

import java.util.List;

import vn.sogo.lmscms.model.ActivityDetails;
import vn.sogo.lmscms.model.CanDoInfo;
import vn.sogo.lmscms.model.CourseActivity;
import vn.sogo.lmscms.model.CourseInfo;
import vn.sogo.lmscms.model.LessonInUnit;
import vn.sogo.lmscms.model.LessonInfo;
import vn.sogo.lmscms.model.QuizQuestion;
import vn.sogo.lmscms.model.TrainerCourseInfo;
import vn.sogo.lmscms.model.UnitInCourse;
import vn.sogo.lmscms.model.UnitInfo;

/**
 * Created by VinhLe on 4/20/2017.
 */
public interface ICourseService {

	List<CourseInfo> GetAllCourse() throws Exception;
	
	List<CourseActivity> GetCourseActivity(Integer courseId) throws Exception;

	List<UnitInCourse> GetUnitInCourse(int courseId) throws Exception;
	
	List<CanDoInfo> GetCanDoInUnit(Integer unitId) throws Exception;
	
	List<CanDoInfo> GetCanDoInLesson(Integer lessionId) throws Exception;

	List<LessonInUnit> GetLessonInUnit(Integer unitId) throws Exception;

	ActivityDetails GetActivityDetail(Integer activityId) throws Exception;

	List<QuizQuestion> GetQuizQuestion(Integer activityId) throws Exception;
	
	List<TrainerCourseInfo> GetTrainerInCourse(Integer courseId) throws Exception;
	
	UnitInfo GetUnitInfoByUnitId(Integer unitId) throws Exception;
	
	LessonInfo GetLessonInfoByLessonId(Integer lessonId) throws Exception;
	
	List<ActivityDetails> GetActivityInLesson(Integer lessonId) throws Exception;
	
	List<UnitInfo> GetAllUnit(Integer courseId) throws Exception;

}
