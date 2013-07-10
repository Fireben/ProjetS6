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
    @Source("1370561423_Gnome-Window-Close-20.png")
    ImageResource deleteButton();
    
    @Source("alignLeft.png")
    ImageResource alignLeft();
    
    @Source("alignCenter.png")
    ImageResource alignCenter();
    
    @Source("alignRight.png")
    ImageResource alignRight();
    
    @Source("flag_french_32px.png")
    ImageResource frenchLanguageButton();
    
    @Source("flag_english_32px.png")
    ImageResource englishLanguageButton();

    @Source("addImage.png")
    ImageResource addImageButton();
    
    @Source("addText.png")
    ImageResource addTextButton();
    
    @Source("addVideo.png")
    ImageResource addVideoButton();
    
    @Source("addPdf.png")
    ImageResource addPdfButton();
}