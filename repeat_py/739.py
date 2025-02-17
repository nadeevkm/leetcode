from typing import List


class SolutionBackward:
    def dailyTemperatures(self, temperatures: List[int]) -> List[int]:
        n = len(temperatures)
        st = []
        res = [0] * n
        for i in range(n - 1, -1, -1):
            while st and temperatures[st[-1]] < temperatures[i]: # st[-1] is analogue to st.peek() in java
                st.pop()
            res[i] = st[-1] - i if st else 0
            st.append(i)
        return res

class SolutionForward:
    def dailyTemperatures(self, temperatures: List[int]) -> List[int]:
        st = []
        res = [0] * len(temperatures)
        for i, t in enumerate(temperatures):
            while st and temperatures[st[-1]] < temperatures[i]:
                lesserValInd = st.pop()
                res[lesserValInd] = i - lesserValInd
            st.append(i)
        return res

SolutionBackward().dailyTemperatures([73, 74, 75, 71, 69, 72, 76, 73])

