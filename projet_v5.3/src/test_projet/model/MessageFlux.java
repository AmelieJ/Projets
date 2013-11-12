
package test_projet.model;


public class MessageFlux {

	public String copyright;
	public String title;
	public String description;
	public String link;
	public String author;
	public String guid;
	public String pubDate;
	public String Item;
	public String Channel;

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getpubDate() {
		return pubDate;
	}

	public void setpubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getItem() {
		return Item;
	}

	public void setItem(String Item) {
		this.Item = Item;
	}

	public String getChannel() {
		return Channel;
	}

	public void setChannel(String Channel) {
		this.Channel = Channel;
	}

	@Override
	public String toString() {
		return "FeedMessage [copyright =" + copyright + ", title=" + title + ", description=" + description
				+ ", link=" + link + ", author=" + author + ", guid=" + guid
				+ " pubDate=" + pubDate + "channel=" + Channel + "Item" + Item
				+ "]";
	}

}