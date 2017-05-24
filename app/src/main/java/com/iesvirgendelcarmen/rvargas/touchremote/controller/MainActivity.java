package com.iesvirgendelcarmen.rvargas.touchremote.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.iesvirgendelcarmen.rvargas.touchremote.R;
import com.iesvirgendelcarmen.rvargas.touchremote.model.User;
import com.iesvirgendelcarmen.rvargas.touchremote.rest.UserRest;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager llmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Notificaciones");

        User employee = getIntent().getParcelableExtra("user");

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        llmanager = new LinearLayoutManager(this);
        llmanager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llmanager);

        UserRest.getNotifications(this, employee.getOrganizer().getId(), employee.getId(), recyclerView, employee);
    }
}
