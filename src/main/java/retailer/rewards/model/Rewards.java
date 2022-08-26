package retailer.rewards.model;

public class Rewards {
    private long customerId;
	private long lastMonthTotalRewardPoints;
    private long lastSecondMonthTotalRewardPoints;
    private long lastThirdMonthTotalRewardPoints;
    private long totalRewardPoints;

	public void setlastMonthTotalRewardPoints(long lastMonthTotalRewardPoints) {
		this.lastMonthTotalRewardPoints = lastMonthTotalRewardPoints;
	}

	public void setlastSecondMonthTotalRewardPoints(long lastSecondMonthTotalRewardPoints) {
		this.lastSecondMonthTotalRewardPoints = lastSecondMonthTotalRewardPoints;
	}

	public void setlastThirdMonthTotalRewardPoints(long lastThirdMonthTotalRewardPoints) {
		this.lastThirdMonthTotalRewardPoints = lastThirdMonthTotalRewardPoints;
	}

	public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public void settotalRewardPoints(long totalRewardPoints) {
        this.totalRewardPoints = totalRewardPoints;
    }
}
