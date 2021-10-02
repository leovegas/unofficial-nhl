package com.app.unofficial_nhl.ui.home;

import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;



    public HomeViewModel() {
        mText = new MutableLiveData<>();
    }

    public void fire() {
        mText.setValue("fire");
    }

    public LiveData<String> getText() {
        return mText;
    }
}