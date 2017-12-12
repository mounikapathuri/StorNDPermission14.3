package com.android.mounika.storndpermission143;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button btnSave, btnCheck;
    TextView tv;
    String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv= (TextView) findViewById(R.id.tv);
        editText = (EditText) findViewById(R.id.edittext);
        btnSave = (Button) findViewById(R.id.btnSaveFile);
        btnCheck = (Button) findViewById(R.id.btnCheckFile);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //saving the file
                    FileOutputStream fileOutputStream = openFileOutput("myTextFile.txt", MODE_PRIVATE);
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                    outputStreamWriter.write(editText.getText().toString());
                    outputStreamWriter.close();
                    Toast.makeText(MainActivity.this, "File Saved Successfully..!!", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean res = isFileExists("myTextFile.txt");
                if (res){
                    Toast.makeText(MainActivity.this,"You clicked Check File : \nFile Found",Toast.LENGTH_SHORT).show();
                    tv.setText(path);
                }
                else {
                    tv.setText("");
                    Toast.makeText(MainActivity.this,"You clicked Check File : \nFile Not Found",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean isFileExists(String fileName){
        File file = getBaseContext().getFileStreamPath(fileName);
        //getting file path
        path = file.getAbsolutePath();
        tv.setText("File Path: "+path);
        return file.exists();
    }
}
