class Solution:
    def hIndex(self, citations: List[int]) -> int:
        n = len(citations) + 1  # max h-ind is n(papers) + 1 (as it can be zero as well) by definition of 'max papers with..'
        counts = [0] * n

        for i in range(len(citations)):
            if citations[i] >= n:
                counts[n - 1] += 1
            else:
                counts[citations[i]] += 1

        above = 0
        for i in range(n - 1, -1, -1):
            if counts[i] + above >= i:
                return i
            above += counts[i]
        
        return 0
