package educatus.server.businesslogic.uibuilder;

import educatus.shared.dto.HomePageContent;
import educatus.shared.dto.HomePageContent.HomePageSection;

public class HomePageFactory {

	public static HomePageContent createHomePageContent(String culture, String language) {
		
		// TODO, we will get textcontententries from database here, to build the correct homepage from provided culture & language
		HomePageContent content = new HomePageContent();
		
		content.setTitle("EducateUdeS");
		content.setWelcomeImage("images/light_bulb.png");
		content.setWelcomeTitle("EducateUdeS");
		content.setWelcomeDescription1("Enhance your abilities in multiples categories.");
		content.setWelcomeDescription2("In collaboration with Université de Sherbrooke, our team of 6 members do their best to offer you useful content in a unique motivating way.");
		content.setWelcomeDescription3("Be the best and complete all the problems!");
		content.setWelcomeButtonText("Start");
		
		HomePageSection seminarsSection = new HomePageSection();
		seminarsSection.setSectionTitle("Seminars");
		seminarsSection.setSectionText("Watch or read tutorial");
		seminarsSection.setSectionImg("images/door_mind.png");
		seminarsSection.setSectionDescription("With these seminars, would learn all you need to know to successfully resolve the problems and to increase your knowledge.");
		seminarsSection.setSectionLinkText("Start now!");
		content.setSeminarsSection(seminarsSection);
		
		HomePageSection problemsSection = new HomePageSection();
		problemsSection.setSectionTitle("Problems");
		problemsSection.setSectionText("Test your knowledge");
		problemsSection.setSectionImg("images/earth_puzzle_3.png");
		problemsSection.setSectionDescription("Resolve a multitude of exercises and success to be rewarded with Achievement. Push your skills and your knowledge to the limits.");
		problemsSection.setSectionLinkText("Start now!");
		content.setProblemsSection(problemsSection);
		
		HomePageSection statisticsSection = new HomePageSection();
		statisticsSection.setSectionTitle("Stats");
		statisticsSection.setSectionText("See if you are good.");
		statisticsSection.setSectionImg("images/1369881028_Volume Manager.png");
		statisticsSection.setSectionDescription("Watch the statistic of everybody. See if your friend have solved more problems than you and compare yourself with others.");
		statisticsSection.setSectionLinkText("Watch now!");
		content.setStatisticsSection(statisticsSection);
		
		HomePageSection forumsSection = new HomePageSection();
		forumsSection.setSectionTitle("Forums");
		forumsSection.setSectionText("Talk with others.");
		forumsSection.setSectionImg("images/Forums.png");
		forumsSection.setSectionDescription("");
		forumsSection.setSectionLinkText("Working on it ...");
		content.setForumsSection(forumsSection);
		
		return content;		
	}
	
}
