package cafe.controller;

import cafe.constants.CafeConstants;
import cafe.rest.UserRest;
import cafe.service.UserService;
import cafe.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController implements UserRest {

    @Autowired
    UserService userService;


    @Override
    public ResponseEntity<String> singUp(Map<String, String> requestMap) {
        try{
            return userService.singUp(requestMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMTHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
