package com.nyce.moves.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nyce.moves.common.ApplicationConstants;
import com.nyce.moves.model.FileObject;
import com.nyce.moves.model.ResponseTemplate;
import com.nyce.moves.model.UploadFileResponse;
import com.nyce.moves.model.ResponseTemplate.StatusEnum;
import com.nyce.moves.service.AmazonClient;

@RestController
public class UploadController {

	private AmazonClient amazonClient;

	@Autowired
	UploadController(AmazonClient amazonClient) {
		this.amazonClient = amazonClient;
	}

	@RequestMapping("/service-status")
	public ResponseTemplate uploadStatus() {

		ResponseTemplate responseTemplate = new ResponseTemplate();
		responseTemplate.setCode(ApplicationConstants.SUCCESS_CODE_11001);
		responseTemplate.setStatus(StatusEnum.SUCCESS);
		responseTemplate.setMessage("File upload service is running");

		return responseTemplate;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/players/{playerId}/uploadFile")
	public UploadFileResponse uploadFile(@RequestPart(value = "file") MultipartFile file, @PathVariable Long playerId, @RequestHeader String fileType) {

		UploadFileResponse uploadFileResponse = new UploadFileResponse();
		uploadFileResponse.setCode(ApplicationConstants.SUCCESS_CODE_11001);
		uploadFileResponse.setStatus(UploadFileResponse.StatusEnum.SUCCESS);
		uploadFileResponse.setMessage("File has been loaded successfully");
		FileObject fileObject = new FileObject();
		fileObject = this.amazonClient.uploadFile(file, playerId, fileType, fileObject);
		uploadFileResponse.setFileObject(fileObject);

		return uploadFileResponse;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/players/temp/uploadFile")
	public UploadFileResponse uploadTempFile(@RequestPart(value = "file") MultipartFile file, @RequestHeader String fileType) {

		UploadFileResponse uploadFileResponse = new UploadFileResponse();
		uploadFileResponse.setCode(ApplicationConstants.SUCCESS_CODE_11001);
		uploadFileResponse.setStatus(UploadFileResponse.StatusEnum.SUCCESS);
		uploadFileResponse.setMessage("File has been loaded successfully");
		FileObject fileObject = new FileObject();
		fileObject = this.amazonClient.uploadFile(file, 0L, fileType, fileObject);
		uploadFileResponse.setFileObject(fileObject);

		return uploadFileResponse;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteFile")
	public ResponseTemplate deleteFile(@RequestPart(value = "url") String fileUrl) {

		this.amazonClient.deleteFileFromS3Bucket(fileUrl);

		ResponseTemplate responseTemplate = new ResponseTemplate();
		responseTemplate.setCode(ApplicationConstants.SUCCESS_CODE_11001);
		responseTemplate.setStatus(StatusEnum.SUCCESS);
		responseTemplate.setMessage("File has been deleted successfully with url [" + fileUrl + "]");

		return responseTemplate;

	}

}