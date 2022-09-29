package com.farasource.component.button;

import android.graphics.Color;
import android.util.StateSet;

import java.util.Arrays;

import androidx.annotation.ColorInt;

public class ButtonBackground {

    private int[] stateTypes = StateSet.WILD_CARD;
    private final float[] radius = new float[4];
    private int[] colors;
    private float strokeWidth;
    private int strokeColor;

    private ButtonBackground() {
    }

    public static final class DrawableParams {

        private final ButtonBackground buttonBackground;

        private DrawableParams() {
            buttonBackground = new ButtonBackground();
        }

        public float[] getRadius() {
            return buttonBackground.radius;
        }

        public int[] getButtonStateType() {
            return buttonBackground.stateTypes;
        }

        public int[] getColors() {
            if (buttonBackground.colors == null || buttonBackground.colors.length == 0)
                return new int[]{Color.BLACK};
            else if (buttonBackground.colors.length == 1)
                return new int[]{buttonBackground.colors[0], buttonBackground.colors[0], buttonBackground.colors[0]};
            else if (buttonBackground.colors.length == 2)
                return new int[]{buttonBackground.colors[0], buttonBackground.colors[0], buttonBackground.colors[1]};
            else return buttonBackground.colors;
        }

        public float getStrokeWidth() {
            return buttonBackground.strokeWidth;
        }

        public int getStrokeColor() {
            return buttonBackground.strokeColor;
        }

        public static class Builder {
            private final DrawableParams drawableParams;

            private Builder() {
                drawableParams = new ButtonBackground.DrawableParams();
            }

            public Builder addButtonStateType(@ButtonUtilities.ButtonStateType int buttonStateType) {
                if (buttonStateType == ButtonUtilities.ButtonStateType.NONE) {
                    drawableParams.buttonBackground.stateTypes = StateSet.WILD_CARD;
                } else {
                    int[] state = drawableParams.buttonBackground.stateTypes;
                    state = Arrays.copyOf(state, state.length + 1);
                    state[state.length - 1] = buttonStateType;
                    drawableParams.buttonBackground.stateTypes = state;
                }
                return this;
            }

            public Builder setRadius(float radius) {
                radius = Math.abs(radius);
                drawableParams.buttonBackground.radius[0] = radius;
                drawableParams.buttonBackground.radius[1] = radius;
                drawableParams.buttonBackground.radius[2] = radius;
                drawableParams.buttonBackground.radius[3] = radius;
                return this;
            }

            public Builder setRadius(float topStartRadius, float topEndRadius, float bottomEndRadius, float bottomStartRadius) {
                drawableParams.buttonBackground.radius[0] = Math.abs(topStartRadius);
                drawableParams.buttonBackground.radius[1] = Math.abs(topEndRadius);
                drawableParams.buttonBackground.radius[2] = Math.abs(bottomEndRadius);
                drawableParams.buttonBackground.radius[3] = Math.abs(bottomStartRadius);
                return this;
            }

            public Builder setTopStartRadius(float topStartRadius) {
                drawableParams.buttonBackground.radius[0] = Math.abs(topStartRadius);
                return this;
            }

            public Builder setTopEndRadius(float topEndRadius) {
                drawableParams.buttonBackground.radius[1] = Math.abs(topEndRadius);
                return this;
            }

            public Builder setBottomStartRadius(float bottomStartRadius) {
                drawableParams.buttonBackground.radius[3] = Math.abs(bottomStartRadius);
                return this;
            }

            public Builder setBottomEndRadius(float bottomEndRadius) {
                drawableParams.buttonBackground.radius[2] = Math.abs(bottomEndRadius);
                return this;
            }

            public Builder setColors(@ColorInt int... colors) {
                drawableParams.buttonBackground.colors = colors;
                return this;
            }

            public Builder setStrokeColor(@ColorInt int color) {
                drawableParams.buttonBackground.strokeColor = color;
                return this;
            }

            public Builder setStrokeWidth(float width) {
                drawableParams.buttonBackground.strokeWidth = width;
                return this;
            }

            public DrawableParams build() {
                return drawableParams;
            }

        }

        public static Builder newBuilder() {
            return new Builder();
        }
    }

    public static final class RippleParams {

        private final ButtonBackground buttonBackground;

        private RippleParams() {
            buttonBackground = new ButtonBackground();
        }

        public int[] getButtonStateType() {
            return buttonBackground.stateTypes;
        }

        public int getColor() {
            if (buttonBackground.colors == null || buttonBackground.colors.length == 0)
                return Color.BLACK;
            return buttonBackground.colors[0];
        }

        public static class Builder {
            private final RippleParams drawableParams;

            private Builder() {
                drawableParams = new RippleParams();
            }

            public RippleParams.Builder addButtonStateType(@ButtonUtilities.ButtonStateType int buttonStateType) {
                if (buttonStateType == ButtonUtilities.ButtonStateType.NONE) {
                    drawableParams.buttonBackground.stateTypes = StateSet.WILD_CARD;
                } else {
                    int[] state = drawableParams.buttonBackground.stateTypes;
                    state = Arrays.copyOf(state, state.length + 1);
                    state[state.length - 1] = buttonStateType;
                    drawableParams.buttonBackground.stateTypes = state;
                }
                return this;
            }

            public Builder setColor(@ColorInt int color) {
                drawableParams.buttonBackground.colors = new int[]{color};
                return this;
            }

            public RippleParams build() {
                return drawableParams;
            }

        }

        public static Builder newBuilder() {
            return new Builder();
        }
    }

}