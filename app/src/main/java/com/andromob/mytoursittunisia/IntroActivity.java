package com.andromob.mytoursittunisia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.FloatRange;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import agency.tango.materialintroscreen.MaterialIntroActivity;
import agency.tango.materialintroscreen.SlideFragmentBuilder;
import agency.tango.materialintroscreen.animations.IViewTranslation;

public class IntroActivity extends MaterialIntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableLastSlideAlphaExitTransition(true);

        getNextButtonTranslationWrapper()
                .setEnterTranslation(new IViewTranslation() {
                    @Override
                    public void translate(View view, @FloatRange(from = 0, to = 1.0) float percentage) {
                        view.setAlpha(percentage);
                    }
                });

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.white)
                .buttonsColor(R.color.greenaqua)
                .image(R.drawable.intro_first_screeen)
                .title(getString(R.string.title_first_intro))
                .description(getString(R.string.txt_first_intro))
                .build());


        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.white)
                .buttonsColor(R.color.greenaqua)
                .image(R.drawable.intro_usd_source)
                .title(getString(R.string.title_second_intro))
                .description(getString(R.string.txt_second_intro))
                .build());


        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.white)
                .buttonsColor(R.color.greenaqua)
                .image(R.drawable.intro_second_screen)
                .title(getString(R.string.title_third_intro))
                .description(getString(R.string.txt_third_intro))
                .build());


        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.white)
                .buttonsColor(R.color.greenaqua)
                .image(R.drawable.intro_tick_mark)
                .title(getString(R.string.title_fourth_intro))
                .description(getString(R.string.txt_fourth_intro))
                .build());

    }

    public static final String MY_PREFS_NAME = "MyPrefsFile";
    @Override
    public void onFinish() {
        super.onFinish();

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt("intro_key", 01);
        editor.apply();
         Toast.makeText(this, "first opening of the app :)", Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(IntroActivity.this, HomeActivity.class);
        //startActivity(intent);
    }
}

