package com.springframework.petclinic.model;

import java.io.Serializable;

public class BaseEntity implements Serializable {

    private Long ig;

    public Long getIg() {
        return ig;
    }

    public void setIg(Long ig) {
        this.ig = ig;
    }
}
