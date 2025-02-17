package com.aier.cloud.biz.ui.biz.adverse.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class MimeTypeUtil {

    private static final Map<String, String> mimeTypes = new HashMap<>();

    static {
        mimeTypes.put("jpg", "image/jpeg");
        mimeTypes.put("jpeg", "image/jpeg");
        mimeTypes.put("png", "image/png");
        mimeTypes.put("gif", "image/gif");
        mimeTypes.put("pdf", "application/pdf");
        // 根据需要添加更多 MIME 类型
    }

    public static String getMimeType(String fileName) {
        try {
            String mimeType = Files.probeContentType(Paths.get(fileName));
            if (mimeType == null) {
                String extension = getFileExtension(fileName);
                mimeType = mimeTypes.get(extension.toLowerCase());
            }
            return mimeType;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getFileExtension(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf('.');
        if (lastIndexOfDot == -1) {
            return ""; // 空扩展名
        }
        return fileName.substring(lastIndexOfDot + 1);
    }
}
