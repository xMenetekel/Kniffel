package model;

public interface WuerfelLogik {

	void wuerfeln();
	int getAugen();
	int getSeiten();
	void setBehalten(boolean behalten);
	void nichtBehalten();
	
}
