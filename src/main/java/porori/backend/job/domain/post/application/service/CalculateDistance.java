package porori.backend.job.domain.post.application.service;

// LocationUtils.java

public class CalculateDistance {

    private static final double EARTH_RADIUS = 6371.0;  // 지구의 반경(킬로미터)

    public static class BoundingBox {
        public double minLatitude;
        public double maxLatitude;
        public double minLongitude;
        public double maxLongitude;
    }

    public static BoundingBox calculateBoundingBox(double latitude, double longitude, double distanceKm) {
        double latitudeDegree = distanceKm / 111.0;
        double longitudeDegree = distanceKm / (111.0 * Math.cos(Math.toRadians(latitude)));

        BoundingBox box = new BoundingBox();
        box.minLatitude = latitude - latitudeDegree;
        box.maxLatitude = latitude + latitudeDegree;
        box.minLongitude = longitude - longitudeDegree;
        box.maxLongitude = longitude + longitudeDegree;

        return box;
    }

    public static double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }
}
