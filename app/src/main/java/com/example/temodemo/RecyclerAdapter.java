package com.example.temodemo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Hanlder> {
    private List<GardBean> gardBeans;
    private Context context;

    public RecyclerAdapter(List<GardBean> gardBeans, Context context) {
        this.gardBeans = gardBeans;
        this.context = context;
    }

    @NonNull
    @Override
    public Hanlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context==null){
            context=parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_gardview, parent, false);
        final Hanlder hanlder = new Hanlder(view);
        hanlder.gardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=hanlder.getAdapterPosition();
                GardBean gardBean=gardBeans.get(position);
                Intent intent=new Intent(context,GardDateActivity.class);
                intent.putExtra(GardDateActivity.FRUIT_NAME,gardBean.getName());
                intent.putExtra(GardDateActivity.FRUIT_IMAGE_ID,gardBean.getImage());
                context.startActivity(intent);
            }
        });
        return hanlder;
    }

    @Override
    public void onBindViewHolder(@NonNull Hanlder holder, int position) {
        GardBean gardBean = gardBeans.get(position);
        holder.imageView.setImageResource(gardBean.getImage());
        holder.name.setText(gardBean.getName());
        Glide.with(context).load(gardBean.getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return gardBeans.size();
    }

    public class Hanlder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView name;
        private CardView gardView;
        public Hanlder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.tv_name);
            gardView=itemView.findViewById(R.id.carView);
        }
    }
}
