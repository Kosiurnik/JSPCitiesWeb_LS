package utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class PaginatorTest {

    @Test
    public void getStartIndex() {
        Paginator test = new Paginator(15,10);
        assertEquals(0,test.getStartIndex());
        test.nextPage();
        assertEquals(10,test.getStartIndex());
    }

    @Test
    public void getEndIndex() {
        Paginator test = new Paginator(15,10);
        assertEquals(9,test.getEndIndex());
        test.nextPage();
        assertEquals(14,test.getEndIndex());
    }

    @Test
    public void getPagesCount() {
        Paginator test = new Paginator(15,10);
        assertEquals(2,test.getPagesCount());
        test = new Paginator(50,10);
        assertEquals(5,test.getPagesCount());
        test = new Paginator(49,10);
        assertEquals(5,test.getPagesCount());
        test = new Paginator(51,10);
        assertEquals(6,test.getPagesCount());
    }

    @Test
    public void nextPage() {
        Paginator test = new Paginator(15,10);
        assertEquals(0,test.getCurrentPage());
        test.nextPage();
        assertEquals(1,test.getCurrentPage());
        test.nextPage();
        assertEquals(1,test.getCurrentPage());
    }

    @Test
    public void previousPage() {
        Paginator test = new Paginator(21,10);
        test.setCurrentPage(2);
        assertEquals(2,test.getCurrentPage());
        test.previousPage();
        assertEquals(1,test.getCurrentPage());
        test.previousPage();
        assertEquals(0,test.getCurrentPage());
        test.previousPage();
        assertEquals(0,test.getCurrentPage());
    }
}