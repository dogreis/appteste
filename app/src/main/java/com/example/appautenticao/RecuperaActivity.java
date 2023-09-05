package com.example.appautenticao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.appautenticao.databinding.ActivityRecuperaBinding;
import com.google.firebase.auth.FirebaseAuth;

public class RecuperaActivity extends AppCompatActivity {
   private ActivityRecuperaBinding binding;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityRecuperaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        binding.btnRecuperaConta.setOnClickListener(v -> validaDados());
    }
    private void validaDados(){
        String email = binding.editEmail.getText().toString().trim();


        if (!email.isEmpty()) {
           binding.progressBar.setVisibility(View.VISIBLE);
           recuperaContaFirebase(email);

        }else {
            Toast.makeText(this, "Informe seu e-mail", Toast.LENGTH_LONG).show();
        }
    }
    private void recuperaContaFirebase(String email){
        mAuth.sendPasswordResetEmail(
                email).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                finish();
               Toast.makeText(this, "Já pode verificar seu e-mail.", Toast.LENGTH_LONG).show();
            }else {

                Toast.makeText(this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
            }
            binding.progressBar.setVisibility(View.GONE);
        });
    }
}