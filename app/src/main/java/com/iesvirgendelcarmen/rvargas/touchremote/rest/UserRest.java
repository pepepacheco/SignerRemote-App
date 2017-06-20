package com.iesvirgendelcarmen.rvargas.touchremote.rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.iesvirgendelcarmen.rvargas.touchremote.adapters.CustomAdapter;
import com.iesvirgendelcarmen.rvargas.touchremote.controller.ChangePasswordActivity;
import com.iesvirgendelcarmen.rvargas.touchremote.controller.MainActivity;
import com.iesvirgendelcarmen.rvargas.touchremote.model.Notification;
import com.iesvirgendelcarmen.rvargas.touchremote.model.Registration;
import com.iesvirgendelcarmen.rvargas.touchremote.model.User;
import java.util.List;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public final class UserRest {

    private final static RestAdapter REST_ADAPTER;
    private final static UserService USER_SERVICE;
    private static User userActive;

    static {
        REST_ADAPTER = ApiClient.getAdapter();
        USER_SERVICE = REST_ADAPTER.create(UserService.class);
        userActive = null;
    }

    public static void loginUser(String email, String password, final View view) {

        final ProgressDialog progressDialog;
        progressDialog = ProgressDialog.show(view.getContext(), "Conectando...", "Espere por favor", true);


        USER_SERVICE.getUser(email, password, new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                if (!user.isChangePassword()) {
                    Intent intent = new Intent(view.getContext(), ChangePasswordActivity.class);
                    intent.putExtra("user", user);

                    view.getContext().startActivity(intent);
                    progressDialog.dismiss();

                } else {
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    intent.putExtra("user", user);

                    view.getContext().startActivity(intent);
                    progressDialog.dismiss();
                }

                userActive = user;
            }

            @Override
            public void failure(RetrofitError error) {
                Snackbar.make(view, "Error de autentificación", Snackbar.LENGTH_LONG).show();
                Log.v("login", error.getMessage());
                progressDialog.dismiss();
            }
        });
    }

    public static void getNotifications(final Context context, String userId, String employeeId, final RecyclerView recyclerView, final User employee) {

        USER_SERVICE.getNotificationsByUser(userId, employeeId, new Callback<List<Notification>>() {
            @Override
            public void success(List<Notification> notifications, Response response) {
                CustomAdapter customAdapter = new CustomAdapter(context, employee, notifications);
                recyclerView.setAdapter(customAdapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.v("notifications", error.getMessage());

            }
        });
    }

    public static void changePassword(final View view, String employeeId, String userId, String oldPassword, String newPassword) {
        final ProgressDialog progressDialog;
        progressDialog = ProgressDialog.show(view.getContext(), "Conectando...", "Espere por favor", true);

        USER_SERVICE.changePassword(employeeId, userId, oldPassword, newPassword, new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                progressDialog.dismiss();
                Snackbar.make(view, "Contraseña cambiada correctamente", Snackbar.LENGTH_LONG).show();
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                intent.putExtra("user", userActive);

                view.getContext().startActivity(intent);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.v("pedro", error.getMessage());

                if (error.getResponse().getStatus() == 400) {
                    Snackbar.make(view, "Contraseña incorrecta", Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(view, "Error interno", Snackbar.LENGTH_LONG).show();
                }
                progressDialog.dismiss();

            }
        });
    }

    public static void addRegistration(final View view, Registration registration) {
        final ProgressDialog progressDialog;
        progressDialog = ProgressDialog.show(view.getContext(), "Enviando datos...", "Espere por favor", true);

        USER_SERVICE.postRegistration(registration, new Callback<Boolean>() {
            @Override
            public void success(Boolean aBoolean, Response response) {
                progressDialog.dismiss();
                Snackbar.make(view, "Registro enviado correctamente", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void failure(RetrofitError error) {
                progressDialog.dismiss();
                Snackbar.make(view, "Error interno", Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
