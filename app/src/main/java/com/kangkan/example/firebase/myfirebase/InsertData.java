package com.kangkan.example.firebase.myfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kangkan.example.firebase.myfirebase.model.User;

import java.util.UUID;

public class InsertData extends AppCompatActivity {

    Button b;
    EditText editText1,editText2,editText3;

    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);
        b=findViewById(R.id.btn_insert);
        editText1=findViewById(R.id.ext_name);
        editText2=findViewById(R.id.ext_prize);
        editText3=findViewById(R.id.ext_product);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
                startActivity(new Intent(InsertData.this,DashBoard.class));

            }
        });
        initFirebase();

    }

    private void initFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }

    private  void createUser(){
        User user1=new User(UUID.randomUUID().toString(),editText1.getText().toString(),editText2.getText().toString(),editText3.getText().toString());
        databaseReference.child("user").child(user1.getPname()).setValue(user1);
        clearEditText();

    }

    private void clearEditText() {
        editText1.setText("");
        editText2.setText("");
        editText3.setText("");


    }
}