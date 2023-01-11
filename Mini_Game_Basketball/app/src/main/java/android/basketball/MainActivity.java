package android.basketball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "android.basketball:MainActivity";
    private PowerManager.WakeLock mWakeLock;

    // The view
    private SimulationView mSimulationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PowerManager mPowerManager = (PowerManager) getSystemService(POWER_SERVICE);
        mWakeLock = mPowerManager.newWakeLock(
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, TAG);
        mSimulationView = new SimulationView(this);
        // Set to the simulation view instead of layout file.
        setContentView(mSimulationView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // acquire wakelock
        mWakeLock.acquire();
        // start simulation to register the listener
        mSimulationView.startSimulation();
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Release wakelock
        mWakeLock.release();
        // stop simulation to unregister the listener
        mSimulationView.stopSimulation();
    }
}