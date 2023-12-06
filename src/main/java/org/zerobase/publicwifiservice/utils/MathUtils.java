package org.zerobase.publicwifiservice.utils;

public class MathUtils {
    public static Double calculateDistance(
            final double lat1,
            final double lon1,
            final double lat2,
            final double lon2
    ) {
        // 지구 반지름 (단위: km)
        double R = 6371.0;

        // 위도 및 경도를 라디안으로 변환
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        // Haversine 공식 계산
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // 거리 계산
        return roundToNDecimalPlaces(R * c, 4);
    }

    public static Double roundToNDecimalPlaces(double value, int decimalPlaces) {
        // 10의 거듭제곱을 이용한 반올림
        double scale = Math.pow(10, decimalPlaces);
        return Math.round(value * scale) / scale;
    }
}
