package project.board.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import project.board.domain.dto.GpsDecimal;

@Getter
@Entity
@EqualsAndHashCode(callSuper = false)
public class UploadFile extends BaseTimeEntity{
	@Id @GeneratedValue
	private Long id;
	private String originFileName;
	private String saveFileName;
	private String dirPath;
	private String fileName;
	private String contentType;
	private Long size;
	
	private Double latitude;
	private Double longitude;
	
	public void seperateDirAndFile(String rootPath, String filePath) {
		this.fileName = filePath.substring(filePath.lastIndexOf('/')+1, filePath.length());
		this.dirPath = filePath.substring(rootPath.length()+1 , filePath.lastIndexOf('/')+1);
	}
	
	public void setGps(GpsDecimal gpsDecimal) {
		this.latitude = gpsDecimal.getLatitude();
		this.longitude = gpsDecimal.getLongitude();
	}
}
