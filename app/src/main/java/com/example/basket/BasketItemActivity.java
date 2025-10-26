package com.example.basket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import DAL.BasketDaMockup;
import DAL.BasketItem;

public class BasketItemActivity extends AppCompatActivity {

    private BasketDaMockup basketDaMockup;
    private EditText edtName;
    private EditText edtQuantity;
    private EditText edtPrice;
    private EditText edtCategory;
    private Button btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_basket_item);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        basketDaMockup=new BasketDaMockup();
        setViews();

    }

    private void setViews(){
        Intent intent=getIntent();
        int itemId = intent.getIntExtra("selectedItemId",-1);
        BasketItem item= basketDaMockup.findById(itemId);

        edtName= findViewById(R.id.edtName);
        edtQuantity= findViewById(R.id.edtQuantity);
        edtPrice= findViewById(R.id.edtPrice);
        edtCategory= findViewById(R.id.edtCategory);
        btnUpdate= findViewById(R.id.btnUpdate);

        if(item!=null) {
            edtName.setText(item.getName());
            edtQuantity.setText(item.getQuantity()+"");
            edtPrice.setText(item.getPrice() + "");
            edtCategory.setText(item.getCategory());
        }

        btnUpdate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String name= edtName.getText().toString();
                String quan= edtQuantity.getText().toString();
                String price= edtPrice.getText().toString();
                String category= edtCategory.getText().toString();

                if(name.trim().isEmpty() || quan.trim().isEmpty() || price.trim().isEmpty() || category.trim().isEmpty()){
                    Toast.makeText(BasketItemActivity.this, "Empty fields are not allowed", Toast.LENGTH_SHORT).show();
                }else{
                    item.setCategory(category);
                    item.setName(name);
                    try{
                        item.setPrice(Double.parseDouble(price));
                        item.setQuantity(Integer.parseInt(quan));
                    }catch (NumberFormatException e){
                        Toast.makeText(BasketItemActivity.this, "Enter numeric values for the quantity and price fields", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Intent intent = new Intent(BasketItemActivity.this, MainActivity.class);
                    setResult(RESULT_OK, intent);
                    startActivity(intent);
                    Toast.makeText(BasketItemActivity.this, "Item Updated Successfully", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}