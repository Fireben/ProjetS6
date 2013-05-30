package educatus.client.ui.dataGrids;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.Image;

public class ImageCell extends AbstractCell<Integer> {
    @Override
    public void render(com.google.gwt.cell.client.Cell.Context context, Integer difficulty, SafeHtmlBuilder sb) {
		Image image = new Image("images/star.png");
		image.setHeight("20px");
		image.setWidth("20px");
		for(int i=0;i<difficulty;i++) {
	        sb.append(SafeHtmlUtils.fromTrustedString(image.toString()));        
		}
    }
}
