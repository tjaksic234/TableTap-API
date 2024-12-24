package TableTap.models.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Point {
    private double[] coordinates;
    private String type = "Point";

    public Point(double longitude, double latitude) {
        this.coordinates = new double[]{longitude, latitude};
    }
}
