package com.aleksandar.menutest.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.aleksandar.menutest.R;
import com.aleksandar.menutest.data.util.Resource;
import com.aleksandar.menutest.databinding.FragmentLoginBinding;
import com.aleksandar.menutest.presentation.viewmodel.LoginViewModel;
import com.aleksandar.menutest.presentation.viewmodel.ViewModelFactory;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends Fragment {

    @Inject
    ViewModelFactory viewModelFactory;

    private LoginViewModel loginViewModel;
    private FragmentLoginBinding fragmentLoginBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = new ViewModelProvider(this, viewModelFactory).get(LoginViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        if (loginViewModel.isUserLoggedIn()) {
            Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_venueListFragment);
        }
        initViewsAndListeners();
        setObservers();
        return fragmentLoginBinding.getRoot();
    }

    void initViewsAndListeners() {
        fragmentLoginBinding.signInButton.setOnClickListener(view -> {

            String inputEmail = fragmentLoginBinding.emailEditTxt.getText().toString();
            String inputPassword = fragmentLoginBinding.passwordEditTxt.getText().toString();

            if (!inputEmail.isEmpty() && !inputPassword.isEmpty()) {
                loginViewModel.login(inputEmail, inputPassword);
            } else {
                Toast.makeText(getActivity(), getString(R.string.please_input_email_and_password), Toast.LENGTH_LONG).show();
            }
        });
    }

    void setObservers() {
        loginViewModel.getLoginAPiResponseLiveData().observe(getViewLifecycleOwner(), loginAPiResponseResource -> {
            switch (loginAPiResponseResource.getType()) {
                case Resource.LOADING:
                    fragmentLoginBinding.progressBar.setVisibility(View.VISIBLE);
                    break;
                case Resource.SUCCESS:
                    fragmentLoginBinding.progressBar.setVisibility(View.GONE);
                    Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_venueListFragment);
                    break;
                case Resource.ERROR:
                    fragmentLoginBinding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginFragment.this.getActivity(), loginAPiResponseResource.getError().getMessage(), Toast.LENGTH_LONG).show();
                    Navigation.findNavController(LoginFragment.this.requireView()).navigate(R.id.action_loginFragment_to_venueListFragment);
                    break;
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        loginViewModel.clearDisposable();
    }
}
