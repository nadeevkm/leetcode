import random

# LOOPS
n = 5
while n < 5:
    print(n)
    n += 1
# Looping from i = 0 to i = 4
for i in range(5):
    print(i)
# Looping from i = 2 to i = 5
for i in range(2, 6):
    print(i)
# Looping from i = 5 to i = 2
for i in range(5, 1, -1):
    print(i)

#  LIST
print("- LISTS -")
arr = [1, 2, 3]
print(arr)
# initialization
arr = [4] * 10
print(arr)
arr = [i for i in range(5)]
print(arr)
# 2-D lists
arr = [[0] * 4 for i in range(4)]
print(arr)

# LIST is basically alos a stack in PYTHON
arr.append(6)  # analogue st.push()
arr.pop()
arr[-1]  # st[-1] is analogue to st.peek() in java


# sorting
arr = ["bobby", "alice", "joe"]
arr.sort()
print(arr)

# custom sorting
arr.sort(key=lambda x: len(x))
print(arr)

# iteration
for i, el in enumerate(arr):
    print(i, f"element: {el}")

# STRINGS
print("\n - STRINGS -")
arr = ["bobby", "alice", "joe"]
print("".join(arr))

# QUEUE
print("\n - QUEUE -")
from collections import deque
q = deque()
q.append(1)
q.appendleft(2)
print(q.popleft())
print(q.pop())

# SET
print("\n - SET -")
s = set()
print(s)
s = set(arr)
print(s)


# HASHMAP
print("\n - HASHMAP -")
map = {"firsVal": 2, "b": 1}
print(map)
map = dict()
print(map)
map["alice"] = 4
print(map)
map.pop("alice")
# advanced initializaion
map = {i: 2*i for i in range(3)}
print(map)

for key in map:
    print(key, map)
for val in map.values():
    print(val)
for k,v in map.items():
    print(k, v)

# HEAPS
print("\n - HEAPS -")
import heapq
minHeap = [50, 17, 123]
heapq.heapify(minHeap)
heapq.heappush(minHeap, 3)
heapq.heappush(minHeap, 2)
# min is minHeap[0]
while len(minHeap):
    print(heapq.heappop(minHeap))

# MAX HEAP should be implemented with (-1) multiplication
maxHeap = []
heapq.heappush(maxHeap, -1 * 3)
heapq.heappush(maxHeap, -1 * 2)
max = maxHeap[0] * (-1)
print(f"max -> {max}")

# MATH, CONVERTION
print("\n - MATH, CONVERTION -")

print(int("123") + int("123"))
print(str(123) + str(123))

# Division is decimal by default
print(5 / 2)
# Double slash rounds down
print(5 // 2)
# CAREFUL: most languages round towards 0 by default
# So negative numbers will round down
print(-3 // 2)
# A workaround for rounding towards zero
# is to use decimal division and then convert to int.
print(int(-3 / 2))
# Modding is similar to most languages
print(10 % 3)
# Except for negative values
print(-10 % 3)
# To be consistent with other languages modulo
import math
# from multiprocessing import heap
print(math.fmod(-10, 3))
# More math helpers
print(math.floor(3 / 2))
print(math.ceil(3 / 2))
print(math.sqrt(2))
print(math.pow(2, 3))

# Max / Min Int
float("inf")
float("-inf")

# Python numbers are infinite so they never overflow
print(math.pow(2, 200))

# But still less than infinity
print(math.pow(2, 200) < float("inf"))


# python int has unlimited precise!
# so in bit-manipulations check sign bit separately
# check and consider sign-bit in python num representation
ans = 2147483648
if ans & 1 << 31:  # i.e. != 0
    ans -= 2 ** 32



# CLASSES
class MyClass:
    # Constructor
    def __init__(self, nums):
        # Create member variables
        self.nums = nums
        self.size = len(nums)

    # self key word required as param
    def getLength(self):
        return self.size

    def getDoubleLength(self):
        return 2 * self.getLength()

myObj = MyClass([1, 2, 3])
print(myObj.getLength())
print(myObj.getDoubleLength())




# Other special features









print(f"Number - {random.randint(1, 141)}") # including both ends