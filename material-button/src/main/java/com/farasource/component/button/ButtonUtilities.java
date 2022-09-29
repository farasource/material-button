package com.farasource.component.button;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;

public final class ButtonUtilities {

    private static final int STATE_ENABLED = android.R.attr.state_enabled;
    private static final int STATE_PRESSED = android.R.attr.state_pressed;
    private static final int STATE_SELECTED = android.R.attr.state_selected;
    private static final int STATE_FOCUSED = android.R.attr.state_focused;

    static float dp(Context context, float px) {
        return context.getResources().getDisplayMetrics().density * px;
    }

    static int px(Context context, float dp) {
        return (int) (dp / context.getResources().getDisplayMetrics().density);
    }

    public static StateListDrawable createStateListDrawable(Context context, List<ButtonBackground.DrawableParams> drawableParams) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        for (ButtonBackground.DrawableParams drawableParam : drawableParams) {
            Drawable drawable = ButtonUtilities.createDrawable(context, drawableParam);
            stateListDrawable.addState(drawableParam.getButtonStateType(), drawable);
        }
        return stateListDrawable;
    }

    public static ColorStateList createColorStateList(List<ButtonBackground.RippleParams> rippleParams) {
        int[][] state = new int[rippleParams.size()][];
        int[] color = new int[rippleParams.size()];
        int i = 0;
        for (ButtonBackground.RippleParams drawableParam : rippleParams) {
            color[i] = drawableParam.getColor();
            state[i++] = drawableParam.getButtonStateType();
        }
        ColorStateList stateList = new ColorStateList(state, color);
        return stateList;
    }

    public static Drawable createBackground(Drawable drawable, ColorStateList colorStateList) {
        if (colorStateList == null) {
            return drawable;
        }
        return new RippleDrawable(colorStateList, drawable, null);
    }

    private static Drawable createDrawable(Context context, ButtonBackground.DrawableParams drawableParam) {
        GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR, drawableParam.getColors());
        float[] r = drawableParam.getRadius();
        drawable.setCornerRadii(new float[]{dp(context, r[0]), dp(context, r[0]),
                dp(context, r[1]), dp(context, r[1]), dp(context, r[2]), dp(context, r[2]),
                dp(context, r[3]), dp(context, r[3])});
        drawable.setStroke((int) dp(context, drawableParam.getStrokeWidth()), drawableParam.getStrokeColor());
        return drawable;
    }

    public static Context applyTheme(Context baseContext, AttributeSet attributeSet) {
        ContextThemeWrapper newContext = new ContextThemeWrapper(baseContext, R.style.MaterialButton_Theme);
        int androidThemeOverlayId = obtainAndroidThemeOverlayId(newContext, attributeSet);
        if (androidThemeOverlayId != 0) {
            newContext.getTheme().applyStyle(androidThemeOverlayId, true);
        }
        return newContext;
    }

    @StyleRes
    private static int obtainAndroidThemeOverlayId(@NonNull Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, new int[]{android.R.attr.theme});
        int androidThemeId = a.getResourceId(0, 0);
        a.recycle();
        return androidThemeId;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ButtonStateType {
        int NONE = 0;
        int ENABLED = STATE_ENABLED;
        int FOCUSED = STATE_FOCUSED;
        int PRESSED = STATE_PRESSED;
        int SELECTED = STATE_SELECTED;
    }

}