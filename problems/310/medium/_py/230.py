# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def kthSmallest(self, root: Optional[TreeNode], k: int) -> int:
        res = []
        
        def inOrderDfs(root):
            if root is None:
                return False

            if inOrderDfs(root.left):
                return True

            res.append(root.val)
            if len(res) == k:
                return True

            if inOrderDfs(root.right):
                return True
        
        inOrderDfs(root)
        return res[-1]
