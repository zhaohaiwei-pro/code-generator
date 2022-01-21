package com.zhw.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.zhw.view.AlertUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 模板工具
 */
@Slf4j
public class TemplateUtil {

    /** 模板文件夹名称 */
    private static final String TEMPLATE_DIR = Constant.TEMPLATE_DIR_NAME;

    /**
     * 查看是否存在模板文件夹,如果不存在则创建
     */
    public static void existsTemplate() {
        try {
            if(Files.exists(Paths.get(TEMPLATE_DIR)) && Files.isDirectory(Paths.get(TEMPLATE_DIR))){
                Files.list(Paths.get(TEMPLATE_DIR)).forEach(path -> {
                    try {
                        Files.delete(path);
                    } catch (IOException ignored) {
                        log.error("删除模板{}失败!", path.getFileName());
                    }
                });
                Files.delete(Paths.get(TEMPLATE_DIR));
            }
        } catch (IOException ignored) {
            log.error("删除模板文件夹{}失败!", Paths.get(TEMPLATE_DIR).getFileName());
        }
        if (Files.notExists(Paths.get(TEMPLATE_DIR))) {
            log.debug("执行创建模板...");
            try {
                Files.createDirectory(Paths.get(TEMPLATE_DIR));
                URL resource = Thread.currentThread().getContextClassLoader().getResource(TEMPLATE_DIR);
                URLConnection conn = Objects.requireNonNull(resource).openConnection();
                if (conn instanceof JarURLConnection) {
                    jarCreateTemplate((JarURLConnection) conn);
                } else {
                    URI uri = resource.toURI();
                    Files.list(Paths.get(uri)).forEach(x -> {
                        Path out = Paths.get(TEMPLATE_DIR, x.getFileName().toString());
                        try {
                            Files.copy(x, out);
                        } catch (IOException e) {
                            AlertUtil.showErrorAlert("创建模板-->失败:" + e);
                        }
                    });
                }
            } catch (Exception e) {
                AlertUtil.showErrorAlert("创建模板-->失败:" + e);
            }

        }
    }

    /**
     * 将jar里面的文件复制到模板文件夹
     */
    public static void jarCreateTemplate(JarURLConnection jarConn) throws IOException {
        try (JarFile jarFile = jarConn.getJarFile()) {
            Enumeration<JarEntry> entrys = jarFile.entries();
            while (entrys.hasMoreElements()) {
                JarEntry entry = entrys.nextElement();
                if (entry.getName().startsWith(jarConn.getEntryName()) && !entry.getName().endsWith("/")) {
                    String fileName = entry.getName().replace(TEMPLATE_DIR + "/", "");
                    InputStream inpt = Thread.currentThread().getContextClassLoader().getResourceAsStream(entry.getName());
                    Files.copy(Objects.requireNonNull(inpt), Paths.get(TEMPLATE_DIR, fileName));
                }
            }
        }

    }

}
