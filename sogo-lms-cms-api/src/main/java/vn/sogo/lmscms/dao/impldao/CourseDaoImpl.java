package vn.sogo.lmscms.dao.impldao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import vn.sogo.lmscms.dao.interfacedao.ICourseDao;
import vn.sogo.lmscms.helpers.MySqlHelper;
import vn.sogo.lmscms.model.ActivityDetails;
import vn.sogo.lmscms.model.CUDReturnMessage;
import vn.sogo.lmscms.model.CanDoInfo;
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

public class CourseDaoImpl implements ICourseDao {
	@Autowired
	MySqlHelper mySqlHelper;
	
	@Override
    public List<CourseInfo> getAllCourse() throws Exception{
    	
		List<CourseInfo> result = new ArrayList<CourseInfo>();
    	
    	result = mySqlHelper.ExecuteStoreProc("web_cms_get_all_course", CourseInfo.class);

        return result;
    }
	
	@Override
	public List<CourseActivity> GetCourseActivity(Integer courseId) throws Exception{
		Object[] params = new Object[]{
				courseId
    	};
		
    	return mySqlHelper.ExecuteStoreProc("web_cms_get_course_activity", params, CourseActivity.class);
	}
	
	@Override
	public List<UnitInCourse> GetUnitInCourse(Integer courseId) throws Exception{
		Object[] params = new Object[]{
				courseId
    	};
		
    	return mySqlHelper.ExecuteStoreProc("web_cms_course_get_unit_in_course", params, UnitInCourse.class);
	}
	
	@Override
	public List<LessonInUnit> GetLessonInUnit(Integer unitId) throws Exception{	
		Object[] params = new Object[]{
				unitId
    	};
		return mySqlHelper.ExecuteStoreProc("web_cms_course_get_lesson_in_unit", params, LessonInUnit.class);
	}
	
	@Override
	public ActivityDetails GetActivityDetail(Integer activityId) throws Exception{
		Object[] params = new Object[]{
				activityId
    	};
		return mySqlHelper.ExecuteStoreProcSingleResult("web_cms_course_get_activity_by_id", params, ActivityDetails.class);
	}
	
	@Override
	public List<QuizQuestion> GetQuizQuestion(Integer activityId) throws Exception{
		Object[] params = new Object[]{
				activityId
    	};
		return mySqlHelper.ExecuteStoreProc("web_cms_course_get_activity_quiz_question", params, QuizQuestion.class);
	}
	
	@Override
	public List<QuestionAnswer> GetQuestionAnswer(Integer questionId) throws Exception{
		Object[] params = new Object[]{
				questionId
    	};
		return mySqlHelper.ExecuteStoreProc("web_cms_course_get_question_answer", params, QuestionAnswer.class);
	}

	@Override
	public List<CanDoInfo> GetCanDoInUnit(Integer unitId) throws Exception {
		Object[] params = new Object[]{
				unitId
    	};
		return mySqlHelper.ExecuteStoreProc("web_cms_get_unit_can_do", params, CanDoInfo.class);
	}

	@Override
	public List<CanDoInfo> GetCanDoInLesson(Integer lessonId) throws Exception {
		Object[] params = new Object[]{
				lessonId
    	};
		return mySqlHelper.ExecuteStoreProc("web_fe_get_lesson_can_do", params, CanDoInfo.class);
	}

	@Override
	public List<TrainerCourseInfo> GetTrainerInCourse(Integer courseId) throws Exception {
		Object[] params = new Object[]{
				courseId
    	};
		
    	return mySqlHelper.ExecuteStoreProc("web_cms_get_trainer_course", params, TrainerCourseInfo.class);
	}

	@Override
	public UnitInfo GetUnitInfoByUnitId(Integer unitId) throws Exception {
		Object[] params = new Object[]{
				unitId
    	};
		return mySqlHelper.ExecuteStoreProcSingleResult("web_cms_get_unit_info", params, UnitInfo.class);
	}

	@Override
	public LessonInfo GetLessonInfoByLessonId(Integer lessonId) throws Exception {
		
		Object[] params = new Object[]{
				lessonId
    	};
		return mySqlHelper.ExecuteStoreProcSingleResult("web_cms_get_lesson_info", params, LessonInfo.class);
	}
	@Override
	public List<ActivityDetails> GetActivityInLesson(Integer lessonId) throws Exception {
		Object[] params = new Object[]{
				lessonId
    	};
		
    	return mySqlHelper.ExecuteStoreProc("web_cms_get_lesson_activity", params, ActivityDetails.class);
	}

	@Override
	public List<UnitInfo> GetAllUnit(Integer courseId) throws Exception {
		Object[] params = new Object[]{
				courseId
    	};
		return mySqlHelper.ExecuteStoreProc("web_cms_get_unit_filter_course",params, UnitInfo.class);
	}

	@Override
	public CUDReturnMessage ExcuteTrainerCourse(ExcuteTrainerCourse model) throws Exception {
		Integer courseId=model.getCourseId();
		Integer trainerId=model.getTrainerId();
		String excuteType=model.getExcuteType();
		Object[] params = new Object[]{
				courseId,
				trainerId,
				excuteType
    	};
		return mySqlHelper.ExecuteStoreProcSingleResult("web_cms_add_new_trainer_course",params, CUDReturnMessage.class);
	}

	@Override
	public CUDReturnMessage ExcuteUnitCourse(ExcuteUnitCourse model) throws Exception {
		// TODO Auto-generated method stub
		Integer courseId=model.getCourseId();
		String unitTitle=model.getUnitTitle();
		String excuteType=model.getExcuteType();
		String unitDes="";
		String unitSumary="";
		Object[] params = new Object[]{
				courseId,
				unitTitle,
				excuteType,
				unitDes,
				unitSumary
    	};
		return mySqlHelper.ExecuteStoreProcSingleResult("web_cms_excute_unit_course",params, CUDReturnMessage.class);
	}

}
