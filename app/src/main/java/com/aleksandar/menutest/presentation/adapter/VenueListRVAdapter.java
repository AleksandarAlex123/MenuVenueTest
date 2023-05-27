package com.aleksandar.menutest.presentation.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.aleksandar.menutest.data.model.VenueListApiResponse;
import com.aleksandar.menutest.databinding.VenueListItemBinding;

import java.util.ArrayList;

public class VenueListRVAdapter extends RecyclerView.Adapter<VenueListRVAdapter.VenueViewHolder> {

    private final ArrayList<VenueListApiResponse> venueList;
    private final ISelectedVenue iSelectedVenue;

    public VenueListRVAdapter(ArrayList<VenueListApiResponse> venueList, ISelectedVenue iSelectedVenue) {
        this.venueList = venueList;
        this.iSelectedVenue = iSelectedVenue;
    }

    @Override
    public VenueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        VenueListItemBinding itemBinding = VenueListItemBinding.inflate(layoutInflater, parent, false);
        return new VenueViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(VenueViewHolder holder, final int position) {
        final VenueListApiResponse venue = venueList.get(position);
        holder.bind(venue, iSelectedVenue);

    }

    @Override
    public int getItemCount() {
        return venueList.size();
    }

    public class VenueViewHolder extends RecyclerView.ViewHolder {
        private final VenueListItemBinding itemBinding;

        public VenueViewHolder(VenueListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void bind(VenueListApiResponse venue, ISelectedVenue iSelectedVenue) {
            itemBinding.setVenue(venue);
            itemBinding.setISelectedVenue(iSelectedVenue);
        }
    }

    public interface ISelectedVenue {
        void setSelectedVenue(VenueListApiResponse venue);
    }
}
