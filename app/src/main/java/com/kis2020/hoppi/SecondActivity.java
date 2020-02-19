package com.kis2020.hoppi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kis2020.hoppi.adapter.RecyclerViewAdapter;
import com.kis2020.hoppi.database.BrandOpportunityDB;
import com.kis2020.hoppi.database.DatabaseHelper;
import com.kis2020.hoppi.model.BrandOpportunity;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements RecyclerViewAdapter.OnOpportunityListiner {

    ArrayList<BrandOpportunity> brandOpportunityArrayList;
    RecyclerView rvOpportunities;

    Intent intent;
    Bundle bundle;

    Dialog customDialog;
    TextView tvTitleDialog;
    TextView tvTypeCodeDialog;
    TextView tvDiscountDialog;
    TextView tvSweetMoney;
    TextView tvLastDate;
    ImageView saveDeleteImage;

    Button button;

    DatabaseHelper dbHelper;

    BrandOpportunity brandOpportunity;
    List<BrandOpportunity> opportunitiesFromDb;

    RecyclerViewAdapter recyclerViewAdapter;

    boolean isFavoritesOpen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        rvOpportunities = findViewById(R.id.rv_opportunities);
        button = findViewById(R.id.btn_favorites);

        getBrandOpportunities();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvOpportunities.setLayoutManager(linearLayoutManager);

        recyclerViewAdapter = new RecyclerViewAdapter(this, brandOpportunityArrayList, this);
        rvOpportunities.setAdapter(recyclerViewAdapter);

        dbHelper = new DatabaseHelper(this);

        isFavoritesOpen = false;


    }

    private void getBrandOpportunities() {
        intent = getIntent();
        bundle = intent.getExtras();
        brandOpportunityArrayList = bundle.getParcelableArrayList("brandOpportunities");
    }

    private void setRecyclerViewAdapter(ArrayList<BrandOpportunity> brandOpportunities) {
        recyclerViewAdapter = new RecyclerViewAdapter(this, brandOpportunities, this);
        rvOpportunities.setAdapter(recyclerViewAdapter);


    }

    public void dialogDisplay(int position, ArrayList<BrandOpportunity> brandOpportunities) {
        customDialog = new Dialog(this);
        customDialog.setContentView(R.layout.dialog);

        tvTitleDialog = customDialog.findViewById(R.id.tv_title_dialog);
        tvTypeCodeDialog = customDialog.findViewById(R.id.tv_type_code_dialog);
        tvDiscountDialog = customDialog.findViewById(R.id.tv_discount_dialog);
        tvSweetMoney = customDialog.findViewById(R.id.tv_sweet_money_dialog);
        tvLastDate = customDialog.findViewById(R.id.tv_last_date_dialog);
        saveDeleteImage = customDialog.findViewById(R.id.iv_save_delete_dialog);

        final BrandOpportunity brandOpportunity = brandOpportunities.get(position);
        tvTitleDialog.setText(brandOpportunity.getTitle());
        tvTypeCodeDialog.setText(brandOpportunity.getTypeCode());
        tvDiscountDialog.setText(brandOpportunity.getDiscount());
        tvSweetMoney.setText(brandOpportunity.getSweetMoney());
        tvLastDate.setText(brandOpportunity.getLastDate());

        saveDeleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!BrandOpportunityDB.isBrandOpportunityExist(dbHelper, brandOpportunity)) {
                    BrandOpportunityDB.insertBrandOpportunity(dbHelper, brandOpportunity);
                    Toast.makeText(customDialog.getContext(), "inserted the record", Toast.LENGTH_SHORT).show();
                } else {
                    BrandOpportunityDB.deleteBrandOpportunity(dbHelper, brandOpportunity);
                    Toast.makeText(customDialog.getContext(), "Deleted the record", Toast.LENGTH_SHORT).show();
                }
                if (isFavoritesOpen) {
                    opportunitiesFromDb = BrandOpportunityDB.getAllBrandOpportunity(dbHelper);
                    setRecyclerViewAdapter(new ArrayList<BrandOpportunity>(opportunitiesFromDb));

                }


            }
        });
        customDialog.show();
    }


    @Override
    public void onOpportunityClick(int position) {
        if (isFavoritesOpen) {
            brandOpportunity = opportunitiesFromDb.get(position);
            dialogDisplay(position, new ArrayList<BrandOpportunity>(opportunitiesFromDb));
        } else {
            brandOpportunity = brandOpportunityArrayList.get(position);
            dialogDisplay(position, brandOpportunityArrayList);
        }

    }

    public void onClick(View view) {


        if (isFavoritesOpen) {
            setRecyclerViewAdapter(brandOpportunityArrayList);
            isFavoritesOpen = false;
            button.setText("Favorites");
        } else {
            opportunitiesFromDb = BrandOpportunityDB.getAllBrandOpportunity(dbHelper);
            setRecyclerViewAdapter(new ArrayList<BrandOpportunity>(opportunitiesFromDb));
            isFavoritesOpen = true;
            button.setText("Opportunities");
        }


    }
}
