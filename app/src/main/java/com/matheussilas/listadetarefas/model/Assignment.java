package com.matheussilas.listadetarefas.model;

import java.io.Serializable;

public class Assignment implements Serializable {

    private Long id;
    private String nameAssignment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameAssignment() {
        return nameAssignment;
    }

    public void setNameAssignment(String nameAssignment) {
        this.nameAssignment = nameAssignment;
    }
}
