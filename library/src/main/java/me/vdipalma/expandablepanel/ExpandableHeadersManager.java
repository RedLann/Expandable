package me.vdipalma.expandablepanel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by enzo on 18/03/18.
 */

public class ExpandableHeadersManager {
    private static ExpandableHeadersManager instance = null;
    private List<ExpandableHeader> mHeaders;

    public ExpandableHeadersManager() {
        this.mHeaders = new ArrayList<>();
    }

    public static ExpandableHeadersManager getInstance() {
        if (instance == null)
            instance = new ExpandableHeadersManager();
        return instance;
    }

    public void register(ExpandableHeader expandableHeader) {
        mHeaders.add(expandableHeader);
    }

    public void toggle(ExpandableHeader expandableHeader) {
        if (expandableHeader.isExclusiveOpen() && !expandableHeader.isExpanded()){
            for (ExpandableHeader header : mHeaders) {
                header.disable();
            }
        }
        expandableHeader.toggle();
    }
}
