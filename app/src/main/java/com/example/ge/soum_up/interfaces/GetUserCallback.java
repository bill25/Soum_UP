package com.example.ge.soum_up.interfaces;

import com.example.ge.soum_up.model.User;

/**
 * Created by GE on 09/02/2017.
 */

public interface GetUserCallback {
    public abstract void done(User returnedUser);
}
