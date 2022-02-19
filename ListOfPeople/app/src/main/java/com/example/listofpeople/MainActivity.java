package com.example.listofpeople;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<People> peopleList = new ArrayList<>();

    People firstPerson = new People(0,"Ayo",12);
    People secondPerson = new People(1,"Tayo",13);
    People thirdPerson = new People(2,"Seun",14);
    People fourthPerson = new People(3,"Iyanu",15);
    People fifthPerson = new People(4,"Sogo",16);
    People sixthPerson = new People(5,"Heezee",17);
    People seventhPerson = new People(6,"Demola",18);
    People eightPerson = new People(7,"Aliyu", 19);
    People ninthPerson = new People(8,"Bidemi",20);
    People tenthPerson = new People(9,"Bola",21);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView mRecyclerView = findViewById(R.id.people_list_recyclerView);

        peopleList.add(0,firstPerson);
        peopleList.add(1,secondPerson);
        peopleList.add(2,thirdPerson);
        peopleList.add(3,fourthPerson);
        peopleList.add(4,fifthPerson);
        peopleList.add(5,sixthPerson);
        peopleList.add(6,seventhPerson);
        peopleList.add(7,eightPerson);
        peopleList.add(8,ninthPerson);
        peopleList.add(9,tenthPerson);



        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ListOfPeopleAdapter adapter = new ListOfPeopleAdapter(peopleList, this);
        mRecyclerView.setAdapter(adapter);

    }
}