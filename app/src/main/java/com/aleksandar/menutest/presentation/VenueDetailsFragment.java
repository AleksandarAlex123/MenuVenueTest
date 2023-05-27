package com.aleksandar.menutest.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.aleksandar.menutest.R;
import com.aleksandar.menutest.data.model.VenueListApiResponse;
import com.aleksandar.menutest.data.util.AppConstant;
import com.aleksandar.menutest.databinding.FragmentVenueDetailsBinding;
import com.aleksandar.menutest.presentation.viewmodel.VenueViewModel;
import com.aleksandar.menutest.presentation.viewmodel.ViewModelFactory;
import com.bumptech.glide.Glide;

import org.parceler.Parcels;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class VenueDetailsFragment extends BaseFragment {

    @Inject
    ViewModelFactory viewModelFactory;

    private VenueViewModel venueViewModel;
    private FragmentVenueDetailsBinding fragmentVenueDetailsBinding;
    private VenueListApiResponse venue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        venueViewModel = new ViewModelProvider(this, viewModelFactory).get(VenueViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentVenueDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_venue_details, container, false);
        initViewsAndListeners();
        setObservers();
        return fragmentVenueDetailsBinding.getRoot();
    }

    @Override
    void initViewsAndListeners() {

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(AppConstant.VENUE_DETAILS)) {
            venue = Parcels.unwrap(bundle.getParcelable(AppConstant.VENUE_DETAILS));
            fragmentVenueDetailsBinding.setVenue(venue);
            Glide.with(this).load(venue.getThumbnailMedium())
                    .placeholder(R.drawable.img_placeholder)
                    .into(fragmentVenueDetailsBinding.venueImg);
        }

        fragmentVenueDetailsBinding.logoutButton.setOnClickListener(view -> {
                    venueViewModel.logOut();
                    Navigation.findNavController(requireView()).navigate(R.id.action_venueDetailsFragment_to_loginFragment);
                }
        );
    }

    @Override
    void setObservers() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        venueViewModel.clearDisposable();
    }
}
