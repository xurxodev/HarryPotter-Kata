package cart;

public class Book {

    private String title;
    private int price;

    public Book (String title){
        this.title = title;
        this.price = 8;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public boolean equals(Object o){
        if(o == null) return false;
        if(!(o instanceof Book)) return false;

        Book other = (Book) o;
        return this.title == other.getTitle();
    }

    public int hashCode(){
        return title.hashCode();
    }
}
