import random

def quickSort(nums):
    def partition(nums, start, end) -> int:
        pInd = random.randrange(start, end + 1)
        nums[end], nums[pInd] = nums[pInd], nums[end]
        p = start
        for i in range(start, end):
            if nums[i] < nums[end]:
                nums[i], nums[p] = nums[p], nums[i]
                p += 1
        nums[end], nums[p] = nums[p], nums[end]
        return p

    def quickSort(nums, start, end):
        if start >= end:
            return
        else:
            p = partition(nums, start, end)
            quickSort(nums, start, p - 1)
            quickSort(nums, p + 1, end)

    quickSort(nums, 0, len(nums) - 1)


nums = [14, -1, 0, 5, -6, 100, 12]
quickSort(nums)
print(nums)
