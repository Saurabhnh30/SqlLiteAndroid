package com.example.sqlite_eg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    EditText txt_1 , txt_2 , txt_3;
    Button add,del,mod,view,view_all,show;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_1 = findViewById(R.id.txt_1);
        txt_2 = findViewById(R.id.txt_2);
        txt_3 = findViewById(R.id.txt_3);
        add = findViewById(R.id.add);
        del = findViewById(R.id.del);
        mod =  findViewById(R.id.mod);
        view = findViewById(R.id.view);
        view_all = findViewById(R.id.view_all);
        show = findViewById(R.id.show);

        final SQLiteDatabase db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(rollno VARCHAR,name VARCHAR,marks VARCHAR);");
        add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String usn = txt_1.getText().toString().trim();
                String name = txt_2.getText().toString().trim();
                String marks =  txt_3.getText().toString().trim();

                if (usn.isEmpty() || name.isEmpty() || marks.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Enter Values", Toast.LENGTH_LONG).show();
                }
                else
                {
                    db.execSQL("INSERT INTO student VALUES('" + txt_1.getText() + "','" + txt_2.getText() +"','" + txt_3.getText()+"')");
                    Toast.makeText(MainActivity.this,"Record Added",Toast.LENGTH_LONG).show();
                }
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor c_1 = db.rawQuery("SELECT * FROM student WHERE RollNo = '"+txt_1.getText()+"'",null);

                if (c_1.moveToFirst())
                {
                    txt_2.setText(c_1.getString(1));
                    txt_3.setText(c_1.getString(2));

                    // --
//                    Bundle bundle = new Bundle();
//                    bundle.putString("name", c_1.getString(1));
//                    bundle.putString("marks", c_1.getString(2));
//
//                    startActivity(new Intent(MainActivity.this, sss).putExtras(bundle));

                    // new actiivity
//                    Intent intent = getIntent();
//                    Bundle extras = intent.getExtras();
                    // name.setText(extras.getString("name");
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Invalid roll",Toast.LENGTH_LONG).show();
                }
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor c_1 = db.rawQuery("SELECT * FROM student WHERE RollNo = '" + txt_1.getText() + "'",null);

                if (c_1.moveToFirst())
                {
                    db.execSQL("DELETE FROM student WHERE rollno='" + txt_1.getText() + "'");
                    Toast.makeText(MainActivity.this,"Record Deleted",Toast.LENGTH_LONG).show();

                }
                else
                {
                    Toast.makeText(MainActivity.this,"Invalid roll",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
