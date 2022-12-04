package com.example.inoutwork;

public class DataModal {
    // string variables for our name and job
    private String object_code;
    private String table_number;
    private String event_datetime;
    private String event;
    private String object_bin;

    public DataModal(String object_code, String table_number, String event_datetime, String event, String object_bin) {
        this.object_code = object_code;
        this.table_number = table_number;
        this.event_datetime = event_datetime;
        this.event = event;
        this.object_bin = object_bin;
    }

    public String getObject_code() {
        return object_code;
    }

    public void setObject_code(String object_code) {
        this.object_code = object_code;
    }

    public String getTable_number() {
        return table_number;
    }

    public void setTable_number(String table_number) {
        this.table_number = table_number;
    }

}
