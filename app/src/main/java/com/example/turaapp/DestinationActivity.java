package com.example.turaapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DestinationActivity extends AppCompatActivity {
    private static final int SECRET_KEY = 99;

    private FirebaseUser user;

    private FrameLayout redCircle;
    private TextView countTextView;
    private int cartItems = 0;
    private int gridNumber = 1;

    // Member variables.
    private RecyclerView mRecyclerView;
   // private ArrayList<ShopingItem> mItemsData;
    //private ShopingItemAdapter mAdapter;

    private SharedPreferences preferences;

    private boolean viewRow = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_destination);

        int secretkey = getIntent().getIntExtra("SECRET_KEY", 0);
        if(secretkey != 99){
            finish();
        }

        user = FirebaseAuth.getInstance().getCurrentUser();


    }
}