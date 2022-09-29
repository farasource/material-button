package com.farasource.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.farasource.component.button.ButtonBackground;
import com.farasource.component.button.ButtonUtilities;
import com.farasource.component.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        MaterialButton materialButton = findViewById(R.id.materialButton);
        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "MaterialButton", Toast.LENGTH_SHORT).show();
            }
        });
        List<ButtonBackground.DrawableParams> drawableParams = new ArrayList<>();
        drawableParams.add(ButtonBackground.DrawableParams.newBuilder()
                .addButtonStateType(ButtonUtilities.ButtonStateType.PRESSED)
                .setColors(getResources().getColor(R.color.purple_200))
                .setRadius(7)
                .setStrokeWidth(3)
                .setStrokeColor(getResources().getColor(R.color.purple_500))
                .build());
        drawableParams.add(ButtonBackground.DrawableParams.newBuilder()
                .setColors(getResources().getColor(R.color.purple_500))
                .setRadius(7)
                .build());
        materialButton.setBackgroundParamsList(drawableParams, ButtonBackground.RippleParams.newBuilder()
                .addButtonStateType(ButtonUtilities.ButtonStateType.PRESSED)
                .setColor(Color.WHITE)
                .build());
        materialButton.setCanButtonScale(true);

        MaterialButton multiColor = findViewById(R.id.multiColor);
        drawableParams = new ArrayList<>();
        drawableParams.add(ButtonBackground.DrawableParams.newBuilder()
                .addButtonStateType(ButtonUtilities.ButtonStateType.PRESSED)
                .setColors(0xffBA68C8, 0xffF06292, 0xff7986CB, 0xff81C784)
                .setRadius(10)
                .setStrokeWidth(1)
                .setStrokeColor(Color.BLACK)
                .build());
        drawableParams.add(ButtonBackground.DrawableParams.newBuilder()
                .setColors(0xff4caf50, 0xff9C27B0, 0xffF42A6E, 0xff03A9F4)
                .setRadius(10)
                .build());
        multiColor.setBackgroundParamsList(drawableParams);

        MaterialButton withoutRipple = findViewById(R.id.withoutRipple);
        drawableParams = new ArrayList<>();
        drawableParams.add(ButtonBackground.DrawableParams.newBuilder()
                .addButtonStateType(ButtonUtilities.ButtonStateType.PRESSED)
                .setColors(0xff009688, 0xffE0F2F1, 0xff009688)
                .setRadius(10)
                .setRadius(10, 10, 10, 10)
                .setStrokeWidth(3)
                .setStrokeColor(0xff009688)
                .build());
        drawableParams.add(ButtonBackground.DrawableParams.newBuilder()
                .setColors(Color.TRANSPARENT)
                .build());
        withoutRipple.setBackgroundParamsList(drawableParams);
    }
}