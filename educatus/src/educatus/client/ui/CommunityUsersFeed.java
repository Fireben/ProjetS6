package educatus.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import educatus.client.NameTokens;
import educatus.client.ui.dataGrids.HyperLinkCell;
import educatus.client.ui.dataGrids.User;
import educatus.resources.SeminaryDataGridCssRessource;

public class CommunityUsersFeed extends Composite {
	
	private static CommunityUsersFeedUiBinder uiBinder = GWT.create(CommunityUsersFeedUiBinder.class);

	@UiField
	VerticalPanel usersFeedVerticalPanel;
	@UiField
	Label usersFeedTitleLabel;
	@UiField(provided=true) 
	CellTable<User> usersFeedTable;
	
	interface CommunityUsersFeedUiBinder extends UiBinder<Widget, CommunityUsersFeed> {
	}

	public CommunityUsersFeed() {
		CellTable.Resources SeminaryDataGridRessources = GWT.create(SeminaryDataGridCssRessource.class);
		usersFeedTable = new CellTable<User>(12, SeminaryDataGridRessources);
		usersFeedTable.addStyleName("newsfeedTable");
		initializeColumns();
		initWidget(uiBinder.createAndBindUi(this));
	}
	

	public CellTable<User> getDataGrid() {
		return usersFeedTable;
	}	
	
	private void initializeColumns() {	
		
		 Column<User, Hyperlink> nameColumn = 
				    new Column<User, Hyperlink>(new HyperLinkCell()) { 
				        @Override 
				        public Hyperlink getValue(User user) {			    	  
				        	Hyperlink link = new Hyperlink(user.getFullName(), NameTokens.getProfil() + ";cip=" + user.getCip());
				            link.setStyleName("cellTableHyperlink");
				            return link;
				        }
					};
			usersFeedTable.addColumn(nameColumn, "Name", "");
			usersFeedTable.setColumnWidth(nameColumn, 200, Unit.PX);
		
		 Column<User, Hyperlink> cipColumn = 
				    new Column<User, Hyperlink>(new HyperLinkCell()) { 
				        @Override 
				        public Hyperlink getValue(User user) {			    	  
				        	Hyperlink link = new Hyperlink(user.getCip(), NameTokens.getProfil() + ";cip=" + user.getCip());
				            link.setStyleName("cellTableHyperlink");
				            return link;
				        }
					};
			usersFeedTable.addColumn(cipColumn, "Cip", "");
			usersFeedTable.setColumnWidth(cipColumn, 100, Unit.PX);
			
			TextColumn<User> joinedDateColumn = new TextColumn<User>() {
		    @Override
		    public String getValue(User user) {
		      return user.getJoinedDate();
		    }
		  };
		usersFeedTable.addColumn(joinedDateColumn, "Joined Date", "");
		usersFeedTable.setColumnWidth(joinedDateColumn, 200, Unit.PX);
		
		TextColumn<User> lastConnexionColumn = new TextColumn<User>() {
		    @Override
		    public String getValue(User user) {
		      return user.getLastConnexion();
		    }
		  };
		usersFeedTable.addColumn(lastConnexionColumn, "Last Connexion", "");
		usersFeedTable.setColumnWidth(lastConnexionColumn, 200, Unit.PX);

	}
}
