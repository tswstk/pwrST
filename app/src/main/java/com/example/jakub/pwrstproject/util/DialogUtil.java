package com.example.jakub.pwrstproject.util;

import android.app.AlertDialog;
import android.content.Context;
import com.example.jakub.pwrstproject.R;

public class DialogUtil {
    public static void showUserDoesNotExist(Context context) {
        new AlertDialog.Builder(context).setTitle(R.string.error)
                .setMessage(R.string.user_not_exists)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    dialog.cancel();
                })
                .create()
                .show();
    }

    public static void showSendNotificationDialog(Context context, final String username,
            final Runnable onPositiveButtonClick) {
        new AlertDialog.Builder(context).setTitle(R.string.send_notification)
                .setMessage(context.getResources().getString(R.string.send_notification_msg, username))
                .setPositiveButton(R.string.send, (dialog, which) -> {
                    onPositiveButtonClick.run();
                })
                .create()
                .show();
    }
}
