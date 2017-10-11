package vn.sogo.lmscms.settings;

/**
 * Created by VinhLe on 4/16/2017.
 */
public class RedisKeyEntity {

    /*----------------------------------- Setting $Redis ---------------------------------------------*/

    public static final int DEFAULT_SECONDS_CACHE_SESSION = 86400;
    public static final int DEFAULT_SECONDS_CACHE = 300;
    public static final int DEFAULT_SECONDS_IN_DAY_CACHE = 86400;

    /*----------------------------------- Setting $SgGlobalEntity ---------------------------------------------*/

    public static final char SPLIT_CHAR = '_';


    public static final String E_SESSION_KEY = "SESSION_KEY";
    public static final String E_USER_ID = "USER_ID";
    public static final String E_USER_EMAIL = "USER_EMAIL";
    public static final String E_USER_PASSWORD = "USER_PASSWORD";
    public static final String E_COURSE_ID = "COURSE_ID";
    public static final String E_ACTIVITY_ID = "ACTIVITY_ID";
    public static final String E_GOAL_CONTENT = "GOAL_CONTENT";
    public static final String E_COURSE_USER_ID = "COURSE_USER_ID";
    public static final String E_LESSON_ID = "LESSON_ID";
    public static final String E_UNIT_ID = "UNIT_ID";

    /*----------------------------------- Setting $SessionKey ---------------------------------------------*/
    public static final String K_FE_SESSION = "FE_SESSION";


    /*----------------------------------- Setting $SgUserEntityKey ---------------------------------------------*/
    public static final String K_FE_USER_INFO = "FE_USER_INFO";


    /*----------------------------------- Setting $SgTraineeGoalEntityKey ---------------------------------------------*/
    public static final String K_FE_TRAINEE_GOAL = "FE_TRAINEE_GOAL";


    /*----------------------------------- Setting $SgLesssonKey ---------------------------------------------*/
    public static final String K_FE_LESSON = "FE_LESSON";


    /*----------------------------------- Setting $SgUnitKey ---------------------------------------------*/
    public static final String K_FE_UNIT = "FE_UNIT";


    /*----------------------------------- Setting $SgLesssonKey ---------------------------------------------*/
    public static final String K_FE_ACTIVITY = "FE_ACTIVITY";


    /*----------------------------------- Setting $SgTraineeResultEntityKey ---------------------------------------------*/
    public static final String K_FE_TRAINEE_RESULT = "FE_TRAINEE_RESULT";


    /*----------------------------------- Setting $SgCourseTraineeEntityKey ---------------------------------------------*/
    public static final String K_FE_COURSE_TRAINEE = "FE_COURSE_TRAINEE";


    /*----------------------------------- Setting $SgOfflineClassTraineeEntityKey ---------------------------------------------*/
    public static final String K_FE_OFFLINE_CLASS_TRAINEE = "FE_OFFLINE_CLASS_TRAINEE";


    /*----------------------------------- Setting $SgOfflineClassEntityKey ---------------------------------------------*/
    public static final String K_FE_OFFLINE_CLASS = "FE_OFFLINE_CLASS";


    /*----------------------------------- Setting $SgOfflineClassTypeEntityKey ---------------------------------------------*/
    public static final String K_FE_OFFLINE_CLASS_TYPE = "FE_OFFLINE_CLASS_TYPE";
}
