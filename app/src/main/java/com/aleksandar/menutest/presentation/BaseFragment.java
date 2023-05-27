package com.aleksandar.menutest.presentation;

import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    abstract void initViewsAndListeners();

    abstract void setObservers();
}
