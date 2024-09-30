package com.cfl.cfl_project.service;

import com.cfl.cfl_project.model.Refresh;
import org.springframework.stereotype.Service;

@Service
public interface RefreshService {
    // generate refresh token
    public Refresh createRefreshToken(String userName);

    // verify refresh token
    public Refresh verifyRefreshToken(String token);
}
