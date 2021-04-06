package com.example.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.fragment.NavHostFragment;

import com.example.ui.databinding.NameFragmentBinding;

public class NameFragment extends Fragment {

    private NameFragmentBinding binding;

    public NameFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = NameFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.goToEmailFragmentButton.setOnClickListener(v -> {
            Bundle emailFragmentArgs = new EmailFragmentArgs.Builder().setName(binding.firstNameEditText.getText().toString())
                    .setSurname(binding.surnameEditText.getText().toString()).build().toBundle();
            NavHostFragment.findNavController(this).navigate(R.id.destination_email_fragment, emailFragmentArgs);
        });

        binding.firstNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.goToEmailFragmentButton.setEnabled(!binding.firstNameEditText.getText().toString().isEmpty() && !binding.surnameEditText.getText().toString().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        binding.surnameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.goToEmailFragmentButton.setEnabled(!binding.firstNameEditText.getText().toString().isEmpty() && !binding.surnameEditText.getText().toString().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}