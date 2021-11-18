package com.example.myapplication2;

//***THIS FILE WILL SHOW ERRORS UNTIL YOU HAVE COMPLETED THE TASKS YOU ARE REQUIRED TO DO.  WHEN YOU HAVE
//SUCCESSFULLY FINISHED THE TASK THE ERRORS SHOULD ALL BE GONE.***

//androidx.appcompat.app.AppCompatActivity and android.os.Bundle are put in by default when basic
//activity selected when new project is created in Android Studio. All of the other imports where
//put in manually later during the making of the project.
//START
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;

import android.content.res.AssetManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication2.db.AppDatabase;
import com.example.myapplication2.db.Result;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
//END

public class ReadCSV extends AppCompatActivity implements View.OnClickListener{

    //Create objects which will be used to store the name of the files in the Asset Folder, the name
    //of the file that is in the EditText (the input box) when the user presses the Read CSV button,
    //an ArrayList of Strings for the data from the csv file, and a String to store the data from the
    //csv file after the data has been cleaned (put in a way that makes is look more readable when
    //you display it).
    //A String array call theAssetFiles, a String called fileName, an ArrayList of Strings called the
    //data, and a String called cleanData.
    //4 LINES
    //***START***
    private String[] theAssetFiles;
    private String fileName;
    private ArrayList<String> theData;
    private String cleanData;
    //***END***

    //Creating Java equivalent objects for the widgets in our activity which we created
    //in xml that we want to interact with (change) or give values to in some way.
    //START
    private TextView txtFilesHeading;
    private TextView txtAssetFiles;
    private EditText edtFileName;
    private Button btnReadCSV;
    private Button btnShowData;
    private Button btnExitPage;
    private Button btnSendData;
    private TextView txtFileData;
    private TextView txtPredData;
    private TextView txtDB;
    private Button btnUpdate;


    private OkHttpClient okHttpClient;

    private AppDatabase db;

