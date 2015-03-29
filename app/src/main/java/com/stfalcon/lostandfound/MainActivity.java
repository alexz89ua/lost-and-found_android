package com.stfalcon.lostandfound;


import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends ActionBarActivity {
    public static String LOG_TAG = "my_log";

    private DrawerLayout mDrawer;
    private ListView mListView;
    private String[] menuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ParseTask().execute();
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mListView = (ListView) findViewById(R.id.left_drawer);
        menuItems = getResources().getStringArray(R.array.menu_items);

        mListView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, menuItems));


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment fragment=null;
                switch (position){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        fragment=new ProfileActivity();
                        break;
                }
                transaction.replace(R.id.content_fragment, fragment);
                transaction.commit();
                mDrawer.closeDrawers();
            }
        });

    }
////////////////////////////
    private class ParseTask extends AsyncTask<Void, Void, String> {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL("http://lost-and-found.dev.stfalcon.com/api/v1/faqs");

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                line = reader.readLine();
                buffer.append(line);

                resultJson = buffer.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultJson;
        }

        @Override
        protected void onPostExecute(String strJson) {
            super.onPostExecute(strJson);
            Log.d(LOG_TAG, strJson);

            JSONObject dataJsonObj = null;
            String question = "";
            String answer = "";
            String locale = "";
            String field = "";
            String content = "";
            try {
                dataJsonObj = new JSONObject(strJson);
                JSONArray faqs = dataJsonObj.getJSONArray("faqs");
                for (int i = 0; i < faqs.length(); i++) {

                    JSONObject faq = faqs.getJSONObject(i);
                    question=faq.getString("question");
                    answer=faq.getString("answer");
                    Log.d(LOG_TAG, "question: " + question);
                    Log.d(LOG_TAG, "answer: " + answer);
                    JSONArray translations = faq.getJSONArray("translations");
                    for(int j=0;j<translations.length();j++){
                        JSONObject translation =translations.getJSONObject(j);
                        locale=translation.getString("locale");
                        field=translation.getString("field");
                        content=translation.getString("content");
                        Log.d(LOG_TAG, "locale: " + locale);
                        Log.d(LOG_TAG, "field: " + field);
                        Log.d(LOG_TAG, "content: " + content);
                    }
                    Log.d(LOG_TAG, ": " + ":");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}

