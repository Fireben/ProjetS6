package educatus.shared.dto;

import java.io.Serializable;

public class HomePageContent implements Serializable {

	private static final long serialVersionUID = 8493021507852482463L;

	public static class HomePageSection implements Serializable {

		private static final long serialVersionUID = -6185923193127847290L;
		
		private String sectionTitle;
		private String sectionText;
		private String sectionImg;
		private String sectionDescription;
		private String sectionLinkText;

		public String getSectionTitle() {
			return sectionTitle;
		}

		public void setSectionTitle(String sectionTitle) {
			this.sectionTitle = sectionTitle;
		}

		public String getSectionText() {
			return sectionText;
		}

		public void setSectionText(String sectionText) {
			this.sectionText = sectionText;
		}

		public String getSectionImg() {
			return sectionImg;
		}

		public void setSectionImg(String sectionImg) {
			this.sectionImg = sectionImg;
		}

		public String getSectionDescription() {
			return sectionDescription;
		}

		public void setSectionDescription(String sectionDescription) {
			this.sectionDescription = sectionDescription;
		}

		public String getSectionLinkText() {
			return sectionLinkText;
		}

		public void setSectionLinkText(String sectionLink) {
			this.sectionLinkText = sectionLink;
		}
	}

	private String title;

	private String welcomeImage;
	private String welcomeTitle;
	private String welcomeDescription1;
	private String welcomeDescription2;
	private String welcomeDescription3;
	private String welcomeButtonText;

	private HomePageSection seminarsSection;
	private HomePageSection problemsSection;
	private HomePageSection statisticsSection;
	private HomePageSection forumsSection;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWelcomeImage() {
		return welcomeImage;
	}

	public void setWelcomeImage(String welcomeImage) {
		this.welcomeImage = welcomeImage;
	}

	public String getWelcomeTitle() {
		return welcomeTitle;
	}

	public void setWelcomeTitle(String welcomeTitle) {
		this.welcomeTitle = welcomeTitle;
	}

	public String getWelcomeDescription1() {
		return welcomeDescription1;
	}

	public void setWelcomeDescription1(String welcomeDescription1) {
		this.welcomeDescription1 = welcomeDescription1;
	}

	public String getWelcomeDescription2() {
		return welcomeDescription2;
	}

	public void setWelcomeDescription2(String welcomeDescription2) {
		this.welcomeDescription2 = welcomeDescription2;
	}

	public String getWelcomeDescription3() {
		return welcomeDescription3;
	}

	public void setWelcomeDescription3(String welcomeDescription3) {
		this.welcomeDescription3 = welcomeDescription3;
	}

	public String getWelcomeButtonText() {
		return welcomeButtonText;
	}

	public void setWelcomeButtonText(String welcomeButton) {
		this.welcomeButtonText = welcomeButton;
	}

	public HomePageSection getSeminarsSection() {
		return seminarsSection;
	}

	public void setSeminarsSection(HomePageSection seminarsSection) {
		this.seminarsSection = seminarsSection;
	}

	public HomePageSection getProblemsSection() {
		return problemsSection;
	}

	public void setProblemsSection(HomePageSection problemsSection) {
		this.problemsSection = problemsSection;
	}

	public HomePageSection getStatisticsSection() {
		return statisticsSection;
	}

	public void setStatisticsSection(HomePageSection statisticsSection) {
		this.statisticsSection = statisticsSection;
	}

	public HomePageSection getForumsSection() {
		return forumsSection;
	}

	public void setForumsSection(HomePageSection forumsSection) {
		this.forumsSection = forumsSection;
	}
}