    //END
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_c_s_v);

        db = Room.databaseBuilder(this, AppDatabase.class, "database").allowMainThreadQueries().build();
        okHttpClient = new OkHttpClient();

        //Connect our java objects to the appropriate widget in our user interface
        //START
        txtFilesHeading = findViewById(R.id.txtFilesHeading);
        txtAssetFiles = findViewById(R.id.txtAssetFiles);
        edtFileName = findViewById(R.id.edtFileName);
        btnReadCSV = findViewById(R.id.btnReadCSV);
        btnShowData = findViewById(R.id.btnShowData);
        btnExitPage = findViewById(R.id.btnExitPage);
        btnSendData = findViewById(R.id.btnSendPage);
        txtFileData = findViewById(R.id.txtFileData);
        txtPredData = findViewById(R.id.txtPredData);
        txtDB = findViewById(R.id.txtDB);
        btnUpdate = findViewById(R.id.btnUpdate);

        //END

        //We call the method getAssetList and then set what we receive back as the value of the
        //String[] (String Array) called theAssetFiles.
        //1 LINE
        //***START***
        theAssetFiles = getAssetList();
        //***END***

        //We use a StringBuilder to take each of the file names that are inside in our String[] theAssetFiles
        //(in our case there is only one file name) and put them all in one String. We then set that String
        //as the text that is shown by our TextView called txtAssetFiles.
        //START
        StringBuilder files = new StringBuilder();
        for (int i = 0; i < theAssetFiles.length; i++){
            files.append(theAssetFiles[i]);
            files.append("\n");
        }
        txtAssetFiles.setText(files);
        //END
        //This is setting the instructions of what to do when the button "Read CSV File" is pressed. When
        //we put "this" inside the brackets it is telling the system to use the main onClick method
        //for the onClickListener for this button. You only have one onClick that is not nested ie
        //the onClick is inside the setOnClickListener.
        btnReadCSV.setOnClickListener(this);
        //Set onClickListener to our Show File Data button which will set the cleanData String that we
        //created as what will be displayed in our TextView call txtFileData. It then makes this TexView
        //visible on our screen, and makes the Show File Data Button disappear from our screen.
        btnShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //We use a StringBuilder to take what is in the String cleanData and put in into a StringBulider
                //called list, we then set list as the text that is shown by our TextView called txtAssetFiles.
                //START
                StringBuilder list = new StringBuilder();
                list.append(cleanData);
                //From API 26 you could use the below line - String.join - to replace what is above this.  We have set
                //a minimum of API 16 for our app so we must use the longer methodology.
                //txtFileData.setText(String.join(" ", theAssetFiles));

                txtFileData.setText(list);
                //END
                //We are making changes to what widgets are visible on our screen.
                //START
                txtFileData.setVisibility(View.VISIBLE);
                btnShowData.setVisibility(View.GONE);
                //btnSendData.setVisibility(View.VISIBLE);
                //END
            }
        });


        // update DB
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < theAssetFiles.length; i++){
                    fileName = theAssetFiles[i];
                    Log.d("asset", fileName);
                    theData = readCSV();
                    cleanData = getCleanData(theData);
                    sendServer();

                }
            }
        });
        // update DB end

        //http
        btnSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendServer();
            }
        });


        //끝
        //Set on onClickListener to our "Back" button which will exit (and close) this activity and go back to the
        //launcher activity
        //START
        btnExitPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //we call the method called finish which sends the instruction to close the activity,
                //exit the page, and go back to the main activity. This is not a method you have to
                //create. It already exists in the library, you just have to call it.
                //1 LINE
                //***START***
                finish();
                //***END***
            }
        });
        //END
        //We making two widgets that are used when the user presses the button to show the data in the
        //file not visible when the activity is first launched.  We make them available again when the
        //user presses the Show Data button.
        //START
        btnShowData.setVisibility(View.GONE);
        btnSendData.setVisibility(View.GONE);
        txtFileData.setVisibility(View.GONE);
        //END

        for(int i = 0; i < theAssetFiles.length; i++){
            fileName = theAssetFiles[i];
            Log.d("asset", fileName);
            theData = readCSV();
            cleanData = getCleanData(theData);
            sendServer();
        }
        Intent intent = new Intent(ReadCSV.this, com.example.myapplication2.BarChartActivity.class);
        startActivity(intent);
    }

    //When the user clicks on the "Read CSV File" button this onClick is triggered.
    public void onClick(View v){
        //We check what is inside the EditText editFileName (the input box), we change it to a String
        //and then we set that as the value of the String fileName.
        //1 LINE
        //***START***
        fileName = edtFileName.getText().toString();
        //***END***
        //We call the method called readCSV which returns an ArrayList of STrings.  Whe the set that
        //to be the value of our ArrayList called theData.
        //1 LINE
        //***START***
        theData = readCSV();
        //***END***
        //We call the method called getCleanData and we send it the ArrayList of Strings called theData.
        //This method returned a String and this String is set as the value of our String cleanData.
        //1 LINE
        //***START***
        cleanData = getCleanData(theData);
        //***END
        //We make the button "Show File Data" visible so that the user can choose to look at the data
        //from the file if they want.
        btnShowData.setVisibility(View.VISIBLE);
        btnSendData.setVisibility(View.VISIBLE);

    }

    private void sendServer(){
        RequestBody formbody
                = new FormBody.Builder()
                .add("title", fileName)
                .add("csv", cleanData)
                .build();

        Request request = new Request.Builder().url("http://192.168.0.17:5000/debug")
                .post(formbody)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(
                    @NotNull Call call,
                    @NotNull IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "server down", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String str = response.body().string();
                txtPredData.setText(str);
                insertDB(fileName, str);

            }
        });
    }

    //This method uses the Asset Manager class in Android to get a list of all of the files inside
    //the folder called "Files" which is inside the Asset Folder.
    //For more information on Asset Manager go to https://developer.android.com/reference/android/content/res/AssetManager
    private String[] getAssetList(){
        Log.d("ASSETS", "getAssetList()");
        AssetManager assetManager = getAssets();
        String[] files = null;
        try {
            files = assetManager.list("Files");
            for(String str : files) { Log.d("ASSETS", str); }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
        }
        return files;
    }

    //This method reads all of the information from the file with the name that is saved in our String
    //fileName and then returs an ArrayList of Strings with the data. It also puts a Toast (pop up message)
    //on the screen of the app when the information has been read successfully.  Since a CSV file is
    //basically a type of text file this is mostly the same general method that we have used in other
    //projects to get information from text files.
    private ArrayList<String> readCSV(){
        Log.d("DATA", "readCSV");
        ArrayList<String> data = new ArrayList<>();
        AssetManager assetManager = getAssets();
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        String info = null;
        try {
            is = assetManager.open(("Files/" + fileName));
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            while ( (info = br.readLine()) != null ) {
                data.add(info);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        Toast.makeText(getBaseContext(), "Read File Successful",
                Toast.LENGTH_LONG).show();
        //for(String str : data) { Log.d("DATA", str); }
        return data;
    }

    //This method is sent an ArrayList of Strings, it uses a toString method to change this ArrayList
    //to a String. it then uses replace and replaceAll methods to make the String look the way we
    //want, that String is then set as the value of a String called dataCleaned.  This String is then
    //sent back to where the method was called.
    private String getCleanData(ArrayList<String> data){
        String dataRaw = data.toString();
        String dataCleaned = dataRaw.replaceAll(", ","\n")
                //.replaceAll(",","    ")
                .replace("[","")
                .replace("]","");
        return dataCleaned;
        //return dataRaw;
    }

    private void insertDB(String date, String score) {

        //날짜구하기
        Log.d("Database", date);
        String[] datearr = date.split("\\.");
        int date_ = Integer.parseInt(datearr[0]);
        Log.d("Database", Integer.toString(date_));





//      확률 구하기 - 15번째가 stressed 10번째가 nostress
        String n = score.replaceAll("\\s+|\\n",",");
        String[] scorearr = n.split(",");
//        for(int i=0;i<scorearr.length;i++) {
//            Log.d("DB", Integer.toString(i)+scorearr[i]);
//        }
//        Log.d("DB", n);
        Log.d("Database", Integer.toString(scorearr.length));

        int nostress = Integer.parseInt(scorearr[10]);
        int stressed = Integer.parseInt(scorearr[15]);
        Log.d("Database", Integer.toString(nostress));
        Log.d("Database", Integer.toString(stressed));
        float percent = ((float)stressed / (float)(nostress + stressed))*100;
        Log.d("Database", Float.toString(percent));
        String percent_ = String.format("%.1f", percent);
        Log.d("Database", percent_);

        String date2 = scorearr[31];
        String[] date2arr = date2.split("\\.");
        int date2_ = Integer.parseInt(date2arr[0]);

        //db insert
        Result result = new Result();
        result.date = date2_;
        result.percent = percent_;
        db.HRVDao().insert(result);
        fetchResult(20211000);
    }

    private void fetchResult(int num){
        List<Result> resultList = db.HRVDao().getResult(num);
        String resultText = "DB";
        for (Result result : resultList) {
            resultText += "\n" + result.date + ", " + result.percent;
        }
        StringBuilder list1 = new StringBuilder();
        list1.append(resultText);

        txtDB.setText(list1);
    }

}