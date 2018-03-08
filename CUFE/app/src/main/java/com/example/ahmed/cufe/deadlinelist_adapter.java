package com.example.ahmed.cufe;

/**
 * Created by ahmed on 3/7/2018.
 */


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class deadlinelist_adapter extends RecyclerView.Adapter<deadlinelist_adapter.MyViewHolder> {
    private Context context;
    private List<Deadline> cartList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, description, type, DueDate, HoursAndDaysLeft;
        public ImageView thumbnail;
        private View thisView;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.Labels_Txt);
            description = view.findViewById(R.id.Description);
            type = view.findViewById(R.id.type);
            DueDate = view.findViewById(R.id.DueDate);
            HoursAndDaysLeft = view.findViewById(R.id.HoursAndDaysLeft);
            thumbnail = view.findViewById(R.id.thumbnail);

            thisView = view;
        }
    }


    public deadlinelist_adapter(Context context, List<Deadline> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.deadline_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Deadline deadline = cartList.get(position);
        holder.name.setText(deadline.getLabel());
       // holder.description.setText( deadline.getDescription());
        holder.description.setText( " ");
        holder.type.setText(deadline.getType());
        holder.DueDate.setText("Due: "+ deadline.getDueDate());
        holder.HoursAndDaysLeft.setText(deadline.getHoursBefore()+" Hours,and "+deadline.getDaysBefor()+" Days");
        holder.thumbnail.setImageResource(deadline.getThumbnail());
        //holder.thumbnail.setBackground(Drawable.createFromPath(deadline.getThumbnail()));


        if(deadline.getType().equals("Assignment"))
            holder.thisView.setBackgroundColor(Color.parseColor("#C3D9FF"));
          else if(deadline.getType().equals("Quiz"))
               holder.thisView.setBackgroundColor(Color.parseColor("#FFFFCC"));
        else if(deadline.getType().equals("Project"))
            holder.thisView.setBackgroundColor(Color.parseColor("#FFBBE8"));
        else
            holder.thisView.setBackgroundColor(Color.parseColor("#CAF99B"));

    }
    // recipe
    @Override
    public int getItemCount() {
        return cartList.size();
    }
}
