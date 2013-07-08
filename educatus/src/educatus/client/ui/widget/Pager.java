package educatus.client.ui.widget;

import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.Range;

public class Pager extends SimplePager {
	private int pageSize = 12;

    public Pager(TextLocation center, Resources simplePagerRessources, boolean showFastForwardButton, 
    		int fastForwardRows, boolean showLastPageButton) {
		super(center, simplePagerRessources, showFastForwardButton, 
	    		fastForwardRows, showLastPageButton);
	}

	@Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public void previousPage() {
        if (getDisplay() != null) {
            Range range = getDisplay().getVisibleRange();
            setPageStart(range.getStart() - getPageSize());
        }
    }

    @Override
    public void setPageStart(int index) {
        if (getDisplay() != null) {
            Range range = getDisplay().getVisibleRange();
            int displayPageSize = getPageSize();
            if (isRangeLimited() && getDisplay().isRowCountExact()) {
                displayPageSize = Math.min(getPageSize(), getDisplay()
                        .getRowCount() - index);
            }
            index = Math.max(0, index);
            if (index != range.getStart()) {
                getDisplay().setVisibleRange(index, displayPageSize);
            }
        }
    }

    @Override
    public void nextPage() {
        if (getDisplay() != null) {
            Range range = getDisplay().getVisibleRange();
            setPageStart(range.getStart() + getPageSize());
        }
    }
}
