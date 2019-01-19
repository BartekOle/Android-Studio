package com.example.dios1.zadanie5a;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onResume(){
        super.onResume();
        final SensorManager sm = (SensorManager)getSystemService(SENSOR_SERVICE);

        boolean enabled = !sm.getSensorList(Sensor.TYPE_LIGHT).isEmpty();
        TextView tv = (TextView)findViewById(R.id.lightinfo);
        tv.setText(getString(R.string.light_status)+" " + getString(enabled ? R.string.txt_avail : R.string.txt_unavail));
        tv.setTextColor(enabled ? Color.GREEN : Color.RED);
        findViewById(R.id.button_light).setEnabled(enabled);

        enabled = !sm.getSensorList(Sensor.TYPE_ACCELEROMETER).isEmpty();
        tv = (TextView) findViewById(R.id.accelinfo);
        tv.setText(getString(R.string.accel_status)+" " + getString(enabled ? R.string.txt_avail : R.string.txt_unavail));
        tv.setTextColor(enabled ? Color.GREEN : Color.RED);
        findViewById(R.id.button_accel).setEnabled(enabled);

        enabled = !sm.getSensorList(Sensor.TYPE_AMBIENT_TEMPERATURE).isEmpty();
        tv = (TextView) findViewById(R.id.tempinfo);
        tv.setText(getString(R.string.temp_status)+" " + getString(enabled ? R.string.txt_avail : R.string.txt_unavail));
        tv.setTextColor(enabled ? Color.GREEN : Color.RED);
        findViewById(R.id.button_temp).setEnabled(enabled);

        enabled = !sm.getSensorList(Sensor.TYPE_GYROSCOPE).isEmpty();
        tv = (TextView) findViewById(R.id.gyroinfo);
        tv.setText(getString(R.string.gyro_status)+" " + getString(enabled ? R.string.txt_avail : R.string.txt_unavail));
        tv.setTextColor(enabled ? Color.GREEN : Color.RED);
        findViewById(R.id.button_gyro).setEnabled(enabled);

        enabled = !sm.getSensorList(Sensor.TYPE_PROXIMITY).isEmpty();
        tv = (TextView) findViewById(R.id.proxinfo);
        tv.setText(getString(R.string.prox_status)+" " + getString(enabled ? R.string.txt_avail : R.string.txt_unavail));
        tv.setTextColor(enabled ? Color.GREEN : Color.RED);
        findViewById(R.id.button_prox).setEnabled(enabled);

        enabled = !sm.getSensorList(Sensor.TYPE_MAGNETIC_FIELD).isEmpty();
        tv = (TextView) findViewById(R.id.magninfo);
        tv.setText(getString(R.string.magn_status)+" " + getString(enabled ? R.string.txt_avail : R.string.txt_unavail));
        tv.setTextColor(enabled ? Color.GREEN : Color.RED);
        findViewById(R.id.button_magn).setEnabled(enabled);

        enabled = !sm.getSensorList(Sensor.TYPE_PRESSURE).isEmpty();
        tv = (TextView) findViewById(R.id.pressinfo);
        tv.setText(getString(R.string.press_status)+" " + getString(enabled ? R.string.txt_avail : R.string.txt_unavail));
        tv.setTextColor(enabled ? Color.GREEN : Color.RED);
        findViewById(R.id.button_press).setEnabled(enabled);

        final LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        tv = (TextView) findViewById(R.id.gpsinfo);
        tv.setText(getString(R.string.gps_status)+" " + getString(enabled ? R.string.txt_avail : R.string.txt_unavail));
        tv.setTextColor(enabled ? Color.GREEN : Color.RED);
        findViewById(R.id.button_gps).setEnabled(enabled);

    }

    public final void startAktywnosc(final View v)
    {

        Intent in;
        if(v.getId() == R.id.button_gps) in = new Intent(this, GPS.class);
        else
        {
            in = new Intent(this, ASensor.class);
            if(v.getId() == R.id.button_light) in.putExtra("sensorType", Sensor.TYPE_LIGHT);
            else if(v.getId() == R.id.button_gyro) in.putExtra("sensorType", Sensor.TYPE_GYROSCOPE);
            else if(v.getId() == R.id.button_accel) in.putExtra("sensorType", Sensor.TYPE_ACCELEROMETER);
            else if(v.getId() == R.id.button_temp) in.putExtra("sensorType", Sensor.TYPE_AMBIENT_TEMPERATURE);
            else if(v.getId() == R.id.button_prox) in.putExtra("sensorType", Sensor.TYPE_PROXIMITY);
            else if(v.getId() == R.id.button_magn) in.putExtra("sensorType", Sensor.TYPE_MAGNETIC_FIELD);
            else if(v.getId() == R.id.button_press) in.putExtra("sensorType", Sensor.TYPE_PRESSURE);
        }
        startActivity(in);
    }
}
