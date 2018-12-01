package puzzle;

public class Performance {

	private int performance = 25, deltaPerformance;	
	
	public void update() {

		/*if (performance > 25)
			adv2 = true;
		

		else if (performance < 25)
			adv1 = true;

		else 
		{
			adv1 = false;
			adv2 = false;
		}*/

		performance += deltaPerformance;
		deltaPerformance = 0;
	}

	public int getPerformance() {
		return performance;
	}

	public void setPerformance(int performance) {
		this.performance = performance;
	}

	public int getDeltaPerformance() {
		return deltaPerformance;
	}

	public void setDeltaPerformance(int deltaPerformance) {
		this.deltaPerformance = deltaPerformance;
	}	
	
}
