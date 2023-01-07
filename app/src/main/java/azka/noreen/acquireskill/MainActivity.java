package azka.noreen.acquireskill;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button,cancel;
    MyAsyncTask myAsyncTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                while(true);
                myAsyncTask=new MyAsyncTask();

                myAsyncTask.execute("Azka");
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAsyncTask.cancel(true);
            }
        });
    }
    private class MyAsyncTask extends AsyncTask<String,Integer,String> {
        @Override
        protected String doInBackground(String... strings) {
            String asd=strings[0];
//            Log.d("StringVal", asd);
            //for delay of 2ms, run in main so displayed on UI
            Handler handler=new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    handler.postDelayed(this,2000);
                    onProgressUpdate(12);

                    if(!isCancelled())
                    {
                        Toast.makeText(MainActivity.this, "This is running in runnable", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        handler.removeCallbacks(this);
                    }
                }
            },2000);
            return asd;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
//            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.d("progress", values[0]+"");
        }
    }


}
