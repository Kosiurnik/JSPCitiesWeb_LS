package utils;

public class Paginator {
    private final int sizeCollection;
    private final int sizePage;
    private int currentPage;

    public Paginator(int sizeCollection, int sizePage) {
        this.sizeCollection = sizeCollection;
        this.sizePage = sizePage;
        this.currentPage = 0;
    }

    public int getStartIndex(){
        return sizePage*currentPage;
    }

    public int getEndIndex(){
        return getStartIndex()+sizePage-1 < sizeCollection ? getStartIndex()+sizePage-1 : sizeCollection-1;
    }

    public void nextPage(){
        currentPage += ((currentPage+1) < getPagesCount()) ? 1 : 0;
    }

    public void previousPage(){
        currentPage -= (currentPage > 0) ? 1 : 0;
    }

    public int getPagesCount(){
        return sizeCollection/sizePage + (sizeCollection % sizePage == 0 ? 0 : 1);
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }
}
