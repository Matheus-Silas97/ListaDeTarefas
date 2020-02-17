package com.matheussilas.listadetarefas.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.matheussilas.listadetarefas.model.Assignment;

import java.util.ArrayList;
import java.util.List;

public class AssignmentDAO implements iAssignmentDAO {

    private SQLiteDatabase write;
    private SQLiteDatabase read;

    public AssignmentDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        write = dbHelper.getWritableDatabase();
        read = dbHelper.getReadableDatabase();
    }

    @Override
    public boolean save(Assignment assignment) {

        ContentValues cv = new ContentValues();
        cv.put("name", assignment.getNameAssignment());

        try {
            write.insert(DBHelper.TB_ASSIGNMENT, null, cv);
            Log.i("INFO", "Tarefa salva com sucesso");
        } catch (Exception e) {
            Log.e("INFO", "Erro ao salvar tarefa" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Assignment assignment) {
        ContentValues cv = new ContentValues();
        cv.put("name", assignment.getNameAssignment());
        try {
            String[] args = {assignment.getId().toString()};

            write.update(DBHelper.TB_ASSIGNMENT, cv, "id=?", args);
            Log.i("INFO", "Tarefa atualizada com sucesso");
        } catch (Exception e) {
            Log.e("INFO", "Erro ao atualizar tarefa" + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean delete(Assignment assignment) {
        try {
            String[] args = {assignment.getId().toString()};

            write.delete(DBHelper.TB_ASSIGNMENT, "id=?", args);
            Log.i("INFO", "Tarefa removida com sucesso");
        } catch (Exception e) {
            Log.e("INFO", "Erro ao remover tarefa" + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public List<Assignment> listar() {


        List<Assignment> assignments = new ArrayList<>();
        String sql = "SELECT * FROM " + DBHelper.TB_ASSIGNMENT + ";";
        Cursor c = read.rawQuery(sql, null);

        while (c.moveToNext()) {
            Assignment assignment = new Assignment();

            Long id = c.getLong(c.getColumnIndex("id"));
            String nameAssignment = c.getString(c.getColumnIndex("name"));

            assignment.setId(id);
            assignment.setNameAssignment(nameAssignment);

            assignments.add(assignment);

        }
        return assignments;
    }
}
