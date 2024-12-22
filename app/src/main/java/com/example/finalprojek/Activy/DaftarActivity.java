package com.example.finalprojek.Activy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.finalprojek.R;
import com.example.finalprojek.databinding.ActivityDaftarBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class DaftarActivity extends BaseActivity {
    ActivityDaftarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDaftarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVariable();
    }

    private void setVariable() {
        binding.daftarBtn.setOnClickListener(v -> {
            String email=binding.userEdit.getText().toString();
            String password=binding.passEdit.getText().toString();

            if(password.length()<6){
                Toast.makeText(DaftarActivity.this, "password anda harus 6 kata", Toast.LENGTH_SHORT).show();
                return;
            }
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(DaftarActivity.this, task -> {
                if (task.isSuccessful()){
                    Log.i(TAG, "onComplete: ");
                    startActivity(new Intent(DaftarActivity.this, MainActivity.class));
                }else{
                    Log.i(TAG, "failure: "+task.getException());
                    Toast.makeText(DaftarActivity.this, "Autentikasi Gagal", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}