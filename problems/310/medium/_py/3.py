class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        l = 0
        chars = {}
        # winSize = 0
        for r, ch in enumerate(s):
            if ch in chars:
                chars[ch] += 1
            else:
                chars[ch] = 1

            # or use chars[ch] = chars.get(ch, 0) + 1
            # or in the beginning chars = defaultdict(int)

            if len(chars) < r - l + 1:  # i.e. duplicates found
                chToPop = s[l]
                chars[chToPop] -= 1
                if chars[chToPop] == 0:
                    chars.pop(chToPop)
                l += 1

            # winSize = r - l + 1
        return r - l + 1  # winSize
