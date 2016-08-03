package com.example.jakub.pwrstproject.util;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import com.example.jakub.pwrstproject.Constants;
import com.example.jakub.pwrstproject.PWrSTApplication;
import com.example.jakub.pwrstproject.PWrSTPreferences;
import com.example.jakub.pwrstproject.model.User;

public class DatabaseHelper {

    private DatabaseReference mDatabaseReference;
    private PWrSTPreferences mHodorPreferences;

    public DatabaseHelper() {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mHodorPreferences = PWrSTApplication.getInstance().getPreferences();
    }

    public void updateUserToken(final String token) {
        mDatabaseReference.child(Constants.DB_USER)
                .child(mHodorPreferences.getUsername())
                .child(Constants.DB_TOKEN)
                .setValue(token);
    }

    public void getUserFromDatabase(final String username, final OnUserReceivedListener listener) {
        mDatabaseReference.child(Constants.DB_USER)
                .child(username)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(final DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);
                        listener.onUserReceived(user);
                    }

                    @Override
                    public void onCancelled(final DatabaseError databaseError) {

                    }
                });
    }

    public String getUserToken(final String username) {
        return mDatabaseReference.child(username).getKey();
    }

    public void addFriendToDatabase(final User user) {
        mDatabaseReference.child(Constants.DB_FRIENDS)
                .child(mHodorPreferences.getUsername())
                .child(user.getUsername())
                .setValue(user);
    }

    public void getAllFriendsFromDatabase(final OnUsersReceivedListener listener) {
        mDatabaseReference.child(Constants.DB_FRIENDS)
                .child(mHodorPreferences.getUsername())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(final DataSnapshot dataSnapshot) {
                        List<String> users = new ArrayList<>();
                        for (DataSnapshot friendsList : dataSnapshot.getChildren()) {
                            User friend = friendsList.getValue(User.class);
                            users.add(friend.getUsername());
                        }
                        listener.onUsersReceived(users);
                    }

                    @Override
                    public void onCancelled(final DatabaseError databaseError) {

                    }
                });
    }

    public void saveUserInDatabase(final User user, final OnFinishedListener listener) {
        mHodorPreferences.setUsername(user.getUsername());
        mDatabaseReference.child(Constants.DB_USER)
                .child(user.getUsername())
                .setValue(user)
                .addOnCompleteListener(task -> listener.onFinished());
    }

    public interface OnUserReceivedListener {
        void onUserReceived(User user);
    }

    public interface OnUsersReceivedListener {
        void onUsersReceived(List<String> users);
    }

    public interface OnFinishedListener {
        void onFinished();
    }
}
