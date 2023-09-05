package com.example.appautenticao;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appautenticao.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;

import kotlin.jvm.internal.PropertyReference0Impl;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();

        binding.textCadastro.setOnClickListener(v -> {
            startActivity(new Intent(this, CadastroActivity.class));
        });
        binding.textRecuperar.setOnClickListener( v ->
                startActivity(new Intent(this, RecuperaActivity.class)));
        binding.btnLogin.setOnClickListener(v -> validaDados());
    }

    private void validaDados() {
        String email = binding.editEmail.getText().toString().trim();
        String senha = binding.editSenha.getText().toString().trim();

        if (!email.isEmpty()) {
            if (!senha.isEmpty()) {
                loginFirebase(email, senha);
                binding.progressBar.setVisibility(View.VISIBLE);
            } else {
                Toast.makeText(this, "Informe uma senha", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Informe seu e-mail", Toast.LENGTH_LONG).show();
        }


        {
        }}
    private void loginFirebase(String email, String senha) {
        mAuth.signInWithEmailAndPassword(
                email, senha
        ).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                finish();
                startActivity(new Intent(this, MainActivity.class));
            } else {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
            }
        });
    }
    }

