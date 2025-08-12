class Solution:
    def findMinArrowShots(self, points: List[List[int]]) -> int:
        arrCount = 0
        points.sort()

        currShotArea = points[0]
        for interv in points:
            if interv[0] <= currShotArea[1]:  # try to include as many balloons in one shot as possible, i.e. search for the intersection of balloons
                currShotArea[0] = max(currShotArea[0], interv[0])
                currShotArea[1] = min(currShotArea[1], interv[1])
            else:
                arrCount += 1  # make a shot, and search for the next best spot
                currShotArea = interv

        arrCount += 1  # don't forget to make the last shot, cause previously we basically just search it

        return arrCount
