package com.callback;

import com.model.UserModel;

public interface UserInterFace {

    void onUserDataSuccess(UserModel model);

    void onUserDataFailed();
}
