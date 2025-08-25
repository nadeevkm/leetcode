class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        # can do it as DP[3][len(prices)], or use only 3 vals, cause every step we will need only prev column
        prevHold = -prices[0] # max hold state in day one (i.e. after buy)
        prevEmpty = 0
        prevCooldown = 0
        for day in range(1, len(prices)):            
            currEmpty = max(prevCooldown, prevEmpty)
            currCooldown = prevHold + prices[day]
            currHold = max(prevHold, prevEmpty - prices[day])

            prevHold = currHold
            prevEmpty = currEmpty
            prevCooldown = currCooldown
        return max(prevEmpty, prevCooldown)
