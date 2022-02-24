package com.example.practice143;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.practice143.Adapters.CustomAdapter;
import com.example.practice143.Class.Utils;
import com.example.practice143.Models.Person;
import com.example.practice143.Models.Phone;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Person> personArrayList = new ArrayList<>();
    CustomAdapter customAdapter;
    ListView lstContactsInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstContactsInfo = findViewById(R.id.lstContactsInfo);
        customAdapter = new CustomAdapter(this, personArrayList);
        lstContactsInfo.setAdapter(customAdapter);

        //persons.add(new Person("1","wiaDevelopers","wia.Developers@gmail.com","Birjand - modarres",new Phone("+98-915***4550","","")));

        new getJson(getApplicationContext()).execute();
    }

    private class getJson extends AsyncTask<Void, Void, String> {

        Context context;

        public getJson(Context context) {
            this.context = context;
        }

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Please Wait");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            String Url = "https://run.mocky.io/v3/fc9a6b16-52cb-4062-8b85-912032de2f14";
            return Utils.getData(Url);
        }

        @Override
        protected void onPostExecute(String jsonStr) {
            progressDialog.dismiss();

            if (jsonStr != null) {
                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    JSONArray jsonArray = jsonObject.getJSONArray("contacts");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);

                        String id = object.getString("id");
                        String name = object.getString("name");
                        String email = object.getString("email");
                        String address = object.getString("address");

                        JSONObject phone = object.getJSONObject("phone");

                        String mobile = phone.getString("mobile");
                        String home = phone.getString("home");
                        String office = phone.getString("office");

                        personArrayList.add(new Person(id, name, email, address, new Phone(mobile, home, office)));
                    }

                    ((BaseAdapter)lstContactsInfo.getAdapter()).notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(context, "No Data", Toast.LENGTH_SHORT).show();
            }
        }
    }
}