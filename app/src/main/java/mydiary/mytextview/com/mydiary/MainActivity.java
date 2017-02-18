package mydiary.mytextview.com.mydiary;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity{

    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.EditText);
        button = (Button) findViewById(R.id.save);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void writeToFile(String myData){
        try{

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("diary.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(myData);
            outputStreamWriter.close(); //always close your stream.

        }
        catch(IOException e)
        {
            Log.v("MyActivity", e.toString());
        }
    }

    private String readFromFile(){
        String result = "";

        try{
            InputStream inputStream = openFileInput("diary.txt");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader();
                String tempString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while((tempString = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(tempString);
                }

                inputStream.close();
                result = stringBuilder.toString();
            }

        }catch(FileNotFoundException e){
            Log.v("MyActivity" + "File not found", e.toString());
        }catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}