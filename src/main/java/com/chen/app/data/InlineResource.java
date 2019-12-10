package com.chen.app.data;

public class InlineResource {
    private String cid;
    private String path;
    public String getCid() {
        return cid;
    }
    public void setCid(String cid) {
        this.cid = cid;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public InlineResource(String cid, String path) {
        super();
        this.cid = cid;
        this.path = path;
    }
}
