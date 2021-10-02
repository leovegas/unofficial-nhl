package com.app.unofficial_nhl.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;



public class NotificationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;



    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
    }


    public void block() {
        System.out.println("block");
        mText.setValue("block");
    }

    public LiveData<String> getText() {
        return mText;
    }
}