package com.example.finalprojek.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalprojek.Adapter.CartAdapter;
import com.example.finalprojek.Helper.ChangeNumberItemsListener;
import com.example.finalprojek.Helper.ManagmentCart;
import com.example.finalprojek.R;
import com.example.finalprojek.databinding.ActivityCartBinding;

public class CartActivity extends BaseActivity {
    private ActivityCartBinding binding;
    private RecyclerView.Adapter adapter;
    private ManagmentCart managmentCart;
    private double tax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCartBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        managmentCart=new ManagmentCart(this);

        setVariable();
        calculateCart();
        initList();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initList() {
        if (managmentCart.getListCart().isEmpty()){
            binding.emptyTxt.setVisibility(View.VISIBLE);
            binding.scrollViewCart.setVisibility(View.GONE);
        }else{
            binding.emptyTxt.setVisibility(View.GONE);
            binding.scrollViewCart.setVisibility(View.VISIBLE);
        }

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        binding.cardView.setLayoutManager(linearLayoutManager);
        adapter=new CartAdapter(managmentCart.getListCart(), this, () -> calculateCart());
        binding.cardView.setAdapter(adapter);
    }

    private void calculateCart() {
        double percentTax=0.02; //percent 2% tax
        double delivery=10; // 10 Rupiah

        tax=Math.round(managmentCart.getTotalFee()*percentTax*100.0)/100;

        double total=Math.round((managmentCart.getTotalFee()+tax+delivery)*180)/100;
        double itemTotal=Math.round(managmentCart.getTotalFee()*100)/100;

        binding.totalFeeTxt.setText("$"+itemTotal);
        binding.taxTxt.setText("$"+tax);
        binding.deliveryTxt.setText("$"+delivery);
        binding.totalTxt.setText("$"+total);
    }

    private void setVariable() {
        binding.backBtn.setOnClickListener(v -> finish());
    }
}