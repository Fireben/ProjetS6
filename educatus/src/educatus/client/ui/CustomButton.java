package educatus.client.ui;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class CustomButton extends Button implements HasWidgets
{
    private ArrayList<Widget> widgets = new ArrayList<Widget>();

    @Override
    public void add(Widget w)
    {
        DOM.insertChild(getElement(), w.getElement(), widgets.size());
    }

    @Override
    public void clear()
    {
        for (Widget widget : widgets)
        {
            DOM.removeChild(getElement(), widget.getElement());
        }
        widgets.clear();
    }

    @Override
    public Iterator<Widget> iterator()
    {
        return widgets.iterator();
    }

    @Override
    public boolean remove(Widget w)
    {
        if (widgets.indexOf(w) != -1)
        {
            widgets.remove(w);
            DOM.removeChild(getElement(), w.getElement());
            return true;
        }
        else
            return false;
    }

}