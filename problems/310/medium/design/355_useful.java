import java.util.*;

class Twitter {

    Map<Integer, Set<Integer>> subscrByUser;
    Map<Integer, LinkedList<Tweet>> tweetsByUser;

    int sequence;

    public Twitter() {
        subscrByUser = new HashMap<>();
        tweetsByUser = new HashMap<>();
        sequence = 0;
    }

    public void postTweet(int userId, int tweetId) {
        var userList = tweetsByUser.computeIfAbsent(userId, k -> new LinkedList<>());
        userList.addFirst(new Tweet(tweetId, sequence));
        sequence++;
    }

    public List<Integer> getNewsFeed(int userId) {
        var subscriptions = subscrByUser.computeIfAbsent(userId, k -> new HashSet<>());
        var pq = new PriorityQueue<Tweet>(11, (t1, t2) -> t1.sequence - t2.sequence);
        subscriptions.add(userId);
        for (var subscr : subscriptions){
            var tweets = tweetsByUser.computeIfAbsent(subscr, k -> new LinkedList<>());
            int n = 0;
            for (var t : tweets){
                pq.add(t);
                if (pq.size() > 10){
                    pq.poll();
                }
                // optimization for adding
//                if (pq.size() < 10){
//                    pq.add(t);
//                } else {
//                    if (t.sequence < pq.peek().sequence){
//                        break; // means all other tweets of that user are older than the oldest in PQ
//                    } else {
//                        pq.add(t);
//                        pq.poll();
//                    }
//                }
                n++;
                if (n == 10){
                    break;
                }
            }
        }
        var res = new LinkedList<Integer>();
        while (!pq.isEmpty()){
            res.addFirst(pq.poll().id);
        }
        return res;
    }

    public void follow(int followerId, int followeeId) {
        var subscriptions = subscrByUser.computeIfAbsent(followerId, k -> new HashSet<>());
        subscriptions.add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        var subscriptions = subscrByUser.computeIfAbsent(followerId, k -> new HashSet<>());
        subscriptions.remove(followeeId);
    }

    class Tweet{
        int id;
        int sequence;

        public Tweet(int id, int sequence){
            this.id = id;
            this.sequence = sequence;
        }
    }
}



class TwitterInterestingApproach { // each tweet as a node in linked list

    Map<Integer, Set<Integer>> subscrByUser;
    Map<Integer, Tweet> tweetsByUser; // just header of tweets list

    int sequence;

    public TwitterInterestingApproach() {
        subscrByUser = new HashMap<>();
        tweetsByUser = new HashMap<>();
        sequence = 0;
    }

    public void postTweet(int userId, int tweetId) {
        var usersLastTweet = tweetsByUser.get(userId);
        var userNewTweet = new Tweet(tweetId, sequence, usersLastTweet);
        tweetsByUser.put(userId, userNewTweet);
        sequence++;
    }

    public List<Integer> getNewsFeed(int userId) {
        var subscriptions = subscrByUser.computeIfAbsent(userId, k -> new HashSet<>());
        var pq = new PriorityQueue<Tweet>((t1, t2) -> t2.sequence - t1.sequence); // max heap
        subscriptions.add(userId);
        for (var subscr : subscriptions){
            var lastTweet = tweetsByUser.get(subscr);
            if (lastTweet == null){
                continue;
            }
            pq.add(lastTweet);
//            if (pq.size() > 10){   -- we really can't do it, cause in max heap we can lose the necessary max value during poll
//                pq.poll();
//            }
        }
        // actually better create PQ here, after collection a collection of all last tweets and heapify it. (O(n) instead of 0(n*logN))
        // but in java its impossible to specify both Comparator and Collection for creation
        // so either add interface Comparable to used class, or use (-1)*values to imitate max heap
        var res = new LinkedList<Integer>();
        int n = 0;
        while (!pq.isEmpty()){  // cool stuff, self-ordering process
            var latestTweet = pq.poll();
            res.add(latestTweet.id);
            if (latestTweet.prevTweet != null){
                pq.add(latestTweet.prevTweet);
            }
            n++;
            if (n == 10){
                break;
            }
        }
        return res;
    }

    public void follow(int followerId, int followeeId) {
        var subscriptions = subscrByUser.computeIfAbsent(followerId, k -> new HashSet<>());
        subscriptions.add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        var subscriptions = subscrByUser.computeIfAbsent(followerId, k -> new HashSet<>());
        subscriptions.remove(followeeId);
    }

    class Tweet{
        int id;
        int sequence;
        Tweet prevTweet;

        public Tweet(int id, int sequence, Tweet prevTweet){
            this.id = id;
            this.sequence = sequence;
            this.prevTweet = prevTweet;
        }
    }
}



class TwitterRepetition {

    int timeSequence = 0;
    Map<Integer, Set<Integer>> userSubscriptions;
    Map<Integer, Tweet> latestTweet;

    public TwitterRepetition() {
        userSubscriptions = new HashMap<>();
        latestTweet = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        var tweet = new Tweet(tweetId, ++timeSequence);
        tweet.prevTweet = latestTweet.get(userId);
        latestTweet.put(userId, tweet);
    }

    public List<Integer> getNewsFeed(int userId) {
        if (userSubscriptions.isEmpty()) {
            var subscr = new HashSet<Integer>();
            subscr.add(userId);
            userSubscriptions.put(userId, subscr);
        }
        PriorityQueue<Tweet> pq = new PriorityQueue<>((t1, t2) -> t2.time - t1.time);
        for (int followee : userSubscriptions.getOrDefault(userId, new HashSet<>())) {
            var tweet = latestTweet.get(followee);
            if (tweet != null) {
                pq.add(tweet);
            }
        }
        LinkedList<Integer> res = new LinkedList<>();
        while (!pq.isEmpty() && res.size() < 10) {
            var tweet = pq.poll();
            res.add(tweet.tweetId);
            if (tweet.prevTweet != null) {
                pq.add(tweet.prevTweet);
            }
        }
        return res;
    }

    public void follow(int followerId, int followeeId) {
        userSubscriptions.computeIfAbsent(followerId, k -> {
                    var set = new HashSet<Integer>();
                    set.add(followerId);
                    return set;
                })
                .add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        var subscr = userSubscriptions.get(followerId);
        if (subscr != null) {
            subscr.remove(followeeId);
        }
    }

    class Tweet {
        int tweetId;
        int time;
        Tweet prevTweet;

        public Tweet(int tweetId, int time) {
            this.tweetId = tweetId;
            this.time = time;
        }
    }
}
