class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        Car[] cars = new Car[n];
        for (int i = 0; i < n; i++){
            cars[i] = new Car(position[i], speed[i]);
        }
        Arrays.sort(cars, (c1, c2) -> c2.position() - c1.position());

        int res = 1;
        var prev = cars[0];
        double fleetTimeToArrival = (double) (target - prev.position()) / prev.speed; // use double - otherwise same speed cars will form a fleet
        for (var curr : cars){
            double carTimeToArrival = (double) (target - curr.position()) / curr.speed;
            if (carTimeToArrival <= fleetTimeToArrival){
                continue;
            } else { // means curr car won't make it to the prev fleet
                res++; // new fleet
                fleetTimeToArrival = carTimeToArrival;
            }
        }
        return res;
    }

    record Car(int position, int speed) {}
}