package android.basketball;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;

public class SimulationView extends View implements SensorEventListener{

    private SensorManager sensorManager;
    private Sensor sensor;
    private Display mDisplay;


    private Bitmap mField;
    private Bitmap mBasket;
    private Bitmap mBitMAP;
    private Bitmap field;
    Particle mBall = new Particle();
    private static final int BALL_SIZE = 64;
    private static final int BASKET_SIZE = 80;

    private float mXOrigin;
    private float mYOrigin;
    private float mHorizontalBound;
    private float mVerticalBound;


    private float mSensorX;
    private float mSensorY;
    private float mSensorZ;
    private long mSensorTimeStamp;


    public SimulationView(Context context) {
        super(context);

        // Initialize images from drawable
        Bitmap ball = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        mBitMAP = Bitmap.createScaledBitmap(ball, BALL_SIZE, BALL_SIZE, true);
        Bitmap basket = BitmapFactory.decodeResource(getResources(), R.drawable.basket);
        mBasket = Bitmap.createScaledBitmap(basket, BASKET_SIZE, BASKET_SIZE, true);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inPreferredConfig = Bitmap.Config.RGB_565;

        field = BitmapFactory.decodeResource(getResources(), R.drawable.field, opts);

        mField =  Bitmap.createScaledBitmap(field,
                Resources.getSystem().getDisplayMetrics().widthPixels,
                Resources.getSystem().getDisplayMetrics().heightPixels, true);

        WindowManager mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mDisplay = mWindowManager.getDefaultDisplay();

        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float X;
        float Y;
        float Z;

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            if (Surface.ROTATION_0 == mDisplay.getRotation())
            {
                mSensorX = event.values[0];
                mSensorY = event.values[1];
            }
            else if (Surface.ROTATION_90 == mDisplay.getRotation())
            {
                mSensorX = -event.values[1];
                mSensorY = event.values[0];
            }
            else if (Surface.ROTATION_180 == mDisplay.getRotation())
            {
                mSensorX = -event.values[0];
                mSensorY = -event.values[1];
            }
            else if (Surface.ROTATION_270 == mDisplay.getRotation())
            {
                mSensorX = event.values[1];
                mSensorY = -event.values[0];
            }
        }
        mSensorZ = event.values[2];
        mSensorTimeStamp = event.timestamp;
    }

    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        mXOrigin = (float) (w * 0.5);
        mYOrigin = (float) (h * 0.5);

        mHorizontalBound = (float) ((w - BALL_SIZE) * 0.5);
        mVerticalBound = (float) ((h - BALL_SIZE) * 0.5);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mField, 0, 0, null);
        canvas.drawBitmap(mBasket, mXOrigin - BASKET_SIZE / 2, mYOrigin - BASKET_SIZE / 2, null);

        mBall.updatePosition(mSensorX, mSensorY, mSensorZ, mSensorTimeStamp);
        mBall.resolveCollisionWithBounds(mHorizontalBound, mVerticalBound);

        canvas.drawBitmap(mBitMAP,
                (mXOrigin - BALL_SIZE / 2) + mBall.mPosX,
                (mYOrigin - BALL_SIZE / 2) - mBall.mPosY, null);

        invalidate();
    }


    public void startSimulation() {
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stopSimulation() {
        sensorManager.unregisterListener(this);
    }
}


