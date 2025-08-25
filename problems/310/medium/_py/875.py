class Solution:
    def minEatingSpeed(self, piles: List[int], h: int) -> int:
        def canEat(speed, piles, time):
            total = 0
            for count in piles:
                total += math.ceil(count / speed)
            return total <= time

        l = 1
        r = max(piles)

        while l < r:
            m = l + (r - l) // 2
            if canEat(m, piles, h):
                r = m
            else:
                l = m + 1
                
        return l
