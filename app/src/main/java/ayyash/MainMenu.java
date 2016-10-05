package ayyash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import ayyash.R;

public class MainMenu extends AppCompatActivity {

    Button sPagi,selinganPagi, makanSiang,selinganSiang,makanMalam,selinganMalam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        sPagi = (Button)findViewById(R.id.btnsarapanpagi);
        selinganPagi= (Button)findViewById(R.id.selinganPagi);
        makanSiang = (Button)findViewById(R.id.makanSiang);
        selinganSiang =(Button)findViewById(R.id.seliSiang);
        makanMalam = (Button)findViewById(R.id.makanMalam);
        selinganMalam = (Button)findViewById(R.id.selinganMala);


         /* Top toolbar */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_main);
        setSupportActionBar(toolbar);


        //makan pagi
        sPagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenu.this, SarapanActivity.class);
                startActivity(i);
            }
        });

    }
}
