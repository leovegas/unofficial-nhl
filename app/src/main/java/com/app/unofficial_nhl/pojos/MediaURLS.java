package com.app.unofficial_nhl.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MediaURLS {

@SerializedName("FLASH_192K_320X180")
@Expose
private String flash192k320x180;
@SerializedName("FLASH_450K_384x216")
@Expose
private String fLASH450K384x216;
@SerializedName("FLASH_1200K_640X360")
@Expose
private String flash1200k640x360;
@SerializedName("FLASH_1800K_896x504")
@Expose
private String fLASH1800K896x504;
@SerializedName("HTTP_CLOUD_MOBILE")
@Expose
private String httpCloudMobile;
@SerializedName("HTTP_CLOUD_TABLET")
@Expose
private String httpCloudTablet;
@SerializedName("HTTP_CLOUD_TABLET_60")
@Expose
private String httpCloudTablet60;
@SerializedName("HTTP_CLOUD_WIRED")
@Expose
private String httpCloudWired;
@SerializedName("HTTP_CLOUD_WIRED_60")
@Expose
private String httpCloudWired60;
@SerializedName("HTTP_CLOUD_WIRED_WEB")
@Expose
private String httpCloudWiredWeb;

public String getFlash192k320x180() {
return flash192k320x180;
}

public void setFlash192k320x180(String flash192k320x180) {
this.flash192k320x180 = flash192k320x180;
}

public String getFLASH450K384x216() {
return fLASH450K384x216;
}

public void setFLASH450K384x216(String fLASH450K384x216) {
this.fLASH450K384x216 = fLASH450K384x216;
}

public String getFlash1200k640x360() {
return flash1200k640x360;
}

public void setFlash1200k640x360(String flash1200k640x360) {
this.flash1200k640x360 = flash1200k640x360;
}

public String getFLASH1800K896x504() {
return fLASH1800K896x504;
}

public void setFLASH1800K896x504(String fLASH1800K896x504) {
this.fLASH1800K896x504 = fLASH1800K896x504;
}

public String getHttpCloudMobile() {
return httpCloudMobile;
}

public void setHttpCloudMobile(String httpCloudMobile) {
this.httpCloudMobile = httpCloudMobile;
}

public String getHttpCloudTablet() {
return httpCloudTablet;
}

public void setHttpCloudTablet(String httpCloudTablet) {
this.httpCloudTablet = httpCloudTablet;
}

public String getHttpCloudTablet60() {
return httpCloudTablet60;
}

public void setHttpCloudTablet60(String httpCloudTablet60) {
this.httpCloudTablet60 = httpCloudTablet60;
}

public String getHttpCloudWired() {
return httpCloudWired;
}

public void setHttpCloudWired(String httpCloudWired) {
this.httpCloudWired = httpCloudWired;
}

public String getHttpCloudWired60() {
return httpCloudWired60;
}

public void setHttpCloudWired60(String httpCloudWired60) {
this.httpCloudWired60 = httpCloudWired60;
}

public String getHttpCloudWiredWeb() {
return httpCloudWiredWeb;
}

public void setHttpCloudWiredWeb(String httpCloudWiredWeb) {
this.httpCloudWiredWeb = httpCloudWiredWeb;
}

}