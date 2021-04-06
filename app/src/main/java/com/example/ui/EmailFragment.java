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

import com.example.ui.databinding.EmailFragmentBinding;

import java.util.regex.Pattern;

public class EmailFragment extends Fragment {

    private EmailFragmentBinding binding;
    public static final String emailValidationRegex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    public EmailFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // Inflate the layout for this fragment
        binding = EmailFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.emailAddressEditText.setText(((MainHostActivity)getActivity()).getEmail());
        if(isValidEmail(binding.emailAddressEditText.getText().toString())){
            binding.goToPasswordFragmentButton.setEnabled(true);
        }

        binding.goToPasswordFragmentButton.setOnClickListener(v -> {
            Bundle passwordFragmentArgs = new PasswordFragmentArgs.Builder().setName(requireArguments().get("name").toString())
                    .setSurname(requireArguments().get("surname").toString())
                    .setEmail(binding.emailAddressEditText.getText().toString()).build().toBundle();
            NavHostFragment.findNavController(this).navigate(R.id.destination_password_fragment, passwordFragmentArgs);
        });

        binding.emailAddressEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(isValidEmail(binding.emailAddressEditText.getText().toString())){
                    binding.goToPasswordFragmentButton.setEnabled(true);
                }else{
                    binding.goToPasswordFragmentButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        binding.backToNameButton.setOnClickListener(v -> {
            ((MainHostActivity) getActivity()).setEmail(binding.emailAddressEditText.getText().toString());
            NavHostFragment.findNavController(this).navigate(R.id.action_email_fragment_pop);
        });
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }

    public boolean isValidEmail(String emailEntry){
        Pattern emailRegex = Pattern.compile(emailValidationRegex);
        if(emailEntry==null){
            return false;
        }
        return emailRegex.matcher(emailEntry).matches();
    }
}
