// Session.java
package com.example.model;

import java.util.Date;

public class Session {
    private int id;
    private int serviceId;
    private Date date;
    private String time;
    
    

    public Session(int id, int serviceId, Date date, String time) {
		this.id = id;
		this.serviceId = serviceId;
		this.date = date;
		this.time = time;
	}
    
    

	public Session() {
	}



	// Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}