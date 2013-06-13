package educatus.client.ui.factory;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import educatus.client.ui.CustomButton;

public class CategoryButtonFactory {
	public static CustomButton get(String labelText, String imageUrl, int id) {
		CustomButton button = new CustomButton();
		button.add(new Image(imageUrl));	
		button.add(new Label(labelText));	
		button.getElement().setId(String.valueOf(id));
		button.setEnabled(false);
		return button;
	}
}
