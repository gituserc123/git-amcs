package com.aier.cloud.biz.ui.biz.adverse.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileEncryptionHelper {

    public static MultipartFile encryptFile(MultipartFile file) throws Exception {
        byte[] fileBytes = file.getBytes();
        String encryptedData = AesUtil.encrypt(Base64.getEncoder().encodeToString(fileBytes));
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);
        return new MockMultipartFile(file.getName(), file.getOriginalFilename(), file.getContentType(), encryptedBytes);
    }

    private static InputStream getFileStream(String fileUrl) throws IOException {
        URL url = new URL(fileUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000); // 超时时间
        connection.setReadTimeout(5000);

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            return connection.getInputStream();
        } else {
            throw new IOException("HTTP 请求失败，状态码：" + connection.getResponseCode());
        }
    }

    public static MultipartFile getMultipartFileFromUrl(String fileUrl, String fileName) {
        try {
            URL url = new URL(fileUrl);
            BufferedInputStream inputStream = new BufferedInputStream(url.openStream());
            String contentType = Files.probeContentType(Paths.get(fileName));
            byte[] fileBytes = IOUtils.toByteArray(inputStream);

            return new MockMultipartFile("file", fileName, contentType, fileBytes);
        } catch (IOException ex) {
            throw new RuntimeException("Failed to get file from URL", ex);
        }
    }

    public static MultipartFile encryptFileByUrl(String url, String fileName) throws Exception {
        InputStream inputStream = getFileStream(url);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }
        byte[] fileBytes = byteArrayOutputStream.toByteArray();
        String encryptedData = AesUtil.encrypt(Base64.getEncoder().encodeToString(fileBytes));
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);
        String contentType = Files.probeContentType(Paths.get(fileName));
        return new MockMultipartFile(fileName, fileName, contentType, encryptedBytes);
    }


    public static MultipartFile decryptFile(MultipartFile file) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        // Read file content as byte array
        byte[] fileBytes = file.getBytes();

        // Decrypt the file content
        String decryptedContent = AesUtil.decrypt(Base64.getEncoder().encodeToString(fileBytes));
        byte[] decryptedBytes = Base64.getDecoder().decode(decryptedContent);

        // Create a new MultipartFile object
        return new MockMultipartFile(file.getName(), file.getOriginalFilename(), file.getContentType(), decryptedBytes);
    }
}
