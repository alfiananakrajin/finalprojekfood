package com.example.finalprojek.Activity;

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
import com.example.finalprojek.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class LoginActivity extends BaseActivity {
ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVariable();

    }

    private void setVariable() {
        binding.loginBtn.setOnClickListener(v -> {
            String email=binding.userEdit.getText().toString();
            String password=binding.passEdit.getText().toString();
            if (!email.isEmpty() && !password.isEmpty()){
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, task -> {
                    if(task.isSuccessful()){
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }else{
                        Toast.makeText(LoginActivity.this, "Autentikasi Gagal", Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                Toast.makeText(LoginActivity.this, "Mohon tolong isi username dan password ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}