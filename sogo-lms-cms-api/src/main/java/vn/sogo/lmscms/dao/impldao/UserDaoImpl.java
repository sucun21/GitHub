package vn.sogo.lmscms.dao.impldao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import vn.sogo.lmscms.dao.interfacedao.IUserDao;
import vn.sogo.lmscms.helpers.MySqlHelper;
import vn.sogo.lmscms.model.ActivityLogInfo;
import vn.sogo.lmscms.model.CUDReturnMessage;
import vn.sogo.lmscms.model.TrainerInfo;
import vn.sogo.lmscms.model.UserSessionInfo;
import vn.sogo.lmscms.model.request.AddNewUserRequest;
import vn.sogo.lmscms.model.request.GetStudentActivityLogRequest;
import vn.sogo.lmscms.model.request.ResetStudentPasswordRequest;

public class UserDaoImpl implements IUserDao {
	@Autowired
	MySqlHelper mySqlHelper;
	
	@Override
    public UserSessionInfo checkLogin(String email, String password) throws Exception {
    	
		UserSessionInfo result = null;
    	
    	if(!email.isEmpty() && !password.isEmpty()){
    		Object[] params = new Object[]{
    				email,
    				password
        	};
    		result = mySqlHelper.ExecuteStoreProcSingleResult("web_cms_checklogin", params, UserSessionInfo.class);
    	}
    	
        return result;
    }
	@Override
	public List<TrainerInfo> GetAllTrainer() throws Exception {
		List<TrainerInfo> result = new ArrayList<TrainerInfo>();
		result = mySqlHelper.ExecuteStoreProc("web_cms_get_trainer_info", TrainerInfo.class);
		return result;
	}
	
	@Override
	public List<ActivityLogInfo> GetStudentActivityLog(GetStudentActivityLogRequest request) throws Exception {
		List<ActivityLogInfo> result = new ArrayList<ActivityLogInfo>();
		
		Object[] params = new Object[]{
				request.getStudentId(),
				request.getCourseTraineeId()
    	};
		
		result = mySqlHelper.ExecuteStoreProc("web_cms_get_student_activity_log", params, ActivityLogInfo.class);
		return result;
	}
	
	@Override
	public CUDReturnMessage ResetStudentPassword(ResetStudentPasswordRequest request) throws Exception {
		CUDReturnMessage result = new CUDReturnMessage();
		
		Object[] params = new Object[]{
				request.getActionUser(),
				request.getStudentId(),
				request.getNewPassword()
    	};
		
		result = mySqlHelper.ExecuteStoreProcSingleResult("web_cms_reset_student_password", params, CUDReturnMessage.class);
		
		return result;
	}

	@Override
	public CUDReturnMessage addNewUser(AddNewUserRequest request) throws Exception {
		CUDReturnMessage result = new CUDReturnMessage();
		
		Object[] params = new Object[]{
				request.getFirstName(),
				request.getLastName(),
				request.getUserTypeId(),
				request.getUserEmail(),
				request.getUserPhone(),
				request.getPassword(),
				request.getDateOfBirth(),
				request.getGender(),
				request.getCreatedBy()
    	};
		
		result = mySqlHelper.ExecuteStoreProcSingleResult("web_cms_add_new_user", params, CUDReturnMessage.class);
		
		return result;
	}

}
