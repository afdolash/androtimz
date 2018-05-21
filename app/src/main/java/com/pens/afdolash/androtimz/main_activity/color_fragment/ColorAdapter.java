package com.pens.afdolash.androtimz.main_activity.color_fragment;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.pens.afdolash.androtimz.R;
import com.pens.afdolash.androtimz.main_activity.ColorData;
import com.pens.afdolash.androtimz.main_activity.MainActivity;

import java.util.List;

/**
 * Created by afdol on 4/1/2018.
 */

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.MyViewHolder> {

    private ColorFragment context;
    private List<ColorData> colors;
    private RelativeLayout rvBackground, rvBackground2;

    public ColorAdapter(ColorFragment context, List<ColorData> colors) {
        this.context = context;
        this.colors = colors;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_color, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ColorData color = colors.get(position);

        holder.imgColor.setImageTintList(ColorStateList.valueOf(Color.parseColor(color.getHexCode())));
        holder.rvColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.getRvBackground().setBackgroundColor(Color.parseColor(color.getHexCode()));
                context.getRvBackground2().setBackgroundColor(Color.parseColor(color.getHexCode()));
                context.getImgMore().setImageTintList(ColorStateList.valueOf(Color.parseColor(color.getTextColor())));
                context.getTvColorName().setText(color.getName());
                context.getTvColorHex().setText(color.getHexCode());
                context.getTvColorName().setTextColor(Color.parseColor(color.getTextColor()));
                context.getTvColorHex().setTextColor(Color.parseColor(color.getTextColor()));
                context.getTvTitle().setTextColor(Color.parseColor(color.getTextColor()));

                MainActivity.CHOOSEN_COLOR = color;
            }
        });
    }

    @Override
    public int getItemCount() {
        return colors.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout rvColor;
        public ImageView imgColor;

        public MyViewHolder(View itemView) {
            super(itemView);
            rvColor = (RelativeLayout) itemView.findViewById(R.id.rv_color);
            imgColor = (ImageView) itemView.findViewById(R.id.img_color);
        }
    }
}
