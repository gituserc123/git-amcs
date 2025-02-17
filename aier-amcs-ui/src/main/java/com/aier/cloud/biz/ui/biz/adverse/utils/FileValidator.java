package com.aier.cloud.biz.ui.biz.adverse.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;

public class FileValidator {
    // 定义多个正则表达式来匹配常见的恶意代码模式
    private static final Pattern[] MALICIOUS_PATTERNS = new Pattern[] {
            Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE),
            Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE),
            Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE),
            Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE),
            Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE),
            Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE),
            // 添加更多的正则表达式来匹配其他类型的恶意代码
    };

    public static boolean isSafeFile(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (Pattern pattern : MALICIOUS_PATTERNS) {
                    if (pattern.matcher(line).find()) {
                        return false; // 如果找到匹配的恶意代码，返回false
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true; // 如果没有找到匹配的恶意代码，返回true
    }

    public static boolean imageCheck(MultipartFile file) {
        try {
            if(file.getContentType().startsWith("image")){
                InputStream inputStream = file.getInputStream();
                ImageIO.read(inputStream);
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
