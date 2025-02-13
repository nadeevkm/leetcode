class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def goodNodes(self, root: TreeNode) -> int:
        def goodNodes(self, root: TreeNode, maxSoFar: int) -> int:
            if root is None:
                return 0
            count = 0
            if maxSoFar <= root.val:
                count += 1
                maxSoFar = root.val
            count += goodNodes(self, root.left, maxSoFar)
            count += goodNodes(self, root.right, maxSoFar)
            return count

        return goodNodes(self, root, root.val)
