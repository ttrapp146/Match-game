package puzzle;

public class Boost {

	private double boost = 0, deltaBoost;	
	private double advMultiplier, disAdvMultiplier;
	//don't need boolean variables
	
	
	public void update()
	{				
		boost = boost + deltaBoost * advMultiplier / disAdvMultiplier;
		deltaBoost = 0;
	}

	public double getBoost() {
		return boost;
	}

	public void setBoost(double boost) {
		this.boost = boost;
	}

	public double getAdvMultiplier() {
		return advMultiplier;
	}

	public void setAdvMultiplier(double advMultiplier) {
		this.advMultiplier = advMultiplier;
	}

	public double getDeltaBoost() {
		return deltaBoost;
	}

	public void setDeltaBoost(double deltaBoost) {
		this.deltaBoost = deltaBoost;
	}

	public double getDisAdvMultiplier() {
		return disAdvMultiplier;
	}

	public void setDisAdvMultiplier(double disAdvMultiplier) {
		this.disAdvMultiplier = disAdvMultiplier;
	}
}
