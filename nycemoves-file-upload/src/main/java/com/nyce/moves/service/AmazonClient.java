package com.nyce.moves.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.nyce.moves.common.ApplicationConstants;
import com.nyce.moves.model.FileObject;

@Service
public class AmazonClient {

	private AmazonS3 s3client;

	@Value("${amazonProperties.endpointUrl}")
	private String endpointUrl;
	@Value("${amazonProperties.bucketName}")
	private String bucketName;
	@Value("${amazonProperties.accessKey}")
	private String accessKey;
	@Value("${amazonProperties.secretKey}")
	private String secretKey;

	@PostConstruct
	private void initializeAmazon() {
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(this.accessKey, this.secretKey);
		this.s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion(Regions.AP_SOUTH_1).build();
	}

	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	private String generateFileName(MultipartFile multiPart) {
		return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
	}

	private void uploadFileTos3bucket(String fileName, File file) {
		s3client.putObject(new PutObjectRequest(bucketName, fileName, file).withCannedAcl(CannedAccessControlList.BucketOwnerFullControl));
	}

	public FileObject uploadFile(MultipartFile multipartFile, Long playerId, String fileType, FileObject fileObject) {

		String fileUrl = "";
		try {
			File file = convertMultiPartToFile(multipartFile);
			String fileName = playerId + "_" + fileType + "_" + generateFileName(multipartFile);
			fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;

			if (fileType != null) {
				if (fileType.equalsIgnoreCase(ApplicationConstants.IMAGE)) {
					fileUrl = playerId + "/" + "images/" + fileName;
				} else if (fileType.equalsIgnoreCase(ApplicationConstants.VIDEO)) {
					fileUrl = playerId + "/" + "videos/" + fileName;
				} else {
					fileUrl = playerId + "/" + fileName;
				}
			}

			uploadFileTos3bucket(fileUrl, file);
			file.delete();

			String finalS3Path = endpointUrl + "/" + bucketName + "/" + fileUrl;
			fileObject.setFinalS3Path(finalS3Path);
			fileObject.setPreSignedUrl(generatePreSignedUrl(bucketName, fileUrl));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileObject;
	}

	public String deleteFileFromS3Bucket(String fileUrl) {
		//String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
		String fileName = fileUrl.substring(fileUrl.indexOf(bucketName + "/") + bucketName.length()+1);
		s3client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
		return "Successfully deleted";
	}

	public String generatePreSignedUrl(String bucketName, String objectName) {

		// Set the presigned URL to expire after one hour.
		java.util.Date expiration = new java.util.Date();
		long expTimeMillis = expiration.getTime();
		expTimeMillis += 1000 * 60 * 60 * 24 * 6;
		expiration.setTime(expTimeMillis);

		GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, objectName).withMethod(HttpMethod.GET).withExpiration(expiration);
		URL url = s3client.generatePresignedUrl(generatePresignedUrlRequest);

		return url.toString();

	}

}
