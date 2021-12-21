package com.example.kugangka.myapplication;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Images;
import android.support.v4.app.ActivityCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GetSignature extends Activity {

    LinearLayout mContent;
    signature mSignature;
    Button mClear, mGetSign, mCancel;
    TextView test;

    public String current = null;
    private Bitmap mBitmap;
    View mView;
    File mypath;

    String DIRECTORY = Environment.getExternalStorageDirectory().getPath() + "/UserSignature/";
    String pic_name = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
    String StoredPath = DIRECTORY + pic_name + ".png";

    private EditText yourName;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_get_signature);

        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        mypath= new File(DIRECTORY);
        if (!mypath.exists()) {
            mypath.mkdir();
        }

        mContent = (LinearLayout) findViewById(R.id.linearLayout);
        mSignature = new signature(this, null);
        mSignature.setBackgroundColor(Color.WHITE);
        mContent.addView(mSignature, LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
        mClear = (Button)findViewById(R.id.clear);
        mGetSign = (Button)findViewById(R.id.getsign);
        mGetSign.setEnabled(false);
        mCancel = (Button)findViewById(R.id.cancel);
        mView = mContent;

        test = findViewById(R.id.textView3);

        yourName = (EditText) findViewById(R.id.yourName);



        Intent intent = getIntent();

        final int ID = intent.getIntExtra("ID", 0);
        final String value1 = intent.getStringExtra("EnumName");
        final String value2 = intent.getStringExtra("date_1");
        final String value4 = intent.getStringExtra("team_supervisor");
        final String value5 = intent.getStringExtra("date_2");
        final String value7 = intent.getStringExtra("cen_area_super");
        final String value8 = intent.getStringExtra("date_3");
        final String value10 = intent.getStringExtra("co_rsso_po");
        final String value11 = intent.getStringExtra("date_4");
        final String value14 = intent.getStringExtra("imagePath2");
        final String value15 = intent.getStringExtra("imagePath3");
        final String value16 = intent.getStringExtra("imagePath4");

        final String value17 = getIntent().getStringExtra("qr_code_number");

        final int geo_iden_id = getIntent().getIntExtra("geo_iden_id",0);

        final String value18 = intent.getStringExtra("update");

        yourName.setText(value1);



        mClear.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                Log.v("log_tag", "Panel Cleared");
                mSignature.clear();
                mGetSign.setEnabled(false);
            }
        });

        mGetSign.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {

                try{
                    if(value18.equals("update")){
                        Log.v("log_tag", "Panel Saved");

                        mView.setDrawingCacheEnabled(true);
                        String name = yourName.getText().toString();

                        mSignature.save(mView, StoredPath);
                        Intent intent = new Intent(GetSignature.this, Household_Certification.class);

                        intent.putExtra("ID", ID);
                        intent.putExtra("imagePath", StoredPath);
                        intent.putExtra("EnumName", value1);
                        intent.putExtra("date_1", value2);
                        intent.putExtra("team_supervisor", value4);
                        intent.putExtra("date_2", value5);
                        intent.putExtra("cen_area_super", value7);
                        intent.putExtra("date_3", value8);
                        intent.putExtra("co_rsso_po", value10);
                        intent.putExtra("date_4", value11);
                        intent.putExtra("imagePath2", value14);
                        intent.putExtra("imagePath3", value15);
                        intent.putExtra("imagePath4", value16);
                        intent.putExtra("qr_code_number", value17);
                        intent.putExtra("geo_iden_id", geo_iden_id);


                        startActivity(intent);
                        finish();
                        recreate();
                    }

                }catch (Exception ex){

                    Log.v("log_tag", "Panel Saved");

                    mView.setDrawingCacheEnabled(true);
                    String name = yourName.getText().toString();

                    mSignature.save(mView, StoredPath);
                    Intent intent = new Intent(GetSignature.this, CertificationForm.class);
                    intent.putExtra("imagePath", StoredPath);
                    intent.putExtra("EnumName", value1);
                    intent.putExtra("date_1", value2);
                    intent.putExtra("team_supervisor", value4);
                    intent.putExtra("date_2", value5);
                    intent.putExtra("cen_area_super", value7);
                    intent.putExtra("date_3", value8);
                    intent.putExtra("co_rsso_po", value10);
                    intent.putExtra("date_4", value11);
                    intent.putExtra("imagePath2", value14);
                    intent.putExtra("imagePath3", value15);
                    intent.putExtra("imagePath4", value16);
                    intent.putExtra("qr_code_number", value17);
                    intent.putExtra("geo_iden_id", geo_iden_id);
                    intent.putExtra("SQ", "SF");
                    startActivity(intent);
                    finish();
                    recreate();
                }


            }


        });

        mCancel.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                Log.v("log_tag", "Panel Canceled");
                Bundle b = new Bundle();
                b.putString("status", "cancel");
                Intent intent = new Intent();
                intent.putExtras(b);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
    @Override
    protected void onDestroy() {
        Log.w("GetSignature", "onDestory");
        super.onDestroy();
    }

    private boolean captureSignature() {

        boolean error = false;
        String errorMessage = "";


        if(yourName.getText().toString().equalsIgnoreCase("")){
            errorMessage = errorMessage + "Please enter your Name\n";
            error = true;
        }

        if(error){
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 105, 50);
            toast.show();
        }

        return error;
    }

    public class signature extends View
    {
        private static final float STROKE_WIDTH = 5f;
        private static final float HALF_STROKE_WIDTH = STROKE_WIDTH / 2;
        private Paint paint = new Paint();
        private Path path = new Path();

        private float lastTouchX;
        private float lastTouchY;
        private final RectF dirtyRect = new RectF();

        public signature(Context context, AttributeSet attrs)
        {
            super(context, attrs);
            paint.setAntiAlias(true);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeWidth(STROKE_WIDTH);
        }

        public void save(View v, String StoredPath) {
            Log.v("log_tag", "Width: " + v.getWidth());
            Log.v("log_tag", "Height: " + v.getHeight());
            if (mBitmap == null) {
                mBitmap = Bitmap.createBitmap(mContent.getWidth(), mContent.getHeight(), Bitmap.Config.RGB_565);
            }
            Canvas canvas = new Canvas(mBitmap);
            try {
                // Output the file
                FileOutputStream mFileOutStream = new FileOutputStream(StoredPath);
                v.draw(canvas);

                // Convert the output file to Image such as .png
                mBitmap.compress(Bitmap.CompressFormat.PNG, 90, mFileOutStream);

                mFileOutStream.flush();
                mFileOutStream.close();


            } catch (Exception e) {
                Log.v("log_tag", e.toString());
            }

        }

        public void clear()
        {
            path.reset();
            invalidate();
        }

        @Override
        protected void onDraw(Canvas canvas)
        {
            canvas.drawPath(path, paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event)
        {
            float eventX = event.getX();
            float eventY = event.getY();
            mGetSign.setEnabled(true);

            switch (event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(eventX, eventY);
                    lastTouchX = eventX;
                    lastTouchY = eventY;
                    return true;

                case MotionEvent.ACTION_MOVE:

                case MotionEvent.ACTION_UP:

                    resetDirtyRect(eventX, eventY);
                    int historySize = event.getHistorySize();
                    for (int i = 0; i < historySize; i++)
                    {
                        float historicalX = event.getHistoricalX(i);
                        float historicalY = event.getHistoricalY(i);
                        expandDirtyRect(historicalX, historicalY);
                        path.lineTo(historicalX, historicalY);
                    }
                    path.lineTo(eventX, eventY);
                    break;

                default:
                    debug("Ignored touch event: " + event.toString());
                    return false;
            }

            invalidate((int) (dirtyRect.left - HALF_STROKE_WIDTH),
                    (int) (dirtyRect.top - HALF_STROKE_WIDTH),
                    (int) (dirtyRect.right + HALF_STROKE_WIDTH),
                    (int) (dirtyRect.bottom + HALF_STROKE_WIDTH));

            lastTouchX = eventX;
            lastTouchY = eventY;

            return true;
        }

        private void debug(String string){
        }

        private void expandDirtyRect(float historicalX, float historicalY)
        {
            if (historicalX < dirtyRect.left)
            {
                dirtyRect.left = historicalX;
            }
            else if (historicalX > dirtyRect.right)
            {
                dirtyRect.right = historicalX;
            }

            if (historicalY < dirtyRect.top)
            {
                dirtyRect.top = historicalY;
            }
            else if (historicalY > dirtyRect.bottom)
            {
                dirtyRect.bottom = historicalY;
            }
        }

        private void resetDirtyRect(float eventX, float eventY)
        {
            dirtyRect.left = Math.min(lastTouchX, eventX);
            dirtyRect.right = Math.max(lastTouchX, eventX);
            dirtyRect.top = Math.min(lastTouchY, eventY);
            dirtyRect.bottom = Math.max(lastTouchY, eventY);
        }
    }
}