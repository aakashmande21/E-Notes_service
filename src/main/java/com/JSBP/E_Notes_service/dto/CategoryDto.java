package com.JSBP.E_Notes_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private int id;
    private String name;
    private String description;
    private Boolean isActive;
    private int createdBy;
    private Date createdOn;
    private int updatedBy;
    private Date updatedOn;
}
