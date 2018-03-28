package me.vdipalma.expandablepanel;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import me.vdipalma.expandable.R;

/**
 * Created by enzo on 17/03/18.
 */

public class ExpandableHeader extends RelativeLayout {
    private boolean exclusiveOpen;
    private String text;
    private int expandable_layout_id;
    private ExpandableLayout expandableLayout;
    private View expansionIndicatorIcon;

    public ExpandableHeader(Context context) {
        super(context);
        init();
    }

    public ExpandableHeader(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ExpandableHeader,
                0, 0);

        try {
            text = a.getString(R.styleable.ExpandableHeader_header_text);
            exclusiveOpen = a.getBoolean(R.styleable.ExpandableHeader_exclusive_open, false);
            expandable_layout_id = a.getResourceId(R.styleable.ExpandableHeader_expandable_layout_id, 0);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        init();
    }

    public boolean isExclusiveOpen() {
        return exclusiveOpen;
    }

    public ExpandableHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ExpandableHeader(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.expandable_header, this);
        TextView text_view = findViewById(R.id.header_inner_text);
        text_view.setText(text);
        ExpandableHeadersManager.getInstance().register(this);
        expansionIndicatorIcon = findViewById(R.id.header_expandable_icon);
        findViewById(R.id.header_container).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ExpandableHeadersManager.getInstance().toggle(ExpandableHeader.this);
            }
        });
    }

    public void loadExpandable(){
        if (expandableLayout == null){
            expandableLayout = getRootView().findViewById(expandable_layout_id);
            expandableLayout.setOnExpansionUpdateListener(new ExpandableLayout.OnExpansionUpdateListener() {
                @Override
                public void onExpansionUpdate(float expansionFraction, int state) {
                    switch (state){
                        case ExpandableLayout.State.COLLAPSING:
                        case ExpandableLayout.State.COLLAPSED:
                            expansionIndicatorIcon.setSelected(false);
                            break;
                        case ExpandableLayout.State.EXPANDING:
                        case ExpandableLayout.State.EXPANDED:
                            expansionIndicatorIcon.setSelected(true);
                            break;
                    }
                }
            });
        }
    }

    public void disable(){
        loadExpandable();
        expandableLayout.setExpanded(false);
    }
    public void toggle(){
        loadExpandable();
        expandableLayout.setExpanded(!expandableLayout.isExpanded());
    }
    public boolean isExpanded(){
        loadExpandable();
        return expandableLayout.isExpanded();
    }

}
