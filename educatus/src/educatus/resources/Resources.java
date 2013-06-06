package educatus.resources;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

public interface Resources extends ClientBundle {
    public static final Resources INSTANCE = GWT.create(Resources.class); 

    @Source("educatus.css")
    @CssResource.NotStrict
    CssResource css();
    
    /* Seminar Section View Resources */
    @Source("deleteButton.png")
    ImageResource deleteButton();
    
    @Source("alignLeft.png")
    ImageResource alignLeft();
    
    @Source("alignCenter.png")
    ImageResource alignCenter();
    
    @Source("alignRight.png")
    ImageResource alignRight();
    
    @Source("addImage.png")
    ImageResource addImageButton();
    
    @Source("addText.png")
    ImageResource addTextButton();
    
    @Source("addVideo.png")
    ImageResource addVideoButton();
}