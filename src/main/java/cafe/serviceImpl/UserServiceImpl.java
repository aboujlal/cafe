package cafe.serviceImpl;

import cafe.POJO.User;
import cafe.constants.CafeConstants;
import cafe.dao.UserDao;
import cafe.service.UserService;
import cafe.utils.CafeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {


    @Resource
    UserDao userDao;
    public ResponseEntity<String> singUp(Map<String, String> requestMap) {
        log.info("Inside singUp");
        try {
            if(validateSingUpMap(requestMap)) {
                User user = userDao.findUserByEmail(requestMap.get("email"));
                if(Objects.isNull(user)) {
                    userDao.save(getUserFromMap(requestMap));
                    return CafeUtils.getResponseEntity(CafeConstants.SUCCESS_SINGUP, HttpStatus.OK);
                }else {
                    return CafeUtils.getResponseEntity(CafeConstants.EMAIL_ALREADY_EXIST, HttpStatus.BAD_REQUEST);
                }
            } else {
                return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstants.SOMTHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private User getUserFromMap(Map<String, String> requestMap) {
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setEmail(requestMap.get("email"));
        user.setContactNumber(requestMap.get("contactNumber"));
        user.setPassword(requestMap.get("password"));
        user.setRole("user");
        user.setStatus("false");
        return user;
    }

    private boolean validateSingUpMap(Map<String, String> requestMap) {
        return requestMap.containsKey("name") && requestMap.containsKey("password")
                && requestMap.containsKey("contactNumber") && requestMap.containsKey("email");
    }
}
