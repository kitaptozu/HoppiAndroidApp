package com.kis2020.hoppi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kis2020.hoppi.model.Account;
import com.kis2020.hoppi.model.BrandOpportunity;
import com.kis2020.hoppi.model.Hoppi;
import com.kis2020.hoppi.task.JsonFetchTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {


    Hoppi hoppi;

    TextView currentBalance;
    TextView memberShipDate;
    TextView type;
    TextView sweetMoney;
    TextView countOfTransactions;

    Intent intent;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getHoppiInfo();

        currentBalance = findViewById(R.id.tv_current_balance);
        memberShipDate = findViewById(R.id.tv_member_ship_date);
        type = findViewById(R.id.tv_type);
        sweetMoney = findViewById(R.id.tv_sweet_money);
        countOfTransactions = findViewById(R.id.tv_count_of_transaction);

        displayInfo();

    }


    private void getHoppiInfo() {

        try {
            JsonFetchTask jsonFetchTask = new JsonFetchTask();
            jsonFetchTask.execute("https://kitaptozu.duckdns.org/AndroidOdev/datas");
            hoppi = jsonFetchTask.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void displayInfo(){
        Account account = hoppi.getAccount();
        int sizeOfTransactions = hoppi.getTransactions().size();
        currentBalance.setText(account.getCurentBalance());
        memberShipDate.setText(account.getMembershipDate());
        type.setText(account.getMembershipType());
        sweetMoney.setText(account.getTotalSweetMoney());
        countOfTransactions.setText(sizeOfTransactions+"");

    }

    public void onClick(View view) {
        ArrayList<BrandOpportunity> brandOpportunities = new ArrayList<>(hoppi.getBrandOpportunities());

        intent = new Intent(this,SecondActivity.class);
        bundle = new Bundle();
        bundle.putParcelableArrayList("brandOpportunities",brandOpportunities);
        intent.putExtras(bundle);
        startActivity(intent);








    }
}
