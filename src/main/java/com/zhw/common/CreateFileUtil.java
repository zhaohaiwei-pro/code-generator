package com.zhw.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;

/**
 * 创建文件的 的工具
 *
 */
@Slf4j
public class CreateFileUtil {

    /**
     * 执行创建文件
     *
     * @param content 模板所需要的上下文
     * @param templeteName 模板的名字 示例:Entity.ftl
     * @param projectPath 生成的项目路径 示例:D://create
     * @param packageName 包名 示例:com.mozi.corex
     * @param fileName 文件名 示例:Entity.java
     * @param codeFormat 输出的字符编码格式 示例:UTF-8
     */
    public static void createFile(Map<String, Object> content, String templeteName, String projectPath, String packageName, String fileName,
        String codeFormat, boolean isOverride) throws Exception {
        String outputPath = projectPath + "/" + packageName.replace(".", "/") + "/";
        if (!isOverride) {
            if (Files.exists(Paths.get(outputPath + fileName))) {
                log.debug("设置了文件存在不覆盖,文件已经存在,忽略本文件的创建");
                return;
            }
        }
        Configuration config = new Configuration(Configuration.VERSION_2_3_23);
        // 打包成jar包使用的路径
        String tempPath = Paths.get(Constant.TEMPLATE_DIR_NAME).toFile().getName();
        config.setDirectoryForTemplateLoading(new File(tempPath));
        config.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_23));
        config.setDefaultEncoding("utf-8");
        Template template = config.getTemplate(templeteName);
        if (!Files.exists(Paths.get(outputPath))) {
            Files.createDirectories(Paths.get(outputPath));
        }
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(outputPath + fileName), codeFormat)) {
            template.process(content, writer);
        }
    }
}
