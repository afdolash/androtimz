package com.pens.afdolash.androtimz.main_activity.color_fragment;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pens.afdolash.androtimz.R;
import com.pens.afdolash.androtimz.main_activity.ColorData;
import com.pens.afdolash.androtimz.main_activity.MainActivity;
import com.pens.afdolash.androtimz.main_activity.weight_fragment.WeightFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ColorFragment extends Fragment {

    private ImageView imgMore;
    private TextView tvColorName, tvColorHex, tvTitle;
    private RelativeLayout rvBackground, rvBackground2, rvNext;
    private RecyclerView rcColor;
    private ColorAdapter colorAdapter;
    private List<ColorData> colors = new ArrayList<>();

    public ColorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_color, container, false);

        imgMore = (ImageView) view.findViewById(R.id.img_more);
        tvColorName = (TextView) view.findViewById(R.id.tv_colorName);
        tvColorHex = (TextView) view.findViewById(R.id.tv_colorHex);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        rvBackground = (RelativeLayout) view.findViewById(R.id.rv_background);
        rvBackground2 = (RelativeLayout) view.findViewById(R.id.rv_background2);
        rvNext = (RelativeLayout) view.findViewById(R.id.rv_next);
        rcColor = (RecyclerView) view.findViewById(R.id.rc_color);

        colorAdapter = new ColorAdapter(this, colors);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rcColor.setLayoutManager(layoutManager);
        rcColor.setItemAnimator(new DefaultItemAnimator());
        rcColor.setAdapter(colorAdapter);

        rvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.CHOOSEN_COLOR != null) {
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    WeightFragment weightFragment = new WeightFragment();
                    fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out, android.R.animator.fade_in, android.R.animator.fade_out);
                    fragmentTransaction.replace(R.id.container, weightFragment, WeightFragment.class.getSimpleName());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else {
                    Toast.makeText(getContext(), "Please pick a color.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getContext(), imgMore, Gravity.RIGHT);
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.menu_color, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.nav_clearRed:
                                ((MainActivity) getActivity()).sendData("!1@");
                                Toast.makeText(getContext(), "Cleaning red color...", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.nav_clearYellow:
                                ((MainActivity) getActivity()).sendData("!2@");
                                Toast.makeText(getContext(), "Cleaning yellow color...", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.nav_clearBlue:
                                ((MainActivity) getActivity()).sendData("!3@");
                                Toast.makeText(getContext(), "Cleaning blue color...", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.nav_clearWhite:
                                ((MainActivity) getActivity()).sendData("!4@");
                                Toast.makeText(getContext(), "Cleaning white color...", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.nav_calibrateRed:
                                ((MainActivity) getActivity()).sendData("!6@");
                                Toast.makeText(getContext(), "Calibrating red color...", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.nav_calibrateYellow:
                                ((MainActivity) getActivity()).sendData("!7@");
                                Toast.makeText(getContext(), "Calibrating yellow color...", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.nav_calibrateBlue:
                                ((MainActivity) getActivity()).sendData("!8@");
                                Toast.makeText(getContext(), "Calibrating blue color...", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.nav_calibrateWhite:
                                ((MainActivity) getActivity()).sendData("!9@");
                                Toast.makeText(getContext(), "Calibrating white color...", Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });

        setDataColor();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MainActivity.CHOOSEN_COLOR != null) {
            rvBackground.setBackgroundColor(Color.parseColor(MainActivity.CHOOSEN_COLOR.getHexCode()));
            rvBackground2.setBackgroundColor(Color.parseColor(MainActivity.CHOOSEN_COLOR.getHexCode()));
            imgMore.setImageTintList(ColorStateList.valueOf(Color.parseColor(MainActivity.CHOOSEN_COLOR.getTextColor())));
            tvColorName.setText(MainActivity.CHOOSEN_COLOR.getName());
            tvColorHex.setText(MainActivity.CHOOSEN_COLOR.getHexCode());
            tvColorName.setTextColor(Color.parseColor(MainActivity.CHOOSEN_COLOR.getTextColor()));
            tvColorHex.setTextColor(Color.parseColor(MainActivity.CHOOSEN_COLOR.getTextColor()));
            tvTitle.setTextColor(Color.parseColor(MainActivity.CHOOSEN_COLOR.getTextColor()));
        }
    }

    private void setDataColor() {
        colors.clear();

        colors.add(new ColorData("Red Crimson", "#D40F12", "#FFFFFF", "!A@"));
        colors.add(new ColorData("Dark Orange", "#F42722", "#FFFFFF", "!B@"));
        colors.add(new ColorData("Deep Pink", "#EF4B4A", "#FFFFFF", "!C@"));
        colors.add(new ColorData("Orange", "#EE3423", "#FFFFFF", "!D@"));
        colors.add(new ColorData("Carrot", "#EE6821", "#FFFFFF", "!E@"));
        colors.add(new ColorData("Blue Cobalt", "#046CCD", "#FFFFFF", "!F@"));
        colors.add(new ColorData("Skylight", "#4CD6E8", "#212121", "!G@"));
        colors.add(new ColorData("Indigo", "#423575", "#FFFFFF", "!H@"));
        colors.add(new ColorData("Purple", "#823C6F", "#FFFFFF", "!I@"));
        colors.add(new ColorData("Dark Magenta", "#6E1836", "#FFFFFF", "!J@"));
        colors.add(new ColorData("Peanut", "#965147", "#FFFFFF", "!K@"));
        colors.add(new ColorData("Yellow Canary", "#FFE926", "#212121", "!L@"));
        colors.add(new ColorData("Light Yellow", "#F0F05D", "#212121", "!M@"));
        colors.add(new ColorData("Green Pine", "#4C9A5C", "#FFFFFF", "!N@"));
        colors.add(new ColorData("Emeral Green", "#73C246", "#FFFFFF", "!O@"));
        colors.add(new ColorData("Green Ocean", "#338173", "#FFFFFF", "!P@"));

        colorAdapter.notifyDataSetChanged();
    }

    public ImageView getImgMore() {
        return imgMore;
    }

    public TextView getTvColorName() {
        return tvColorName;
    }

    public TextView getTvColorHex() {
        return tvColorHex;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public RelativeLayout getRvBackground() {
        return rvBackground;
    }

    public RelativeLayout getRvBackground2() {
        return rvBackground2;
    }
}
