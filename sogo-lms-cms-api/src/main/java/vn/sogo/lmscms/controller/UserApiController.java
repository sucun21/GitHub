package vn.sogo.lmscms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import vn.sogo.lmscms.model.*;
import vn.sogo.lmscms.model.request.*;
import vn.sogo.lmscms.model.response.*;
import vn.sogo.lmscms.services.interfaceservice.IUserService;
import vn.sogo.lmscms.settings.UrlEntity;

@RestController
@RequestMapping(UrlEntity.E_USER)
@CrossOrigin
public class UserApiController extends BaseController {

    /*----------------------------------- Variable $UserApiController ---------------------------------------------*/

//    private static final Logger logger = Logger.getLogger(UserApiController.class);

    @Autowired
    IUserService userService;

    /*----------------------------------- Method $UserApiController ---------------------------------------------*/

    @RequestMapping(value = UrlEntity.A_CHECK_LOGIN, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public @ResponseBody ApiResponse<UserSessionInfo> CheckLogin(@RequestBody CheckLoginRequest model) throws Exception {
        return responseResult(userService.CheckLogin(model.getEmail(), model.getPassword()));
    }
    
    @RequestMapping(value = UrlEntity.A_CLEAR_SESSION_KEY, method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
    @CrossOrigin
    public @ResponseBody ApiResponse<Boolean> ClearSessionKey(@RequestBody String sessionKey) throws Exception {
        return responseResult(userService.ClearSessionKey(sessionKey));
    }
    
    @RequestMapping(value = UrlEntity.A_GET_USER_INFO_BY_SESSION, method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
    @CrossOrigin
    public @ResponseBody ApiResponse<UserSessionInfo> GetUserInfoBySession(@RequestBody String sessionKey) throws Exception {
        return responseResult(userService.GetUserInfoBySession(sessionKey));
    }
    
    @RequestMapping(value = UrlEntity.A_GET_ALL_TRAINER, method = RequestMethod.POST)
    @CrossOrigin
    public @ResponseBody ApiResponse<List<TrainerInfo>> GetAllTrainer() throws Exception {
        return responseResult(userService.GetAllTrainer());
    }
    
    @RequestMapping(value = UrlEntity.A_RESET_STUDENT_PASSWORD, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public @ResponseBody ApiResponse<CUDReturnMessage> ResetStudentPassword(@RequestBody ResetStudentPasswordRequest request) throws Exception {
        return responseResult(userService.ResetStudentPassword(request));
    }
    @RequestMapping(value = UrlEntity.A_ADD_NEW_USER, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public @ResponseBody ApiResponse<CUDReturnMessage> AddNewUser(@RequestBody AddNewUserRequest model) throws Exception {
        return responseResult(userService.AddNewUser(model));
    }
}
