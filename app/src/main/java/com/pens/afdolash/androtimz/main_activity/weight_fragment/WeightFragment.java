package com.pens.afdolash.androtimz.main_activity.weight_fragment;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pens.afdolash.androtimz.R;
import com.pens.afdolash.androtimz.main_activity.MainActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeightFragment extends Fragment {

    private TextView tvTitle, tv1000gr, tv500gr;
    private RelativeLayout rvBackground, rvBackground2, rv1000gr, rv500gr, rvMake;
    private ImageView imgMain;

    public WeightFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weight, container, false);

        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tv1000gr = (TextView) view.findViewById(R.id.tv_1000gr);
        tv500gr = (TextView) view.findViewById(R.id.tv_500gr);
        rvBackground = (RelativeLayout) view.findViewById(R.id.rv_background);
        rvBackground2 = (RelativeLayout) view.findViewById(R.id.rv_background2);
        rv1000gr = (RelativeLayout) view.findViewById(R.id.rv_1000gr);
        rv500gr = (RelativeLayout) view.findViewById(R.id.rv_500gr);
        rvMake = (RelativeLayout) view.findViewById(R.id.rv_make);
        imgMain = (ImageView) view.findViewById(R.id.img_main);

        rvBackground.setBackgroundColor(Color.parseColor(MainActivity.CHOOSEN_COLOR.getHexCode()));
        rvBackground2.setBackgroundColor(Color.parseColor(MainActivity.CHOOSEN_COLOR.getHexCode()));
        tvTitle.setTextColor(Color.parseColor(MainActivity.CHOOSEN_COLOR.getTextColor()));

        rv1000gr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rv1000gr.setBackgroundResource(R.drawable.xd_button_gradient);
                tv1000gr.setTextColor(Color.parseColor("#FFFFFF"));

                rv500gr.setBackgroundResource(R.drawable.xd_button_white);
                tv500gr.setTextColor(Color.parseColor("#0D87FF"));

                imgMain.setImageResource(R.drawable.img_weight_1000gr);

                MainActivity.CHOOSEN_COLOR.setWeight("!10@");
            }
        });

        rv500gr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rv500gr.setBackgroundResource(R.drawable.xd_button_gradient);
                tv500gr.setTextColor(Color.parseColor("#FFFFFF"));

                rv1000gr.setBackgroundResource(R.drawable.xd_button_white);
                tv1000gr.setTextColor(Color.parseColor("#0D87FF"));

                imgMain.setImageResource(R.drawable.img_weight_500gr);

                MainActivity.CHOOSEN_COLOR.setWeight("!5@");
            }
        });

        rvMake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.CHOOSEN_COLOR.getWeight() != null) {
                    Toast.makeText(getContext(), "Color: "+ MainActivity.CHOOSEN_COLOR.getName() +", Weight: "+ MainActivity.CHOOSEN_COLOR.getWeight(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Please choose a weight.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

}
