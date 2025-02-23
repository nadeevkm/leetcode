class Solution:
    def characterReplacement(self, s: str, k: int) -> int:
        l = 0
        chars = {}
        mostFrChFreq = 0
        for r in range(len(s)):
            chToAdd = s[r]
            chars[chToAdd] = chars.get(chToAdd, 0) + 1
            chFr = chars[chToAdd]
            mostFrChFreq = max(mostFrChFreq, chFr)

            if r - l + 1 > mostFrChFreq + k:  # size of window is > than k + freq of most freq char => wis is not valid => need to schrink
                chToPop = s[l]
                chars[chToPop] -= 1
                l += 1

        return r - l + 1
