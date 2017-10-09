package vn.sogo.lmscms.services.implservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import vn.sogo.lmscms.dao.impldao.CourseDaoImpl;
import vn.sogo.lmscms.model.ActivityDetails;
import vn.sogo.lmscms.model.CanDoInfo;
import vn.sogo.lmscms.model.CanDoInfo;
import vn.sogo.lmscms.model.CourseActivity;
import vn.sogo.lmscms.model.CourseInfo;
import vn.sogo.lmscms.model.LessonInUnit;
import vn.sogo.lmscms.model.LessonInfo;
import vn.sogo.lmscms.model.QuizQuestion;
import vn.sogo.lmscms.model.TrainerCourseInfo;
import vn.sogo.lmscms.model.UnitInCourse;
import vn.sogo.lmscms.model.UnitInfo;
import vn.sogo.lmscms.services.interfaceservice.ICourseService;
/**
 * Created by VinhLe on 4/20/2017.
 */
public class CourseServiceImpl extends BaseService implements ICourseService {

    /*----------------------------------- Variable $CourseService ---------------------------------------------*/

//    private static final String DEFAULT_E_URL = "FE_COURSE_API";

    @Autowired
    CourseDaoImpl courseDao;

    /*----------------------------------- Method $CourseService ---------------------------------------------*/

    @Override
    public List<CourseInfo> GetAllCourse() throws Exception {    	
    	return courseDao.getAllCourse();
    }
    
    @Override
	public List<CourseActivity> GetCourseActivity(Integer courseId) throws Exception{
    	return courseDao.GetCourseActivity(courseId);
    }
    
    @Override
	public List<UnitInCourse> GetUnitInCourse(int courseId) throws Exception{
    	return courseDao.GetUnitInCourse(courseId);
    }
    
   
    @Override
	public List<LessonInUnit> GetLessonInUnit(Integer unitId) throws Exception{	
    	return courseDao.GetLessonInUnit(unitId);
    }
    
    
    @Override
	public ActivityDetails GetActivityDetail(Integer activityId) throws Exception{
    	return courseDao.GetActivityDetail(activityId);
    }
    
    @Override
	public List<QuizQuestion> GetQuizQuestion(Integer activityId) throws Exception{
    	
    	List<QuizQuestion> listQuestion = courseDao.GetQuizQuestion(activityId);
    	
    	for(QuizQuestion question: listQuestion){
			question.setListAnswer(courseDao.GetQuestionAnswer(question.getQuestionId()));
		}
    	
    	return listQuestion;
    }

	@Override
	public List<CanDoInfo> GetCanDoInUnit(Integer unitId) throws Exception {
		// TODO Auto-generated method stub
		return courseDao.GetCanDoInUnit(unitId);
	}

	@Override
	public List<CanDoInfo> GetCanDoInLesson(Integer lessonId) throws Exception {
		// TODO Auto-generated method stub
		return courseDao.GetCanDoInLesson(lessonId);
	}

	@Override
	public List<TrainerCourseInfo> GetTrainerInCourse(Integer courseId) throws Exception {
		// TODO Auto-generated method stub
		return courseDao.GetTrainerInCourse(courseId);
	}

	@Override
	public UnitInfo GetUnitInfoByUnitId(Integer unitId) throws Exception {
		// TODO Auto-generated method stub
		return courseDao.GetUnitInfoByUnitId(unitId);
	}

	@Override
	public LessonInfo GetLessonInfoByLessonId(Integer lessonId) throws Exception {
		// TODO Auto-generated method stub
		return courseDao.GetLessonInfoByLessonId(lessonId);
	}

	@Override
	public List<ActivityDetails> GetActivityInLesson(Integer lessonId) throws Exception {
		return courseDao.GetActivityInLesson(lessonId);
	}

	@Override
	public List<UnitInfo> GetAllUnit(Integer courseId) throws Exception {
		return courseDao.GetAllUnit(courseId);
	}
	
	
}
