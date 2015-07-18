package net.rosoftlab.nav;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import net.rosoftlab.nav.launcher.R;
import net.rosoftlab.nav.launcher.VanillaNav;

/**
 * Created by Adi Pascu on 7/18/2015.
 * Email mail@adipascu.ro
 */
public class NavigationButton extends ImageView {
    private Long venueId;
    private IconSize iconSize;
    private boolean isInverted;
    private String referenceId;


    public NavigationButton(Context context) {
        super(context);
        init(null, 0, 0);
    }

    public NavigationButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0, 0);
    }

    public NavigationButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public NavigationButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs, defStyleAttr, defStyleRes);
    }

    public boolean isInverted() {
        return isInverted;
    }

    private void setInverted(boolean isInverted) {
        this.isInverted = isInverted;
        if (isInverted)
            setColorFilter(getColor(R.color.vanillanav_white));
        else
            setColorFilter(getColor(R.color.vanillanav_orange));
    }

    @ColorInt
    private int getColor(@ColorRes int color) {
        if (isInEditMode()) { //fix for preview screen crashing
            if (color == R.color.vanillanav_orange)
                return 0xffe97300;
            if (color == R.color.vanillanav_white)
                return 0xffffffff;
        }
        return getResources().getColor(color);
    }


    @Nullable
    public Long getVenueId() {
        return venueId;
    }

    public void setVenueId(@Nullable Long venueId) {
        this.venueId = venueId;
        updateListener();
    }


    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
        updateListener();
    }

    private void init(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray attr = getContext().obtainStyledAttributes(
                attrs, R.styleable.NavigationButton, defStyleAttr, defStyleRes);

        if (!attr.getBoolean(R.styleable.NavigationButton_noStyle, false)) {
            if (!hasPadding())
                setDefaultPadding();
            setScaleType(ScaleType.CENTER);
            setSelectableBackground();
        }

        if (getDrawable() == null) {
            setInverted(attr.getBoolean(R.styleable.NavigationButton_invert, false));
            int iconSizeInt = attr.getInteger(R.styleable.NavigationButton_iconSize, 0x01);
            setIconSize(IconSize.valueOf(iconSizeInt));
        }

        if (attr.hasValue(R.styleable.NavigationButton_venueId))
            setVenueId((long) attr.getInteger(R.styleable.NavigationButton_venueId, 0));
        else
            setVenueId(null);
        setReferenceId(attr.getString(R.styleable.NavigationButton_reference));

        attr.recycle();

//        boolean customBackground = getBackground() != null;
//        boolean customTint;
//        if (Build.VERSION.SDK_INT >= 21)
//            customTint = getImageTintList() != null;
//        else
//            customTint = getColorFilter() != null;
//        boolean customImage = getDrawable() != null;
//
//        boolean customScaleType = getScaleType() != null;
//
//        if (!customBackground)
//            setSelectableBackground();
//        if (!customTint)
//            setInverted();
//        if (!customImage)
//            setNavigationImage();
//        if (!customScaleType)
//            setScaleType(ScaleType.CENTER);
    }

    @SuppressWarnings("RedundantIfStatement")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private boolean hasPadding() {
        if ((getPaddingTop() > 0) || (getPaddingBottom() > 0) ||
                (getPaddingLeft() > 0) || (getPaddingRight() > 0))
            return true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && ((getPaddingStart() > 0) || (getPaddingEnd() > 0)))
            return true;
        return false;

    }

    private void updateListener() {
        if (!TextUtils.isEmpty(referenceId) && venueId != null)
            setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    VanillaNav.navigate(getContext(), venueId, Long.parseLong(referenceId));
                }
            });
        else
            setOnClickListener(null);
    }

    private void setDefaultPadding() {
        int padding = (int) getDimension(R.dimen.navigationPadding);
        setPadding(padding, padding, padding, padding);
    }

    private float getDimension(@DimenRes int id) {
        if (isInEditMode() && id == R.dimen.navigationPadding) {
            return (getContext().getResources().getDisplayMetrics().density * 16);
        }
        return getResources().getDimension(id);
    }


    private void setSelectableBackground() {
        int[] attrs = new int[]{android.R.attr.selectableItemBackground};
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs);
        int backgroundResource = typedArray.getResourceId(0, 0);
        setBackgroundResource(backgroundResource);
        typedArray.recycle();
    }

    @Nullable
    public IconSize getIconSize() {
        return iconSize;
    }

    public void setIconSize(@Nullable IconSize iconSize) {
        this.iconSize = iconSize;
        if (iconSize == null)
            setImageResource(0);
        else if (iconSize == IconSize.SMALL)
            setImageResource(R.drawable.navigate_24);
        else if (iconSize == IconSize.BIG)
            setImageResource(R.drawable.navigate_48);
        else
            throw new UnsupportedOperationException();
    }


    enum IconSize {
        SMALL,
        BIG;

        public static IconSize valueOf(int i) {
            if (i == 0x01)
                return SMALL;
            if (i == 0x02)
                return BIG;
            throw new UnsupportedOperationException();
        }
    }


}
