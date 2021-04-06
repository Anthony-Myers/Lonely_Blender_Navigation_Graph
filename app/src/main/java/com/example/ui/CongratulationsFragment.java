package com.example.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.ui.databinding.CongratulationsFragmentBinding;

public class CongratulationsFragment extends Fragment {

    private CongratulationsFragmentBinding binding;

    public CongratulationsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = CongratulationsFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.nameFinalEditText.setText(getArguments().get("name").toString());
        binding.nameFinalEditText.setEnabled(false);
        binding.surnameEt.setText(getArguments().get("surname").toString());
        binding.surnameEt.setEnabled(false);
        binding.emailFinalEditText.setText(getArguments().get("email").toString());
        binding.emailFinalEditText.setEnabled(false);
        binding.passwordFinalEditText.setText(getArguments().get("password").toString());
        binding.passwordFinalEditText.setEnabled(false);

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                returnToWelcomeFragment();
            }
        });

        binding.backToWelcomeButton.setOnClickListener(v -> {
            returnToWelcomeFragment();
        });
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }

    private void returnToWelcomeFragment() {
        NavHostFragment.findNavController(this).navigate(R.id.action_congratulations_fragment_pop);
    }
}
