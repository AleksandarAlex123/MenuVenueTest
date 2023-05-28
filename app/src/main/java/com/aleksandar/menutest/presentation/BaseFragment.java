package com.aleksandar.menutest.presentation;

import androidx.fragment.app.Fragment;

/**
 * This serves as a template for inheritance.
 * Any commonalities related to fragments should be extracted here
 */
public abstract class BaseFragment extends Fragment {

    // Fill Views and Listeners should be added in this method
    abstract void fillViewsAndListeners();

    // Live data observers should be added in this method
    abstract void setObservers();
}
