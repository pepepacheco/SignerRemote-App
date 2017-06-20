package com.iesvirgendelcarmen.rvargas.touchremote.controller;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import com.iesvirgendelcarmen.rvargas.touchremote.model.Notification;
import com.iesvirgendelcarmen.rvargas.touchremote.model.Registration;
import com.iesvirgendelcarmen.rvargas.touchremote.model.User;
import com.iesvirgendelcarmen.rvargas.touchremote.rest.UserRest;

@TargetApi(Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    private CancellationSignal cancellationSignal;
    private View appView;
    private User employee;
    private Notification notification;

    public FingerprintHandler(View appView, User employee, Notification notification) {
        this.appView = appView;
        this.employee = employee;
        this.notification = notification;
    }

    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {
        cancellationSignal = new CancellationSignal();

        if (ActivityCompat.checkSelfPermission(appView.getContext(), Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }

    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        Snackbar.make(appView, "Error de autoneticación:\n" + errString, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        Snackbar.make(appView, "Ayuda:\n" + helpString, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationFailed() {
        Snackbar.make(appView, "Autentiacción errónea.", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        Snackbar.make(appView, "Autenticacion satisfactoria", Snackbar.LENGTH_LONG).show();

        LocationManager locationManager = (LocationManager) appView.getContext().getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(appView.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(appView.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        String organizerId = employee.getOrganizer().getId();
        String employeeId = employee.getId();
        String notificationId = notification.getId();
        double latitude = 0;
        double longitude = 0;

        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }

        Registration registration = new Registration(organizerId, employeeId, notificationId, longitude, latitude);

        UserRest.addRegistration(appView, registration);
    }

}