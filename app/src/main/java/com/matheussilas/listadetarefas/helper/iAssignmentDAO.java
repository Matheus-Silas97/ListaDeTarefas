package com.matheussilas.listadetarefas.helper;

import com.matheussilas.listadetarefas.model.Assignment;

import java.util.List;

public interface iAssignmentDAO {
    public boolean save (Assignment assignment);
    public boolean update (Assignment assignment);
    public boolean delete (Assignment assignment);
    public List<Assignment> listar();
}
