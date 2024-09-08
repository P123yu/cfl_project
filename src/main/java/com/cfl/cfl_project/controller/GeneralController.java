package com.cfl.cfl_project.controller;

import com.cfl.cfl_project.model.Login;
import com.cfl.cfl_project.model.Register;
import com.cfl.cfl_project.service.AuthenticationAndAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class GeneralController {

    @Autowired
    private AuthenticationAndAuthorizationService authenticationAndAuthorizationService;

    @GetMapping("/get")
    public String message(){
        return "user get";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Register register) {
        System.out.println(register.getRole()+"controller");
        String registerResponse=authenticationAndAuthorizationService.register(register);
        if(register!=null) {
            return ResponseEntity.ok(registerResponse);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not registerd");
        }
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login) {
        Authentication auth=authenticationAndAuthorizationService.login(login);
        if(auth!=null) {
            // step 9
            return ResponseEntity.ok(auth);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not login");
        }
    }


    @GetMapping("/getMail/{userName}")
    public ResponseEntity<?>getMail(@PathVariable String userName){
        Boolean mail=authenticationAndAuthorizationService.isValidEmail(userName);
        if(mail) {
            return ResponseEntity.ok(true);
        }
        else {
            return ResponseEntity.ok(false);
        }
    }
}
