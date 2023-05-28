package com.aleksandar.menutest.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.aleksandar.menutest.R;
import com.aleksandar.menutest.data.model.VenueListApiResponse;
import com.aleksandar.menutest.data.util.AppConstant;
import com.aleksandar.menutest.data.util.Resource;
import com.aleksandar.menutest.databinding.FragmentVenueListBinding;
import com.aleksandar.menutest.presentation.adapter.VenueListRVAdapter;
import com.aleksandar.menutest.presentation.viewmodel.VenueViewModel;
import com.aleksandar.menutest.presentation.viewmodel.ViewModelFactory;

import org.parceler.Parcels;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class VenueListFragment extends BaseFragment implements VenueListRVAdapter.ISelectedVenue {

    @Inject
    ViewModelFactory viewModelFactory;

    private VenueViewModel venueViewModel;
    private FragmentVenueListBinding fragmentVenueListBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        venueViewModel = new ViewModelProvider(this, viewModelFactory).get(VenueViewModel.class);
        requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        venueViewModel.getVenueList(AppConstant.LATITUDE, AppConstant.LONGITUDE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentVenueListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_venue_list, container, false);
        setObservers();
        return fragmentVenueListBinding.getRoot();
    }

    @Override
    void setObservers() {
        venueViewModel.getVenueAPiResponseLiveData().observe(getViewLifecycleOwner(), venueListApiResponseResource -> {
            switch (venueListApiResponseResource.getType()) {
                case Resource.LOADING:
                    fragmentVenueListBinding.progressBar.setVisibility(View.VISIBLE);
                    break;
                case Resource.SUCCESS:
                    fragmentVenueListBinding.progressBar.setVisibility(View.GONE);
                 //   VenueListRVAdapter venueListRVAdapter = new VenueListRVAdapter(venueListApiResponses, this);
                   // fragmentVenueListBinding.venueRecyclerView.setAdapter(venueListRVAdapter);
                    break;
                case Resource.ERROR:
                    fragmentVenueListBinding.progressBar.setVisibility(View.GONE);
                    ArrayList<VenueListApiResponse> venueListApiResponsesMock = new ArrayList<>();
                    addMockVenuesData(venueListApiResponsesMock);
                    VenueListRVAdapter venueListRVAdapter = new VenueListRVAdapter(venueListApiResponsesMock, this);
                    fragmentVenueListBinding.venueRecyclerView.setAdapter(venueListRVAdapter);
                    Toast.makeText(VenueListFragment.this.getActivity(), venueListApiResponseResource.getError().getMessage(), Toast.LENGTH_LONG).show();
                    break;
            }
        });
    }

    @Override
    void fillViewsAndListeners() {}

    @Override
    public void onDestroy() {
        super.onDestroy();
        venueViewModel.clearDisposable();
    }

    private void addMockVenuesData(ArrayList<VenueListApiResponse> venueListApiResponses) {
        for (int i = 0; i < 15; i++) {
            VenueListApiResponse venueListApiResponse = new VenueListApiResponse();
            venueListApiResponse.setName("Ocean Drive Miami");
            venueListApiResponse.setDistance("230 m");
            venueListApiResponse.setAddress("2 Belgard Road, Tallaght, Miam");
            venueListApiResponse.setDate("Today 09:00 - 22:00");
            venueListApiResponse.setDescription("Poké Bar makes it easy to customize your bowl with endless toppings");
            venueListApiResponse.setWelcomeMessage("Welcome to Poke Bar");
            venueListApiResponse.setIsOpen(true);
            venueListApiResponses.add(venueListApiResponse);
        }
    }

    @Override
    public void setSelectedVenue(VenueListApiResponse venue) {
        Bundle args = new Bundle();
        args.putParcelable(AppConstant.VENUE_DETAILS, Parcels.wrap(venue));
        Navigation.findNavController(requireView()).navigate(R.id.action_venueListFragment_to_venueDetailsFragment, args);
    }
}
