package com.kis2020.hoppi.task;

import android.os.AsyncTask;

import com.kis2020.hoppi.model.Account;
import com.kis2020.hoppi.model.BrandOpportunity;
import com.kis2020.hoppi.model.Hoppi;
import com.kis2020.hoppi.model.Transaction;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JsonFetchTask extends AsyncTask<String ,String, Hoppi> {

    @Override
    protected Hoppi doInBackground(String... strings) {
        HttpURLConnection httpURLConnection = null;
        BufferedReader bufferedReader = null;
        Hoppi hoppi = null;


        try {
            URL url = new URL(strings[0]);

            httpURLConnection=(HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            InputStream inputStream= httpURLConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String lines;
            String hoppiFile="";
            while ((lines=bufferedReader.readLine())!=null){
                hoppiFile+=lines;

            }


            hoppi = hoppiJsonParsing(hoppiFile);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return hoppi;
    }


    @Override
    protected void onPostExecute(Hoppi hoppi) {
        super.onPostExecute(hoppi);
    }



    private Hoppi hoppiJsonParsing(String hoppiFile){
        Hoppi hoppi = null;
        try{

            JSONObject jsonObject = new JSONObject(hoppiFile);
            JSONObject accountJsonObject = jsonObject.getJSONObject("account");
            JSONArray brandOpportunitiesJsonArray = jsonObject.getJSONArray("brandOpportunities");
            JSONArray transactionsJsonArray = jsonObject.getJSONArray("transactions");

            Account account = new Account(accountJsonObject.getString("curentBalance"),accountJsonObject.getString("membershipDate"),accountJsonObject.getString("membershipType"),accountJsonObject.getString("totalSweetMoney"));

            List<BrandOpportunity> brandOpportunities = new ArrayList<>();
            for (int i = 0; i < brandOpportunitiesJsonArray.length(); i++){
                JSONObject brandOpportunityJsonObject = brandOpportunitiesJsonArray.getJSONObject(i);
                BrandOpportunity brandOpportunity = new BrandOpportunity(brandOpportunityJsonObject.getString("title"),brandOpportunityJsonObject.getString("typeCode"),brandOpportunityJsonObject.getString("discount"),brandOpportunityJsonObject.getString("sweetMoney"),brandOpportunityJsonObject.getString("lastDate"));
                brandOpportunities.add(brandOpportunity);
            }

            List<Transaction> transactions = new ArrayList<>();
            for (int i = 0; i < transactionsJsonArray.length(); i++){

                JSONObject transactionJsonObject = transactionsJsonArray.getJSONObject(i);
                Transaction transaction = new Transaction(transactionJsonObject.getString("name"),transactionJsonObject.getString("type"),transactionJsonObject.getString("date"),transactionJsonObject.getString("amount"));
                transactions.add(transaction);
            }
            hoppi = new Hoppi(account,brandOpportunities,transactions);


        }catch (Exception ex){
            ex.printStackTrace();
        }

        return hoppi;
    }
}
