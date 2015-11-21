package Main.Model;

/**
 * Created by nikitaborodulin on 31/10/15.
 */
public class Article extends Publication implements Tuple {
    public String journal;
    public String month;
    public String volume;
    public String number;

    public Article() {
    }

    public Article(String title, String year, String url, String journal, String month, String volume, String number) {
        this.title = title;
        this.year = year;
        this.url = url;
        this.journal = journal;
        this.month = month;
        this.volume = volume;
        this.number = number;
    }

    public Article(String[] attributes) {
        this.journal = attributes[0];
        this.month = attributes[1];
        this.volume = attributes[2];
        this.number = attributes[3];
    }
}
