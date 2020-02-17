package com.matheussilas.listadetarefas.Activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.matheussilas.listadetarefas.adapter.Adapter;
import com.matheussilas.listadetarefas.helper.AssignmentDAO;
import com.matheussilas.listadetarefas.helper.DBHelper;
import com.matheussilas.listadetarefas.model.Assignment;
import com.matheussilas.listadetarefas.R;
import com.matheussilas.listadetarefas.helper.RecyclerItemClickListener;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter adapter;
    private List<Assignment> listAssignment = new ArrayList<>();
    private Assignment assignmentSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }

                            @Override
                            public void onItemClick(View view, int position) {
                                Assignment assignmentSelect = listAssignment.get(position);

                                Intent intent = new Intent(MainActivity.this, NewAssignment.class);
                                intent.putExtra("assignmentSelect", assignmentSelect);
                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                assignmentSelect = listAssignment.get(position);

                                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                                alert.setTitle("Confirmar Exclusão");
                                alert.setMessage("Deseja excluir a tarefa: " + assignmentSelect.getNameAssignment() + "?");
                                alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        AssignmentDAO assignmentDAO = new AssignmentDAO(getApplicationContext());
                                        if (assignmentDAO.delete(assignmentSelect)) {
                                            loadAssignment();
                                            Toast.makeText(getApplicationContext(), "Tarefa excluida com sucesso", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Erro ao excluir tarefa", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });
                                alert.setNegativeButton("Não", null);
                                alert.create();
                                alert.show();
                            }
                        }
                )
        );

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewAssignment.class);
                startActivity(intent);
            }
        });
    }


    public void loadAssignment() {

        //Listar Tarefas
        AssignmentDAO assignmentDAO = new AssignmentDAO(getApplicationContext());
        listAssignment = assignmentDAO.listar();

        //set adapter
        adapter = new Adapter(listAssignment);

        //set recycleview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        loadAssignment();
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
