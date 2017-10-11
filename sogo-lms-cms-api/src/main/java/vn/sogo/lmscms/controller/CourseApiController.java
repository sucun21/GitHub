package vn.sogo.lmscms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import vn.sogo.lmscms.model.ActivityDetails;
import vn.sogo.lmscms.model.CUDReturnMessage;
import vn.sogo.lmscms.model.CanDoInfo;
import vn.sogo.lmscms.model.CourseActivity;
import vn.sogo.lmscms.model.CourseInfo;
import vn.sogo.lmscms.model.LessonInUnit;
import vn.sogo.lmscms.model.LessonInfo;
import vn.sogo.lmscms.model.QuizQuestion;
import vn.sogo.lmscms.model.TrainerCourseInfo;
import vn.sogo.lmscms.model.UnitInCourse;
import vn.sogo.lmscms.model.UnitInfo;
import vn.sogo.lmscms.model.request.ExcuteTrainerCourse;
import vn.sogo.lmscms.model.request.ExcuteUnitCourse;
import vn.sogo.lmscms.model.request.GetItemInCourse;
import vn.sogo.lmscms.model.request.GetItemInLesson;
import vn.sogo.lmscms.model.request.GetItemInUnit;
import vn.sogo.lmscms.model.response.ApiResponse;
import vn.sogo.lmscms.services.interfaceservice.ICourseService;
import vn.sogo.lmscms.settings.UrlEntity;

@RestController
@RequestMapping(UrlEntity.E_COURSE)
@CrossOrigin
public class CourseApiController extends BaseController {

    /*----------------------------------- Variable $CourseApiController ---------------------------------------------*/

    @Autowired
    ICourseService courseService;

    /*----------------------------------- Method $CourseApiController ---------------------------------------------*/

    @RequestMapping(value = UrlEntity.A_GET_ALL_COURSE, method = RequestMethod.POST)
    @CrossOrigin
    public @ResponseBody ApiResponse<List<CourseInfo>> GetAllCourse() throws Exception {
        return responseResult(courseService.GetAllCourse());
    }
    
    @RequestMapping(value = UrlEntity.A_GET_COURSE_ACTIVITY, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public @ResponseBody ApiResponse<List<CourseActivity>> GetCourseActivity(@RequestBody Integer courseId) throws Exception {
        return responseResult(courseService.GetCourseActivity(courseId));
    }
    
    @RequestMapping(value = UrlEntity.A_GET_UNIT_IN_COURSE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public @ResponseBody ApiResponse<List<UnitInCourse>> GetUnitInCourse(@RequestBody GetItemInCourse model) throws Exception {
    	Integer courseId=model.getCourseId();
        return responseResult(courseService.GetUnitInCourse(courseId));
    }
    
    @RequestMapping(value = UrlEntity.A_GET_TRAINER_IN_COURSE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public @ResponseBody ApiResponse<List<TrainerCourseInfo>> GetTrainerInCourse(@RequestBody GetItemInCourse model) throws Exception {
    	Integer courseId=model.getCourseId();
        return responseResult(courseService.GetTrainerInCourse(courseId));
    }
    
    @RequestMapping(value = UrlEntity.A_GET_LESSON_IN_UNIT, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public @ResponseBody ApiResponse<List<LessonInUnit>> GetLessonInUnit(@RequestBody GetItemInUnit model) throws Exception {
    	Integer unitId=model.getUnitId();
        return responseResult(courseService.GetLessonInUnit(unitId));
    }
     
    @RequestMapping(value = UrlEntity.A_GET_CAN_DO_IN_UNIT, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public @ResponseBody ApiResponse<List<CanDoInfo>> GetCanDoInUnit(@RequestBody GetItemInUnit model) throws Exception {
    	Integer unitId=model.getUnitId();
        return responseResult(courseService.GetCanDoInUnit(unitId));
    }
    @RequestMapping(value = UrlEntity.A_GET_UNIT_BY_UNIT_ID, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public @ResponseBody ApiResponse<UnitInfo> GetUnitInfoByUnitId(@RequestBody GetItemInUnit model) throws Exception {
    	Integer unitId=model.getUnitId();
        return responseResult(courseService.GetUnitInfoByUnitId(unitId));
    }
    @RequestMapping(value = UrlEntity.A_GET_LESSON_BY_LESSON_ID, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public @ResponseBody ApiResponse<LessonInfo> GetLessonInfoByLessonId(@RequestBody GetItemInLesson model) throws Exception {
    	Integer lessonId=model.getLessonId();
        return responseResult(courseService.GetLessonInfoByLessonId(lessonId));
    }
    
    @RequestMapping(value = UrlEntity.A_GET_CAN_DO_IN_LESSON, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public @ResponseBody ApiResponse<List<CanDoInfo>> GetCanDoInLesson(@RequestBody GetItemInLesson model) throws Exception {
    	Integer lessonId=model.getLessonId();
        return responseResult(courseService.GetCanDoInLesson(lessonId));
    }
    @RequestMapping(value = UrlEntity.A_GET_ACTIVITY_IN_LESSON, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public @ResponseBody ApiResponse<List<ActivityDetails>> GetActivityInLesson(@RequestBody GetItemInLesson model) throws Exception {
    	Integer lessonId=model.getLessonId();
        return responseResult(courseService.GetActivityInLesson(lessonId));
    }
    
    @RequestMapping(value = UrlEntity.A_GET_ACTIVITY_BY_ID, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public @ResponseBody ApiResponse<ActivityDetails> GetActivityDetail(@RequestBody Integer activityId) throws Exception {
        return responseResult(courseService.GetActivityDetail(activityId));
    }
    
    @RequestMapping(value = UrlEntity.A_GET_QUIZ_QUESTION, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public @ResponseBody ApiResponse<List<QuizQuestion>> GetQuizQuestion(@RequestBody Integer activityId) throws Exception {
        return responseResult(courseService.GetQuizQuestion(activityId));
    }
    
    @RequestMapping(value = UrlEntity.A_GET_UNIT_FILTER_COURSE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public @ResponseBody ApiResponse<List<UnitInfo>> GetAllUnit(@RequestBody GetItemInCourse model) throws Exception {
    	int courseId=model.getCourseId();
        return responseResult(courseService.GetAllUnit(courseId));
    }
    
    @RequestMapping(value = UrlEntity.A_ADD_NEW_TRAINER_COURSE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public @ResponseBody ApiResponse<CUDReturnMessage> ExcuteTrainerCourse(@RequestBody ExcuteTrainerCourse model) throws Exception {
        return responseResult(courseService.ExcuteTrainerCourse(model));
    }
    
    @RequestMapping(value = UrlEntity.A_ADD_NEW_UNIT_COURSE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public @ResponseBody ApiResponse<CUDReturnMessage> AddNewUnitCourse(@RequestBody ExcuteUnitCourse model) throws Exception {
        return responseResult(courseService.ExcuteUnitCourse(model));
    }
    
    
}
