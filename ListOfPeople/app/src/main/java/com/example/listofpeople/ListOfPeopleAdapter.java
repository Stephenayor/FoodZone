package com.example.listofpeople;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class ListOfPeopleAdapter extends RecyclerView.Adapter<ListOfPeopleAdapter.ListOfPeopleViewHolder> {

    private List<People>peopleList;
    private LayoutInflater layoutInflater;
    private Context mContext;

    public ListOfPeopleAdapter(List<People> peopleList,  Context context) {
        this.peopleList = peopleList;
        this.mContext = context;
        this.layoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ListOfPeopleViewHolder  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.people_list_item, parent, false);
        return new ListOfPeopleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListOfPeopleViewHolder holder, int position) {
        People people = peopleList.get(position);
        holder.nameOfPeople.setText(people.getName());
        holder.ageOfPeople.setText(people.getAge().toString());
    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }

    public class ListOfPeopleViewHolder extends RecyclerView.ViewHolder{

        public TextView nameOfPeople;
        public TextView ageOfPeople;
        public ListOfPeopleViewHolder(@NonNull View itemView) {
            super(itemView);

        nameOfPeople = itemView.findViewById(R.id.people_name);
        ageOfPeople = itemView.findViewById(R.id.people_age);

        }
    }
}
