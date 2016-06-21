package net.rosoftlab.nav;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import net.rosoftlab.nav.sdk.R;
import net.rosoftlab.nav.sdk.VanillaNav;

/**
 * Created by Adi Pascu on 7/18/2015.
 * Email mail@adipascu.ro
 */
public class NavigationButton extends ImageView {
    private Long venueId;
    private String destinationId;
    private boolean hasStyle;


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


    private void setTint(boolean isInverted) {
        int colorRes = isInverted ? R.color.vn_white : R.color.vn_orange;
        if (Build.VERSION.SDK_INT >= 23)
            setColorFilter(getContext().getColor(colorRes));
        else
            //noinspection deprecation
            setColorFilter(getResources().getColor(colorRes));
    }

    public Long getVenueId() {
        return venueId;
    }

    public void setVenueId(Long venueId) {
        this.venueId = venueId;
        updateListener();
    }


    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
        updateListener();
    }

    private void init(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray attr = getContext().obtainStyledAttributes(
                attrs, R.styleable.NavigationButton, defStyleAttr, defStyleRes);

        hasStyle = !attr.getBoolean(R.styleable.NavigationButton_vn_no_style, false);
        if (hasStyle) {
            if (!hasPadding())
                setDefaultPadding();
            setScaleType(ScaleType.CENTER);
        }

        if (getDrawable() == null) {
            setTint(attr.getBoolean(R.styleable.NavigationButton_vn_invert, false));
            int iconSizeInt = attr.getInteger(R.styleable.NavigationButton_vn_icon_size, 0x01);
            setImageResource(iconSizeInt == 0x02 ? R.drawable.vn_navigate_48 : R.drawable.vn_navigate_24);
        }

        if (attr.hasValue(R.styleable.NavigationButton_vn_venue_id))
            setVenueId((long) attr.getInteger(R.styleable.NavigationButton_vn_venue_id, 0));
        else
            setVenueId(null);
        setDestinationId(attr.getString(R.styleable.NavigationButton_vn_destination_id));

        attr.recycle();

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
        boolean hasValidData = !TextUtils.isEmpty(destinationId) && venueId != null;
        if (hasValidData) {
            setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    VanillaNav.navigate(getContext(), venueId, Long.parseLong(destinationId));
                }
            });
        } else {
            setOnClickListener(null);
            setClickable(false);
        }
        setSelectableBackground(hasValidData);
    }

    private void setDefaultPadding() {
        int padding = (int) getDimension(R.dimen.vn_nav_padding);
        setPadding(padding, padding, padding, padding);
    }

    private float getDimension(int id) {
        if (isInEditMode() && id == R.dimen.vn_nav_padding) {
            return (getContext().getResources().getDisplayMetrics().density * 16);
        }
        return getResources().getDimension(id);
    }


    private void setSelectableBackground(boolean isSelectable) {
        if (!hasStyle)
            return;
        if (isSelectable) {
            int[] attrs = new int[]{android.R.attr.selectableItemBackground};
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs);
            int backgroundResource = typedArray.getResourceId(0, 0);
            setBackgroundResource(backgroundResource);
            typedArray.recycle();
        } else
            setBackgroundResource(0);
    }

}
