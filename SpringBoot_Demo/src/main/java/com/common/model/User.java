package com.common.model;

import com.common.generic.AbstractGenericEntity;
import com.common.generic.GenericEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class User extends AbstractGenericEntity<Integer> implements GenericEntity<Integer>, Serializable {
    private Integer id;

    private String username;

    private String passwd;

    private String role;

    private String comments;

    @Override
    public Integer getPK() {

        return id;
    }
}