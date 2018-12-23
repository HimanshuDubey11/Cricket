package project.himanshu.com.cricket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ABC>{

    ArrayList<Data> dataArrayList;

    public DataAdapter(ArrayList<Data> dataArrayList) {
        this.dataArrayList = dataArrayList;
    }

    @Override
    public DataAdapter.ABC onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_data_adapter,null);
        return new ABC(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ABC holder, int position) {

        holder.name.setText(dataArrayList.get(position).name);
        holder.date.setText(dataArrayList.get(position).date);

    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    public class ABC extends RecyclerView.ViewHolder {
        TextView name;
        TextView date;
        public ABC(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.mycardtextview);
            date = itemView.findViewById(R.id.mycardtextviewdate);

        }
    }
}
