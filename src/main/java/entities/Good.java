package entities;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Good {
    private String author;
    private String titleOfGood;
    private String description;
    private int price;
    private List<String> keyWords;
    private List<String> categories;

    public Good(String author, String titleOfGood, String description, int price, List<String> categories) {
        this.author = author;
        this.titleOfGood = titleOfGood;
        this.description = description;
        this.price = price;
        this.categories = categories;
        setKeyWords();
    }

    public Good(String author, String titleOfGood, String description, int price, List<String> keyWords, List<String> categories) {
        this.author = author;
        this.titleOfGood = titleOfGood;
        this.description = description;
        this.price = price;
        this.keyWords = keyWords;
        this.categories = categories;
    }

    public Good() {

    }

    private void setKeyWords() {
        this.keyWords = Arrays.stream(titleOfGood.split("[. ]")).collect(Collectors.toList());
        this.keyWords.addAll(Arrays.stream(author.split("[,]")).collect(Collectors.toList()));
        this.keyWords.removeIf(word -> word.length() < 3);
    }

    public String getKeyWordsStr() {
        StringBuilder sb = new StringBuilder();
        keyWords.forEach(word->{
            sb.append(word).append(",");
        });
        return sb.toString();
    }

    public String getCategoriesStr() {
        StringBuilder sb = new StringBuilder();
        categories.forEach(categ->{
            sb.append(categ).append(",");
        });
        return sb.toString();
    }

    public String getTitleOfGood() {
        return titleOfGood;
    }

    public void setTitleOfGood(String titleOfGood) {
        this.titleOfGood = titleOfGood;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<String> keyWords) {
        this.keyWords = keyWords;
    }

    public List<String> getCategories() {
        return categories;
    }

    private void setCategories(String categories) {
        this.categories = Arrays.stream(categories.split("[,]")).collect(Collectors.toList());
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "entities.Good{" +
                "\n nameOfGood='" + titleOfGood + '\'' +
                "\n author='" + author + '\''+
                "\n description='" + description + '\'' +
                "\n price=" + price +
                "\n keyWords=" + keyWords +
                "\n categories=" + categories +
                '}';
    }
}
