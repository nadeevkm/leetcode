class SolutionTwoStack:
    def checkValidString(self, s: str) -> bool:
        open = []
        ast = []
        for i, ch in enumerate(s):
            if ch == '(':
                open.append(i)
            if ch == ')':
                if open:
                    open.pop()
                elif ast:
                    ast.pop()
                else:
                    return False
            if ch == '*':
                ast.append(i)
        while open and ast and ast[len(ast) - 1] > open[len(open) - 1]:
            ast.pop()
            open.pop()
        return len(open) == 0


class SolutionDfsMem:
    def checkValidString(self, s: str) -> bool:
        n = len(s) + 1
        mem = [[-1 for r in range(n)] for c in range(n)]

        def dfs(s, ind, brBalance):
            if brBalance < 0:
                return False
            if mem[ind][brBalance] != -1:
                return mem[ind][brBalance] == 1
            if ind == len(s):
                isValid = brBalance == 0
                mem[ind][brBalance] = 1 if isValid else 0
                return isValid
            isValid = False
            if s[ind] == '(':
                isValid = dfs(s, ind + 1, brBalance + 1)
            elif s[ind] == ')':
                isValid = dfs(s, ind + 1, brBalance - 1)
            else: # s[ind] == '*'
                asBlank = dfs(s, ind + 1, brBalance)
                asOpen = dfs(s, ind + 1, brBalance + 1)
                asClose = dfs(s, ind + 1, brBalance - 1)
                isValid = asBlank or asOpen or asClose
            mem[ind][brBalance] = 1 if isValid else 0
            return isValid

        return dfs(s, 0, 0)
