package com.example.betaomer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FBref {

    public static FirebaseAuth refAuth = FirebaseAuth.getInstance();

    public static FirebaseDatabase FBDB = FirebaseDatabase.getInstance();
    public static DatabaseReference refUsers = FBDB.getReference("Users");
    public static DatabaseReference refEventt=FBDB.getReference("Eventt");

}


