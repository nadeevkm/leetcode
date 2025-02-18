from typing import Optional


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def isValidBST(self, root: Optional[TreeNode]) -> bool:
        def dfs(root, leftBound, rightBound):
            if root is None:
                return True
            isValid = rightBound > root.val > leftBound
            if not isValid:
                return False
            leftIsValid = dfs(root.left, leftBound, root.val)
            if not leftIsValid:
                return False
            rightIsValid = dfs(root.right, root.val, rightBound)
            if not rightIsValid:
                return False
            return True

        return dfs(root, float('-inf'), float('inf'))

