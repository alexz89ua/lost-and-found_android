package com.stfalcon.lostandfound.ui.fragments;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stfalcon.lostandfound.Faq;
import com.stfalcon.lostandfound.R;
import com.stfalcon.lostandfound.RecyclerAdapter;
import com.stfalcon.lostandfound.Translation;


public class FaqFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Faq> faqList;
    private ArrayList<Translation> translationList;
    private Faq faqObj;
    private Translation translationObj;
    private RecyclerAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_faq, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        faqList = new ArrayList<>();
        translationList = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity().getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Asyntask parseFaqJSON = new Asyntask();
        parseFaqJSON.execute();
    }

    public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
        private Drawable mDivider;

        public SimpleDividerItemDecoration(Context context) {
            mDivider = context.getResources().getDrawable(R.drawable.line_divider);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + mDivider.getIntrinsicHeight();

                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }

    private class Asyntask extends AsyncTask<Void, Void, String> {
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
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultJson;
        }

        @Override
        protected void onPostExecute(String strJson) {
            super.onPostExecute(strJson);

            JSONObject dataJsonObj = null;
            try {
                dataJsonObj = new JSONObject(strJson);
                JSONArray faqs = dataJsonObj.getJSONArray("faqs");
                for (int i = 0; i < faqs.length(); i++) {
                    faqObj = new Faq();

                    JSONObject faq = faqs.getJSONObject(i);
                    faqObj.setQuestion(faq.getString("question"));
                    faqObj.setAnswer(faq.getString("answer"));

                    JSONArray translations = faq.getJSONArray("translations");
                    for (int j = 0; j < translations.length(); j++) {
                        translationObj = new Translation();
                        JSONObject translation = translations.getJSONObject(i);
                        translationObj.setLocale(translation.getString("locale"));
                        translationObj.setField(translation.getString("field"));
                        translationObj.setContent(translation.getString("content"));
                        translationList.add(translationObj);
                    }
                    faqObj.setTranslationList(translationList);
                    faqList.add(faqObj);
                }

                mAdapter = new RecyclerAdapter(faqList);
                recyclerView.setAdapter(mAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
