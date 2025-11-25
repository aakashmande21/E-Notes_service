package com.JSBP.E_Notes_service.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {

    private Boolean isActive;
    private Boolean isDeleted;
    private int createdBy;
    private Date createdOn;
    private int updatedBy;
    private Date updatedOn;
}
