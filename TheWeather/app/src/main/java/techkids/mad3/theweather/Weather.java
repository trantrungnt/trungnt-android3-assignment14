package techkids.mad3.theweather;

import java.util.Date;

/**
 * Created by TrungNT on 6/1/2016.
 */
public class Weather {
    private int id;
    private String minTemp;
    private String maxTemp;
    private String mainTemp;

    public String getDescriptionTemp() {
        return descriptionTemp;
    }

    public void setDescriptionTemp(String descriptionTemp) {
        this.descriptionTemp = descriptionTemp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getMainTemp() {
        return mainTemp;
    }

    public void setMainTemp(String mainTemp) {
        this.mainTemp = mainTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    private String descriptionTemp;
    private String createAt;
}
