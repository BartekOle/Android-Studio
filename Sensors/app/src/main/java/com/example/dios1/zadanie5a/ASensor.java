package com.example.dios1.zadanie5a;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ASensor extends Activity implements SensorEventListener {

    private SensorManager mSrMgr = null;
    private int mSensorType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asensor);
        mSrMgr = (SensorManager)getSystemService(SENSOR_SERVICE);
        mSensorType = getIntent().getIntExtra("sensorType", Sensor.TYPE_LIGHT);
        if(mSensorType ==Sensor.TYPE_LIGHT) setTitle(R.string.light_status);
        else if(mSensorType == Sensor.TYPE_GYROSCOPE)
            setTitle(R.string.gyro_status);
        else if(mSensorType == Sensor.TYPE_ACCELEROMETER)
            setTitle(R.string.accel_status);
        else if(mSensorType == Sensor.TYPE_AMBIENT_TEMPERATURE)
            setTitle(R.string.temp_status);
        else if(mSensorType == Sensor.TYPE_PROXIMITY)
            setTitle(R.string.prox_status);
        else if(mSensorType == Sensor.TYPE_MAGNETIC_FIELD)
            setTitle(R.string.magn_status);
        else if(mSensorType == Sensor.TYPE_PRESSURE)
            setTitle(R.string.press_status);
    }

    @Override
    public void onResume() {
        super.onResume();
        final Sensor sens = mSrMgr.getSensorList(mSensorType).get(0);
        mSrMgr.registerListener(this, sens, SensorManager.SENSOR_DELAY_NORMAL);

    }
    @Override
    public void onPause() {
        super.onPause();
        mSrMgr.unregisterListener(this);


    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        TextView tv = (TextView) findViewById(R.id.txt_data);
        StringBuilder sb = new StringBuilder();
        if(mSensorType == Sensor.TYPE_LIGHT)
        {
            sb.append("Ambient light level: ");
            sb.append(event.values[0]);
            sb.append(" lux");
        }

        else if(mSensorType == Sensor.TYPE_GYROSCOPE)
        {
            sb.append("axisX Gyroscope: ");
            sb.append(String.format("%7.4f", event.values[0]));
            sb.append(" \naxisY Gyroscope: ");
            sb.append(String.format("%7.4f", event.values[1]));
            sb.append(" \naxisZ Gyroscope: ");
            sb.append(String.format("%7.4f", event.values[2]));
            sb.append(" \n");
        }

        else if(mSensorType == Sensor.TYPE_ACCELEROMETER)
        {
            sb.append("X acceleration: ");
            sb.append(String.format("%7.4f", event.values[0]));
            sb.append(" m/s\u00B2\nY acceleration: ");
            sb.append(String.format("%7.4f", event.values[1]));
            sb.append(" m/s\u00B2\nZ acceleration: ");
            sb.append(String.format("%7.4f", event.values[2]));
            sb.append(" m/s\u00B2\n");
        }

        else if(mSensorType == Sensor.TYPE_AMBIENT_TEMPERATURE)
        {
            sb.append("Temperatura wynosi: ");
            sb.append(event.values[0]);
            sb.append(" *C");
        }

        else if(mSensorType == Sensor.TYPE_PROXIMITY)
        {
            sb.append("Bliskość do urzadzenia wynosi: ");
            sb.append(event.values[0]);
            sb.append(" cm");
        }

        else if(mSensorType == Sensor.TYPE_PRESSURE)
        {
            sb.append("Cisnienie atmosferyczne wynosi: ");
            sb.append(event.values[0]);
            sb.append(" hPa");
        }

        else if(mSensorType == Sensor.TYPE_MAGNETIC_FIELD)
        {
            sb.append("X axis magnetic field: ");
            sb.append(String.format("%7.4f", event.values[0]));
            sb.append(" uT\nY axis magnetic field: ");
            sb.append(String.format("%7.4f", event.values[1]));
            sb.append(" uT\nZ axis magnetic field: ");
            sb.append(String.format("%7.4f", event.values[2]));
            sb.append(" uT\n");
        }

        tv.setText(sb);

        tv = (TextView)findViewById(R.id.txt_status);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("\nAccuracy: ");
        sb2.append(event.accuracy == 3 ? "High" : (event.accuracy == 2 ? "Medium" : "Low"));
        tv.setText(sb2);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
