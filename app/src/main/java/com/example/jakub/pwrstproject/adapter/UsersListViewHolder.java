package com.example.jakub.pwrstproject.adapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.jakub.pwrstproject.PWrSTApplication;
import com.example.jakub.pwrstproject.R;
import com.example.jakub.pwrstproject.model.User;
import com.example.jakub.pwrstproject.util.DatabaseHelper;

public class UsersListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_view_username) TextView mTextViewUsername;

    private DatabaseHelper mDatabaseHelper;

    public UsersListViewHolder(final View itemView, final DatabaseHelper databaseHelper) {
        super(itemView);
        mDatabaseHelper = databaseHelper;
        ButterKnife.bind(this, itemView);
    }

    public void bindData(final String username, final int backgroundColorRes) {
        mTextViewUsername.setText(username);
        mTextViewUsername.setBackgroundColor(ContextCompat.getColor(mTextViewUsername.getContext(),
                backgroundColorRes));

        mDatabaseHelper.getUserFromDatabase(username, this::setOnClickListener);
    }

    private void setOnClickListener(final User friend) {
        mTextViewUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PWrSTApplication.getInstance().getRestAdapter().sendPush(friend.getToken());
            }
        });
    }
}
