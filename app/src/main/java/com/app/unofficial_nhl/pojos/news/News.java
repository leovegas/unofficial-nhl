package com.app.unofficial_nhl.pojos.news;


public class News {

    private String status;
    private String copyright;
    private Response response;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "News{" +
                "status='" + status + '\'' +
                ", copyright='" + copyright + '\'' +
                ", response=" + response +
                '}';
    }
}
