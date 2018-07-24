package rs.ac.uns.ftn.mykeepserver.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "widget")
public class Widget implements Serializable {

	private static final long serialVersionUID = 8405975872512623038L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false , unique = true)
	private int id;

	@Column(name = "title")
	private String title;
	
	@Lob
	@Column(name = "content")
	private String content;
	
	@CreationTimestamp
	@Column(name = "date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@Column(name = "drag_and_drop")
	private boolean dragAndDrop;
	
	@Column(name = "resizable")
	private boolean resizable;
	
	@Column(name = "x")
	private int x;

	@Column(name = "y")
	private int y;

	@Column(name = "w")
	private int w;

	@Column(name = "h")
	private int h;

	@Column(name = "xsm")
	private int xSm;
	
	@Column(name = "ysm")
	private int ySm;
	
	@Column(name = "wsm")
	private int wSm;
	
	@Column(name = "hsm")
	private int hSm;
	
	@Column(name = "xmd")
	private int xMd;
	
	@Column(name = "ymd")
	private int yMd;
	
	@Column(name = "wmd")
	private int wMd;
	
	@Column(name = "hmd")
	private int hMd;
	
	@Column(name = "xlg")
	private int xLg;
	
	@Column(name = "ylg")
	private int yLg;
	
	@Column(name = "wlg")
	private int wLg;
	
	@Column(name = "hlg")
	private int hLg;
	
	@Column(name = "xxl")
	private int xXl;
	
	@Column(name = "yxl")
	private int yXl;
	
	@Column(name = "wxl")
	private int wXl;
	
	@Column(name = "hxl")
	private int hXl;

	@ManyToOne
	@JoinColumn(name = "dashboard_id", referencedColumnName = "id", nullable = false)
	private Dashboard dashboard;
	
	
	public Widget() {
		super();
	}

	public Widget(String title, String content, Date date, boolean dragAndDrop, boolean resizable, int x, int y, int w,
			int h, int xSm, int ySm, int wSm, int hSm, int xMd, int yMd, int wMd, int hMd, int xLg, int yLg, int wLg,
			int hLg, int xXl, int yXl, int wXl, int hXl) {
		super();
		this.title = title;
		this.content = content;
		this.date = date;
		this.dragAndDrop = dragAndDrop;
		this.resizable = resizable;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.xSm = xSm;
		this.ySm = ySm;
		this.wSm = wSm;
		this.hSm = hSm;
		this.xMd = xMd;
		this.yMd = yMd;
		this.wMd = wMd;
		this.hMd = hMd;
		this.xLg = xLg;
		this.yLg = yLg;
		this.wLg = wLg;
		this.hLg = hLg;
		this.xXl = xXl;
		this.yXl = yXl;
		this.wXl = wXl;
		this.hXl = hXl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isDragAndDrop() {
		return dragAndDrop;
	}

	public void setDragAndDrop(boolean dragAndDrop) {
		this.dragAndDrop = dragAndDrop;
	}

	public boolean isResizable() {
		return resizable;
	}

	public void setResizable(boolean resizable) {
		this.resizable = resizable;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getxSm() {
		return xSm;
	}

	public void setxSm(int xSm) {
		this.xSm = xSm;
	}

	public int getySm() {
		return ySm;
	}

	public void setySm(int ySm) {
		this.ySm = ySm;
	}

	public int getwSm() {
		return wSm;
	}

	public void setwSm(int wSm) {
		this.wSm = wSm;
	}

	public int gethSm() {
		return hSm;
	}

	public void sethSm(int hSm) {
		this.hSm = hSm;
	}

	public int getxMd() {
		return xMd;
	}

	public void setxMd(int xMd) {
		this.xMd = xMd;
	}

	public int getyMd() {
		return yMd;
	}

	public void setyMd(int yMd) {
		this.yMd = yMd;
	}

	public int getwMd() {
		return wMd;
	}

	public void setwMd(int wMd) {
		this.wMd = wMd;
	}

	public int gethMd() {
		return hMd;
	}

	public void sethMd(int hMd) {
		this.hMd = hMd;
	}

	public int getxLg() {
		return xLg;
	}

	public void setxLg(int xLg) {
		this.xLg = xLg;
	}

	public int getyLg() {
		return yLg;
	}

	public void setyLg(int yLg) {
		this.yLg = yLg;
	}

	public int getwLg() {
		return wLg;
	}

	public void setwLg(int wLg) {
		this.wLg = wLg;
	}

	public int gethLg() {
		return hLg;
	}

	public void sethLg(int hLg) {
		this.hLg = hLg;
	}

	public int getxXl() {
		return xXl;
	}

	public void setxXl(int xXl) {
		this.xXl = xXl;
	}

	public int getyXl() {
		return yXl;
	}

	public void setyXl(int yXl) {
		this.yXl = yXl;
	}

	public int getwXl() {
		return wXl;
	}

	public void setwXl(int wXl) {
		this.wXl = wXl;
	}

	public int gethXl() {
		return hXl;
	}

	public void sethXl(int hXl) {
		this.hXl = hXl;
	}

	public Dashboard getDashboard() {
		return dashboard;
	}

	public void setDashboard(Dashboard dashboard) {
		this.dashboard = dashboard;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((dashboard == null) ? 0 : dashboard.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (dragAndDrop ? 1231 : 1237);
		result = prime * result + h;
		result = prime * result + hLg;
		result = prime * result + hMd;
		result = prime * result + hSm;
		result = prime * result + hXl;
		result = prime * result + id;
		result = prime * result + (resizable ? 1231 : 1237);
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + w;
		result = prime * result + wLg;
		result = prime * result + wMd;
		result = prime * result + wSm;
		result = prime * result + wXl;
		result = prime * result + x;
		result = prime * result + xLg;
		result = prime * result + xMd;
		result = prime * result + xSm;
		result = prime * result + xXl;
		result = prime * result + y;
		result = prime * result + yLg;
		result = prime * result + yMd;
		result = prime * result + ySm;
		result = prime * result + yXl;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Widget other = (Widget) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (dashboard == null) {
			if (other.dashboard != null)
				return false;
		} else if (!dashboard.equals(other.dashboard))
			return false;
	
		if (dragAndDrop != other.dragAndDrop)
			return false;
		if (h != other.h)
			return false;
		if (hLg != other.hLg)
			return false;
		if (hMd != other.hMd)
			return false;
		if (hSm != other.hSm)
			return false;
		if (hXl != other.hXl)
			return false;
		if (id != other.id)
			return false;
		if (resizable != other.resizable)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (w != other.w)
			return false;
		if (wLg != other.wLg)
			return false;
		if (wMd != other.wMd)
			return false;
		if (wSm != other.wSm)
			return false;
		if (wXl != other.wXl)
			return false;
		if (x != other.x)
			return false;
		if (xLg != other.xLg)
			return false;
		if (xMd != other.xMd)
			return false;
		if (xSm != other.xSm)
			return false;
		if (xXl != other.xXl)
			return false;
		if (y != other.y)
			return false;
		if (yLg != other.yLg)
			return false;
		if (yMd != other.yMd)
			return false;
		if (ySm != other.ySm)
			return false;
		if (yXl != other.yXl)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Widget [id=" + id + ", title=" + title + ", content=" + content + ", dashboard=" + dashboard.getDashboardStatus() + "]";
	}

}
