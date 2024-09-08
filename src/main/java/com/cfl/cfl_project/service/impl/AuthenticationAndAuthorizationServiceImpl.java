package com.cfl.cfl_project.service.impl;


import com.cfl.cfl_project.jwt.JwtTokenGenerator;
import com.cfl.cfl_project.model.Login;
import com.cfl.cfl_project.model.Register;
import com.cfl.cfl_project.repository.RegisterRepository;
import com.cfl.cfl_project.service.AuthenticationAndAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationAndAuthorizationServiceImpl implements AuthenticationAndAuthorizationService {

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private JwtTokenGenerator jwtTokenGenerator;
//
//    @Autowired
//    private RefreshServiceImpl refreshServiceImpl;

    @Override
    public String register(Register register) {
        System.out.print(register.getUserPassword());
        register.setUserPassword(passwordEncoder.encode(register.getUserPassword()));

        Register newRegister=registerRepository.save(register);
        System.out.println(newRegister.getRole().toString());
//        refreshServiceImpl.createRefreshToken(newRegister.getUsername());


        // this line generate JWT Token
        String jwtToken=jwtTokenGenerator.generateToken(newRegister);
        return jwtToken;
    }


    @Autowired
    AuthenticationManager authenticationManager;


    //LoginModel


    // step 3
    @Override
    public Authentication login(Login login) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(login.getUserName(), login.getUserPassword());
        System.out.println(authToken+"authToken");
        Authentication authentication = authenticationManager.authenticate(authToken); // from this line it will throw to step 4

        // step 8
        return authentication;
    }

    @Override
    public Boolean isValidEmail(String username) {
        return registerRepository.existsByUserName(username);
    }


}
