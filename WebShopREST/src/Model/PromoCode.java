package Model;

import java.util.List;

public class PromoCode extends IDClass{
    
    private String code;

    private String howLongItWorksDate;

    private int ammountOfUsage;

    private float discountPercentage;

    public PromoCode() {}

    public PromoCode(String code, String howLongItWorksDate, int ammountOfUsage,
            float discountPercentage) {
        this.code = code;
        this.howLongItWorksDate = howLongItWorksDate;
        this.ammountOfUsage = ammountOfUsage;
        this.discountPercentage = discountPercentage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHowLongItWorksDate() {
        return howLongItWorksDate;
    }

    public void setHowLongItWorksDate(String howLongItWorksDate) {
        this.howLongItWorksDate = howLongItWorksDate;
    }

    public int getAmmountOfUsage() {
        return ammountOfUsage;
    }

    public void setAmmountOfUsage(int ammountOfUsage) {
        this.ammountOfUsage = ammountOfUsage;
    }

    public float getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public int FromCSV(List<String> values) {
        int i = super.FromCSV(values);
        code = values.get(i++);
        howLongItWorksDate = values.get(i++);
        ammountOfUsage = Integer.parseInt(values.get(i++));
        discountPercentage = Float.parseFloat(values.get(i++));
        return i;
    }

    @Override
    public List<String> ToCSV() {
        List<String> result = super.ToCSV();
		result.add(code);
		result.add(howLongItWorksDate);
		result.add(String.valueOf(ammountOfUsage));
		result.add(String.valueOf(discountPercentage));
		return result;
    }

}
