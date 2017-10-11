package vn.sogo.lmscms.services.implservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import vn.sogo.lmscms.dao.impldao.UserDaoImpl;
import vn.sogo.lmscms.helpers.PasswordHelper;
import vn.sogo.lmscms.model.*;
import vn.sogo.lmscms.model.request.*;
import vn.sogo.lmscms.services.interfaceservice.IUserService;
import vn.sogo.lmscms.settings.RedisKeyEntity;

/**
 * Created by VinhLe on 4/20/2017.
 */
public class UserServiceImpl extends BaseService implements IUserService {

	// private static final String DEFAULT_E_URL = "BE_USER_API";
	private static final String REDIS_KEY_BE_SESSION = "BE_SS_%s_%s";

	@Autowired
	UserDaoImpl userDao;

	@Override
	public UserSessionInfo CheckLogin(String email, String password) throws Exception {


		UserSessionInfo checkResult = userDao.checkLogin(email, PasswordHelper.ParseToMD5(password));

		if (checkResult != null) {

			String sessionKey = String.format(REDIS_KEY_BE_SESSION, checkResult.getUserId(), new Date().getTime());

			writeCache(sessionKey, checkResult, RedisKeyEntity.DEFAULT_SECONDS_CACHE_SESSION);

		}

		return checkResult;
	}

	@Override
	public Boolean ClearSessionKey(String sessionKey) throws Exception {

		deleteCache(sessionKey);

		return Boolean.TRUE;
	}

	@Override
	public UserSessionInfo GetUserInfoBySession(String sessionKey) throws Exception {		
		return (UserSessionInfo) getCache(sessionKey);
	}
	@Override
	public List<TrainerInfo> GetAllTrainer() throws Exception {
		return userDao.GetAllTrainer();
	}
	
	@Override
	public List<ActivityLogInfo> GetStudentActivityLog(GetStudentActivityLogRequest request) throws Exception {
		return userDao.GetStudentActivityLog(request);
	}
		
	@Override
	public CUDReturnMessage ResetStudentPassword(ResetStudentPasswordRequest request) throws Exception {
		request.setNewPassword(PasswordHelper.ParseToMD5(request.getNewPassword()));
		
		return userDao.ResetStudentPassword(request);
	}
	
	@Override
	public CUDReturnMessage AddNewUser(AddNewUserRequest request) throws Exception {

		String md5Password = PasswordHelper.ParseToMD5(request.getPassword());

		request.setPassword(md5Password);

		return userDao.addNewUser(request);
	}
}
