package retailer.rewards.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import retailer.rewards.constants.RewardConstants;
import retailer.rewards.entity.Transaction;
import retailer.rewards.model.Rewards;
import retailer.rewards.repository.TransactionRepository;

@Service
public class RewardsServiceImpl implements RewardsService {

	
	@Autowired
	TransactionRepository transactionRepository;

	public Rewards getRewardsByCustomerId(Long customerId) {

		Timestamp lastMonthTimestamp = getDateBasedOnOffSetDays(RewardConstants.MONTH);
		Timestamp lastSecondMonthTimestamp = getDateBasedOnOffSetDays(2*RewardConstants.MONTH);
		Timestamp lastThirdMonthTimestamp = getDateBasedOnOffSetDays(3*RewardConstants.MONTH);

		List<Transaction> lastMonthTransactions = transactionRepository.findAllByCustomerIdAndTransactionDateBetween(
				customerId, lastMonthTimestamp, Timestamp.from(Instant.now()));
		List<Transaction> lastSecondMonthTransactions = transactionRepository
				.findAllByCustomerIdAndTransactionDateBetween(customerId, lastSecondMonthTimestamp, lastMonthTimestamp);
		List<Transaction> lastThirdMonthTransactions = transactionRepository
				.findAllByCustomerIdAndTransactionDateBetween(customerId, lastThirdMonthTimestamp,
						lastSecondMonthTimestamp);

		Long lastMonthTotalRewardPoints = getRewardsPerMonth(lastMonthTransactions);
		Long lastSecondMonthTotalRewardPoints = getRewardsPerMonth(lastSecondMonthTransactions);
		Long lastThirdMonthTotalRewardPoints = getRewardsPerMonth(lastThirdMonthTransactions);

		Rewards customerRewards = new Rewards();
		customerRewards.setCustomerId(customerId);
		customerRewards.setlastMonthTotalRewardPoints(lastMonthTotalRewardPoints);
		customerRewards.setlastSecondMonthTotalRewardPoints(lastSecondMonthTotalRewardPoints);
		customerRewards.setlastThirdMonthTotalRewardPoints(lastThirdMonthTotalRewardPoints);
		customerRewards.settotalRewardPoints(lastMonthTotalRewardPoints + lastSecondMonthTotalRewardPoints + lastThirdMonthTotalRewardPoints);

		return customerRewards;

	}

	private Long getRewardsPerMonth(List<Transaction> transactions) {
		return transactions.stream().map(transaction -> calculateRewards(transaction))
				.collect(Collectors.summingLong(r -> r.longValue()));
	}

	private Long calculateRewards(Transaction t) {
		if (t.getTransactionAmount() > RewardConstants.LIMIT && t.getTransactionAmount() <= RewardConstants.SECOND_LIMIT) {
			return Math.round(t.getTransactionAmount() - RewardConstants.LIMIT);
		} else if (t.getTransactionAmount() > RewardConstants.SECOND_LIMIT) {
			return Math.round(t.getTransactionAmount() - RewardConstants.SECOND_LIMIT) * 2
					+ (RewardConstants.SECOND_LIMIT - RewardConstants.LIMIT);
		} else
			return 0l;

	}

	public Timestamp getDateBasedOnOffSetDays(int days) {
		return Timestamp.valueOf(LocalDateTime.now().minusDays(days));
	}

}
