class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> ans = new LinkedList<>();

        Map<String, Set<String>> adjMap = new HashMap<>();
        for (var accList : accounts){
            var firstAcc = accList.get(1);
            adjMap.computeIfAbsent(firstAcc, k -> new HashSet<>());
            for (int i = 2; i < accList.size(); i++){
                var acc = accList.get(i);
                adjMap.get(firstAcc).add(acc);
                adjMap.computeIfAbsent(acc, k -> new HashSet<>())
                        .add(firstAcc);
            }
        }

        for (var accList : accounts){
            var name = accList.get(0);
            var mergedAcc = dfs(accList.get(1), adjMap);
            if (!mergedAcc.isEmpty()){
                Collections.sort(mergedAcc);
                var res = new LinkedList<String>();
                res.add(name);
                res.addAll(mergedAcc);
                ans.add(res);
            }
        }

        return ans;
    }

    private List<String> dfs(String currAcc, Map<String, Set<String>> adjMap){
        List<String> linkedAcc = new LinkedList<>();
        if (!adjMap.containsKey(currAcc)){
            return linkedAcc;
        }

        linkedAcc.add(currAcc);
        var neighbours = adjMap.get(currAcc);
        adjMap.remove(currAcc);

        for (var neigh : neighbours){
            linkedAcc.addAll(dfs(neigh, adjMap));
        }

        return linkedAcc;
    }
}