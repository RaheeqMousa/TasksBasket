package com.example.basket;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import DAL.BasketDaMockup;
import DAL.BasketItem;

public class MainActivity extends AppCompatActivity {

    private BasketDaMockup basketDaMockup;
    private ListView lstItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        basketDaMockup=new BasketDaMockup();
        setViews();
        setBasketListItems();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK) {
            ArrayAdapter<BasketItem> adapter = (ArrayAdapter<BasketItem>) lstItems.getAdapter();
            adapter.notifyDataSetChanged();
        }
    }

    private void setBasketListItems(){
        ArrayAdapter<BasketItem> adapter= new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, basketDaMockup.getAllItems());
        lstItems.setAdapter(adapter);
        lstItems.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BasketItem selectedItem= (BasketItem) parent.getItemAtPosition(position);
                Intent intent=new Intent(MainActivity.this, BasketItemActivity.class);
                intent.putExtra("selectedItemId",selectedItem.getId());
                startActivityForResult(intent, 100);
            }
        });
//        lstItems.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, basketDaMockup.getAllItems()) {
//            @NonNull
//            @Override
//            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
//                TextView view = (TextView) super.getView(position, convertView, parent);
//                view.setTypeface(Typeface.MONOSPACE);
//                return view;
//            }
//        });
    }

    private void setViews(){
        lstItems=findViewById(R.id.lstItems);
    }

}