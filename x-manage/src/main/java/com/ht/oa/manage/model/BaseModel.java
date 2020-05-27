package com.ht.oa.manage.model;

public class BaseModel {

    private int start = 0;

    private int limit = 20;

    private int end;

    private int page = 1;

    public int getStart() {
        if (page == 0) {
            return 0;
        }
        return (page - 1) * limit;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        if (limit > 50) {
            limit = 50;
        }
        return limit;
    }

    public void setLimit(int limit) {
        if (limit > 50) {
            limit = 50;
        }
        this.limit = limit;
    }

    public int getEnd() {
        return page * limit;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

}
