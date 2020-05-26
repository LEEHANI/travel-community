package project.board.entity.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import project.board.domain.dto.GpsDecimal;
import project.board.entity.Article;
import project.board.enums.Category;
import project.board.enums.Nation;

@Data
public class ArticleDetail {
	private Long articleId;
	private Category category;
	private Nation nation;
	private String title;
	private String content;
	private int hit;
	private int good;
	private LocalDateTime createdDate;
	private LocalDateTime updateDate;
	
	private List<GpsDecimal> gpsInfo;
	
	private int liked;
	private int bookmarked;
	
	public ArticleDetail(Article article, int liked, int bookmarked) {
		articleId = article.getId();
		category = article.getCategory();
		nation = article.getNation();
		title = article.getTitle();
		content = article.getContent();
		hit = article.getHit();
		good = article.getGood();
		createdDate = article.getCreatedDate();
		updateDate = article.getLastModifiedDate();
		this.liked = liked;
		this.bookmarked = bookmarked;
		
		gpsInfo = article.getPostFiles().stream()
					.map(o-> new GpsDecimal(o.getLatitude(), o.getLongitude()))
					.collect(Collectors.toList());
	}
}
