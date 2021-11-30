package com.example.master12021hunaultfil_rouge;

import android.os.Parcel;
import android.os.Parcelable;

public class TAskPrioriteItem implements Parcelable {
    String priorite;
    String task;

    public TAskPrioriteItem(String prio,String tache){
        priorite=prio;
        task=tache;
    }

    protected TAskPrioriteItem(Parcel in) {
        priorite = in.readString();
        task = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(priorite);
        dest.writeString(task);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TAskPrioriteItem> CREATOR = new Creator<TAskPrioriteItem>() {
        @Override
        public TAskPrioriteItem createFromParcel(Parcel in) {
            return new TAskPrioriteItem(in);
        }

        @Override
        public TAskPrioriteItem[] newArray(int size) {
            return new TAskPrioriteItem[size];
        }
    };
}
