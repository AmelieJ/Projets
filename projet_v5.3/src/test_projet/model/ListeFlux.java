
package test_projet.model;

import java.util.ArrayList;
import java.util.List;

public class ListeFlux {

  public final String title;
  final String link;
  final String description;
  final String language;
  final String copyright;
  final String pubDate;

  public final static List<MessageFlux> entries = new ArrayList<MessageFlux>();

  public ListeFlux(String title, String link, String description, String language, String copyright, String pubDate) {
    this.title = title;
    this.link = link;
    this.description = description;
    this.language = language;
    this.copyright = copyright;
    this.pubDate = pubDate;
  }

  public List<MessageFlux> getMessages() {
    return entries;
  }

  public String getTitle() {
    return title;
  }

  public String getLink() {
    return link;
  }

  public String getDescription() {
    return description;
  }

  public String getLanguage() {
    return language;
  }

  public String getCopyright() {
    return copyright;
  }

  public String getPubDate() {
    return pubDate;
  }

  @Override
  public String toString() {
    return "ListeFlux [copyright=" + copyright + ", description=" + description
        + ", language=" + language + ", link=" + link + ", pubDate="
        + pubDate + ", title=" + title + "]";
  }

} 