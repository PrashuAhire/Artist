package com.zplus.Artist.model;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@MappedSuperclass
public class BaseModel {

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt=new Date();

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt=new Date();

    @PreUpdate
    public void setLastUpdate(){
        this.updatedAt=new Date();
    }
    public void setDateCreated(Date dateCreated) {
        this.createdAt = dateCreated;
    }
}
