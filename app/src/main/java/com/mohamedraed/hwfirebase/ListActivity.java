package com.mohamedraed.hwfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private ListView mUserListView;
    private FirebaseFirestore db;
    private UserAdapter mUserAdapter;
    private ArrayList<User> mUserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mUserListView = findViewById(R.id.list_view);

        db = FirebaseFirestore.getInstance();
        //Set up the ArrayList
        mUserList = new ArrayList<User>();
        //set the Adapter
        mUserAdapter = new UserAdapter(this, mUserList);

        mUserListView.setAdapter(mUserAdapter);

        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        User user = document.toObject(User.class);
                        mUserList.add(user);
                    }
                    ListView mUserListView = (ListView) findViewById(R.id.list_view);
                    UserAdapter mUserAdapter = new UserAdapter(ListActivity.this, mUserList);
                    mUserListView.setAdapter(mUserAdapter);
                }
                else {
                    Log.d("MissionActivity", "Error getting documents: ", task.getException());
                }
            }
        });
        //add the whole Arraylist of User to the adapter
        mUserAdapter.addAll(mUserList);
    }
}