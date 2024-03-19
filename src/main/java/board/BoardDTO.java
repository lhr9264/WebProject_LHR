package board;

public class BoardDTO {
	
	private String idx;
	private String title;
	private String name;
	private String content;
	private int thumb;
	private int visitcount;
	private java.sql.Date postdate;
	
	
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getThumb() {
		return thumb;
	}
	public void setThumb(int thumb) {
		this.thumb = thumb;
	}
	public int getVisitcount() {
		return visitcount;
	}
	public void setVisitcount(int visitcount) {
		this.visitcount = visitcount;
	}
	public java.sql.Date getPostdate() {
		return postdate;
	}
	public void setPostdate(java.sql.Date postdate) {
		this.postdate = postdate;
	}
}
