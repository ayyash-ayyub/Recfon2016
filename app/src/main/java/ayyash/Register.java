package ayyash;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ayyash.R;

public class Register extends Activity {

//    private DatePicker datePicker;
//    private Calendar calendar;
//
//    private int year, month, day;
//
//    private EditText calendarTxt;

    Typeface fonts1,fonts2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button next = (Button)findViewById(R.id.btnNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Register2.class);
                startActivity(i);
            }
        });


//        fonts1 =  Typeface.createFromAsset(Register.this.getAssets(),
//                "fonts/OpenSans-Regular.ttf");
//
//
//
//        fonts2 =  Typeface.createFromAsset(Register.this.getAssets(),
//                "fonts/OpenSans-Semibold.ttf");


//        TextView t =(TextView)findViewById(R.id.name1);
//        t.setTypeface(fonts2);



    }
}