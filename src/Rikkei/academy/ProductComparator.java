package Rikkei.academy;

import java.util.Comparator;

public class ProductComparator implements Comparator<Product> {
    private int sortOption;

    public int getSortOption() {
        return sortOption;
    }

    public void setSortOption(int sortOption) {
        this.sortOption = sortOption;
    }

    @Override
    public int compare(Product o1, Product o2) {
        switch (sortOption) {
            case 1:
                return o1.getProductPrice() - o2.getProductPrice();
            case 2:
                return o2.getProductPrice() - o1.getProductPrice();
            default:
                return 0;
        }
    }
}
