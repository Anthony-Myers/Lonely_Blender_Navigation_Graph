package com.example.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.ui.databinding.PasswordFragmentBinding;

public class PasswordFragment extends Fragment {

    PasswordFragmentBinding binding;

    public PasswordFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = PasswordFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.passwordEditText.setText(((MainHostActivity)getActivity()).getPassword());
        if(!binding.passwordEditText.getText().toString().isEmpty()){
            binding.goToCongratulationsButton.setEnabled(true);
        }

        binding.goToCongratulationsButton.setOnClickListener(v -> {
            Bundle congratulationsFragmentArgs = new CongratulationsFragmentArgs.Builder().setName(requireArguments().get("name").toString())
                    .setSurname(requireArguments().get("surname").toString())
                    .setEmail(getArguments().get("email").toString())
                    .setPassword(binding.passwordEditText.getText().toString()).build().toBundle();
            NavHostFragment.findNavController(this).navigate(R.id.destination_congratulations_fragment, congratulationsFragmentArgs);
        });

        binding.passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.goToCongratulationsButton.setEnabled(!binding.passwordEditText.getText().toString().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        binding.backToEmailButton.setOnClickListener(v -> {
            ((MainHostActivity) getActivity()).setPassword(binding.passwordEditText.getText().toString());
            NavHostFragment.findNavController(this).navigate(R.id.action_password_fragment_pop);
        });
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
