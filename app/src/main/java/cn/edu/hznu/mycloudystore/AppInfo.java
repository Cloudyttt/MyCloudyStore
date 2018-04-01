package cn.edu.hznu.mycloudystore;

import android.media.Image;
import android.widget.ImageView;

/**
 * Created by Cloudy on 2017/12/12.
 */

public class AppInfo {
    String appName;
    String appType;
    int appIconID;
    int imageIntroID;
    String appIntro;
    String appPrice;
    String appTime;
    String appCurTime;

    public AppInfo(String appName, String appType, int appIconID, int imageIntroID, String appIntro,
                   String appPrice, String appTime, String appCurTime) {
        this.appName = appName;
        this.appType = appType;
        this.appIconID = appIconID;
        this.imageIntroID = imageIntroID;
        this.appIntro = appIntro;
        this.appPrice = appPrice;
        this.appTime = appTime;
        this.appCurTime = appCurTime;
    }

    public AppInfo(String appName, String appType, int appIconID, int imageIntroID, String
            appPrice, String appTime) {
        this.appName = appName;
        this.appType = appType;
        this.appIconID = appIconID;
        this.imageIntroID = imageIntroID;
        this.appPrice = appPrice;
        this.appTime = appTime;
    }

    public AppInfo(String appName, String appType, int appIconID, String appPrice, String appTime, String appCurTime) {
        this.appName = appName;
        this.appType = appType;
        this.appIconID = appIconID;
        this.appPrice = appPrice;
        this.appTime = appTime;
        this.appCurTime = appCurTime;
    }

    public AppInfo(String appName, int imageIntroID, String appIntro) {
        this.appName = appName;
        this.imageIntroID = imageIntroID;
        this.appIntro = appIntro;
    }

    public AppInfo(String appName, int appIconID, String appIntro, String appPrice) {
        this.appName = appName;
        this.appIconID = appIconID;
        this.appIntro = appIntro;
        this.appPrice = appPrice;
    }

    //getter
    public String getAppName() {
        return appName;
    }

    public String getAppType() {
        return appType;
    }

    public int getAppIconID() {
        return appIconID;
    }

    public int getImageIntroID() {
        return imageIntroID;
    }

    public String getAppIntro() {
        return appIntro;
    }

    public String getAppPrice() {
        return appPrice;
    }

    public String getAppTime() {
        return appTime;
    }

    public String getAppCurTime() {
        return appCurTime;
    }
    //setter
    public void setAppName(String name) {
        this.appName = name;
    }

    public void setAppType(String type) {
        this.appType = type;
    }

    public void setAppIconID(int appIconID) {
        this.appIconID = appIconID;
    }

    public void setImageIntroID(int imageIntroID) {
        this.imageIntroID = imageIntroID;
    }

    public void setAppIntro(String appIntro) {
        this.appIntro = appIntro;
    }

    public void setAppPrice(String appPrice) {
        this.appPrice = appPrice;
    }

    public void setAppTime(String appTime) {
        this.appTime = appTime;
    }

    public void setAppCurTime(String appCurTime) {
        this.appCurTime = appCurTime;
    }
}
