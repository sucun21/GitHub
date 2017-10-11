package vn.sogo.lmscms.dao.interfacedao;

import java.util.List;

import vn.sogo.lmscms.model.*;
import vn.sogo.lmscms.model.request.*;

public interface IUserDao {

	UserSessionInfo checkLogin(String email, String password) throws Exception;
	List<TrainerInfo> GetAllTrainer() throws Exception;
	List<ActivityLogInfo> GetStudentActivityLog(GetStudentActivityLogRequest studentId) throws Exception;
	CUDReturnMessage ResetStudentPassword(ResetStudentPasswordRequest request) throws Exception;
	CUDReturnMessage addNewUser(AddNewUserRequest request) throws Exception;
}
