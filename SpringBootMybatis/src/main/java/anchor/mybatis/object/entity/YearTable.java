package anchor.mybatis.object.entity;

import lombok.Data;

import java.time.Year;

/**
 * @author Anchor
 */
@Data
public class YearTable {

    private String yearOne;

    private String yearTwo;

    private Integer yearThree;

    private Integer yearFour;

    private Integer yearFive;

    private Year yearSix;

    public YearTable() {
        this.yearOne = "2020";
        this.yearTwo = "68";
        this.yearThree = 2020;
        this.yearFour = 68;
        this.yearFive = 89;
        this.yearSix = Year.now();
    }
}
