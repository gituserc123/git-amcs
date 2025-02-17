package com.aier.cloud.biz.ui.biz.adverse.controller;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.aier.cloud.api.amcs.adverse.domain.Attachment;
import com.aier.cloud.biz.ui.biz.adverse.utils.AesUtil;
import com.aier.cloud.biz.ui.biz.adverse.utils.FileEncryptionHelper;
import com.aier.cloud.biz.ui.biz.adverse.utils.FileValidator;
import com.aier.cloud.biz.ui.biz.adverse.utils.MimeTypeUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.aier.cloud.api.amcs.adverse.condition.AttachmentCondition;
import com.aier.cloud.basic.api.domain.enums.FileEnum;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.api.response.domain.file.FileInfo;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.biz.ui.biz.adverse.feign.AeAttachmentService;
import com.aier.cloud.biz.ui.biz.adverse.feign.FileService;

import static com.aier.cloud.biz.ui.biz.adverse.utils.FileEncryptionHelper.getMultipartFileFromUrl;


@Controller
@RequestMapping("/ui/service/biz/amcs/adverse/aeFile")
public class AeFileController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(AeFileController.class);

	@Autowired
	private FileService fs;
	
	@Autowired
	private AeAttachmentService attachmentService;
	
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Object save(@RequestBody AttachmentCondition cond) {
		attachmentService.saveAttachment(cond);
		return this.success("保存成功！");
	}

	@PostMapping("/decryptFile")
	public ResponseEntity<byte[]> decryptFile(@RequestBody String encryptedUrl) {
		try {
			// 打开URL连接并读取加密的图像数据
			URL url = new URL(encryptedUrl);
			try (InputStream inputStream = url.openStream();
				 ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
				byte[] data = new byte[8192];
				int bytesRead;
				while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
					buffer.write(data, 0, bytesRead);
				}
				byte[] encryptedImageData = buffer.toByteArray();
				// 将字节数组转换为Base64编码的字符串
				String base64EncodedData = Base64.getEncoder().encodeToString(encryptedImageData);
				// 解密图像数据
				String decryptedData = AesUtil.decrypt(base64EncodedData);

				byte[] decryptedImageData = Base64.getDecoder().decode(decryptedData);
				// 获取文件名和MIME类型
				String decodedUrl = URLDecoder.decode(encryptedUrl, StandardCharsets.UTF_8.name());
				String fileName = Paths.get(new URL(decodedUrl).getPath()).getFileName().toString();
				String mimeType = MimeTypeUtil.getMimeType(fileName);
				// 返回解密后的图像数据
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.parseMediaType(mimeType));
				return new ResponseEntity<>(decryptedImageData, headers, HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Failed to decrypt the image data: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}


	/**
	 * 上传图片
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/upload")
	public @ResponseBody Object upload(@RequestParam("file") MultipartFile file) throws IOException {

		//判断文件是否包含脚步
		if(!FileValidator.isSafeFile(file))	{
			return this.fail("上传文件包含脚本！");
		}
		//判断是否为非法图片文件
		if(!FileValidator.imageCheck(file)) {
			return this.fail("上传图片文件存在异常！");
		}

		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        Long timeInfo = Instant.now().toEpochMilli();

		try {
			MultipartFile encryptFile = FileEncryptionHelper.encryptFile(file);
			FileInfo info = fs.upload(encryptFile, FileEnum.AMCS_AE_FILE.getBucketType(),
					FileEnum.AMCS_AE_FILE.getIsEncrypt(), FileEnum.AMCS_AE_FILE.getPathPrefix().replace("*", "") + timeInfo + "/" + timeInfo + "." + suffix,
					FileEnum.AMCS_AE_FILE.getPlatform());
			if (null == info) {
				return this.fail("文件上传失败！");
			}

			return this.success(info);
		} catch (Exception e) {
			e.printStackTrace();
			return this.fail("文件加密失败！");
		}
	}
	

	
	/**
	 * 删除图片
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/delete")
	public @ResponseBody Object delete(@RequestParam("attachmentUrl") String attachmentUrl,
			@RequestParam("attachmentFileId") String attachmentFileId) throws IOException {
		String storeName = attachmentUrl.substring(attachmentUrl.lastIndexOf("/") + 1);
        String storeFolder = storeName.substring(0, storeName.lastIndexOf("."));
        boolean flag = fs.deleteFile(FileEnum.AMCS_AE_FILE.getBucketType(),
                FileEnum.AMCS_AE_FILE.getPathPrefix().replace("*", "") + storeFolder + "/" + storeName,
                FileEnum.AMCS_AE_FILE.getPlatform());
		if (!flag) {
			return this.fail("文件删除失败！");
		}else {
			//删除文件记录
			attachmentService.delByFileId(attachmentFileId);
			return this.success("文件删除成功！");
		}
		
	}
	
	
	@RequestMapping(value="/findByCond" , method = RequestMethod.POST)
	public @ResponseBody PageResponse<Map<String, Object>> findByCond(AttachmentCondition cond) {
		return attachmentService.findByCond(cond);
	}

	/*
	 * 查询页面上传
	 * */
	@PostMapping(value = "/queryPageUpload")
	@ResponseBody
	public Map<String, Object> queryPageUpload(@RequestParam("bizId") Long bizId,
											   @RequestParam("file") MultipartFile file) {
		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
		Long timeInfo = Instant.now().toEpochMilli();
		FileInfo info = fs.upload(file, FileEnum.AMCS_AE_FILE.getBucketType(),
				FileEnum.AMCS_AE_FILE.getIsEncrypt(), FileEnum.AMCS_AE_FILE.getPathPrefix().replace("*", "") + timeInfo + "/" + timeInfo + "." + suffix,
				FileEnum.AMCS_AE_FILE.getPlatform());

		if (null == info) {
			return this.fail("文件上传失败！");
		}

		Attachment attachment = new Attachment();
		attachment.setFileName(file.getOriginalFilename());
		attachment.setBasicId(bizId);
		attachment.setFileSize((long) Math.toIntExact(file.getSize()));
		attachment.setTag("adv_querylist");
		attachment.setUsingSign(1);
		attachment.setUrl(info.getUrl());
		attachment.setFileId(info.getId().toString());
		attachmentService.saveOrModify(attachment);

		return success("文件上传成功！");
	}


	@GetMapping(value = "/doEncrypt")
	@ResponseBody
	public Map<String, Object> doEncrypt(@RequestParam("code") String code) {
		String codeMatch = "dafjmsDfii23jfiasYjf8i2i2jhs";
		if (codeMatch.equals(code)) {
			List<Attachment> attachments = attachmentService.getUploadFiles();
			for (Attachment attachment : attachments) {
				try {
					// 根据文件链接地址获取文件并加密
					String url = attachment.getUrl();
					String fileName = attachment.getFileName();
					MultipartFile file = getMultipartFileFromUrl(url, fileName);

					MultipartFile encryptFile = FileEncryptionHelper.encryptFile(file);
					String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
					Long timeInfo = Instant.now().toEpochMilli();
					FileInfo info = fs.upload(encryptFile, FileEnum.AMCS_AE_FILE.getBucketType(),
							FileEnum.AMCS_AE_FILE.getIsEncrypt(), FileEnum.AMCS_AE_FILE.getPathPrefix().replace("*", "") + timeInfo + "/" + timeInfo + "." + suffix,
							FileEnum.AMCS_AE_FILE.getPlatform());
					if (null == info) {
						return this.fail("文件上传失败！");
					}
					// 保存已经上传的文件
					Attachment newAttachment = new Attachment();
					BeanUtils.copyProperties(newAttachment, attachment);
					newAttachment.setId(null);
					newAttachment.setFileSize(info.getFileSize());
					newAttachment.setFileId(info.getId().toString());
					newAttachment.setUrl(info.getUrl());
					newAttachment.setIsEncrypt(1);
					newAttachment.setModifyDate(new Date());
					newAttachment.setModifer(1L);
					attachmentService.saveOrModify(newAttachment);
					attachment.setUsingSign(0);
					attachmentService.saveOrModify(attachment);

				} catch (Exception e) {
					e.printStackTrace();
					return this.fail("文件加密失败！");
				}
			}
			return success("encrypt success!!!");
		}
		return fail("code error!!!");
	}

	@GetMapping(value = "/doDelRepeat")
	@ResponseBody
	public Map<String, Object> doDelRepeat(@RequestParam("code") String code) {
		String codeMatch = "5adf34ttwert4rwe44fsdf34frf4";
		if (codeMatch.equals(code)) {
			List<String> repeatIds = attachmentService.getRepeatIds();
			for (String repeatId : repeatIds) {
				attachmentService.delByFileId(repeatId);
			}
			return success("del repeat success!!!");
		}
		return fail("code error!!!");
	}

}
