package vn.sogo.lmscms.services.interfaceservice;

import java.util.List;

import vn.sogo.lmscms.model.*;
import vn.sogo.lmscms.model.request.*;

/**
 * Created by VinhLe on 4/20/2017.
 */
public interface IUserService {

//    protected static final String E_URL = "FE_API_USER";
	UserSessionInfo CheckLogin(String email, String password) throws Exception;
	UserSessionInfo GetUserInfoBySession(String sessionKey) throws Exception;
	Boolean ClearSessionKey(String sessionKey) throws Exception;
	List<TrainerInfo> GetAllTrainer() throws Exception;
	List<ActivityLogInfo> GetStudentActivityLog(GetStudentActivityLogRequest request) throws Exception;
	CUDReturnMessage ResetStudentPassword(ResetStudentPasswordRequest request) throws Exception;
	CUDReturnMessage AddNewUser(AddNewUserRequest req) throws Exception;
}
