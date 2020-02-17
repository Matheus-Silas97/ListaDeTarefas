package com.matheussilas.listadetarefas.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.matheussilas.listadetarefas.R;
import com.matheussilas.listadetarefas.helper.AssignmentDAO;
import com.matheussilas.listadetarefas.model.Assignment;

public class NewAssignment extends AppCompatActivity {
    private TextInputEditText textAssignment;
    private Assignment assignmentCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_assignment);
        textAssignment = findViewById(R.id.textAssignment);

        //recuperar tarefa
        assignmentCurrent = (Assignment) getIntent().getSerializableExtra("assignmentSelect");

        //set textbox
        if (assignmentCurrent != null) {
            textAssignment.setText(assignmentCurrent.getNameAssignment());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_new_assignment, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.save:

                AssignmentDAO assignmentDAO = new AssignmentDAO(getApplicationContext());
                //update
                if (assignmentCurrent != null) {
                    String nameAssignment = textAssignment.getText().toString();
                    if (!nameAssignment.isEmpty()) {
                        Assignment assignment = new Assignment();
                        assignment.setNameAssignment(nameAssignment);
                        assignment.setId(assignmentCurrent.getId());

                        if (assignmentDAO.update(assignment)) {
                            finish();
                            Toast.makeText(getApplicationContext(), "Tarefa Atualizada com sucesso", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Erro ao atualizar tarefa", Toast.LENGTH_SHORT).show();
                        }

                    }

                } else {
                    String nameAssignment = textAssignment.getText().toString();
                    //save
                    if (!nameAssignment.isEmpty()) {
                        Assignment assignment = new Assignment();
                        assignment.setNameAssignment(nameAssignment);
                        if (assignmentDAO.save(assignment)) {
                            Toast.makeText(getApplicationContext(), "Tarefa salva com sucesso", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Preencha o campo tarefa", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
