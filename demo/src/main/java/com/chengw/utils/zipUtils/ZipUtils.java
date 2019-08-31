package com.chengw.utils.zipUtils;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipUtil;
import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author chengw
 */
public class ZipUtils {

    private static final Logger logger = LoggerFactory.getLogger(ZipUtil.class);


    /**
     * 解压zip,ZIP,JAR,AR,CPIO,ARJ,7Z,TAR……等文件
     * @param sourceFilePath 压缩文件路径
     * @return  返回解压完成后的目标路径
     */
    public static String unZip(String sourceFilePath) {
        ArchiveInputStream o = null;
        String targetDir = sourceFilePath.substring(0, sourceFilePath.lastIndexOf("."));
        try (InputStream fi = Files.newInputStream(Paths.get(sourceFilePath));
             InputStream buffer = new BufferedInputStream(fi);
             ArchiveInputStream archive = new ArchiveStreamFactory()
                     .createArchiveInputStream(buffer)
        ) {
            ArchiveEntry entry = null;
            while ((entry = archive.getNextEntry()) != null) {
                if (!archive.canReadEntryData(entry)) {
                    logger.error("---{}---无法读取Entry", entry.getName());
                    continue;
                }
                /**新建"解压到"文件获文件夹*/
                String name = targetDir + File.separator + entry.getName();
                File file = new File(name);
                if (entry.isDirectory()) {
                    if (!file.isDirectory() && !file.mkdirs()) {
                        throw new IOException(" 创建目录失败" + file);
                    }
                } else {
                    File parent = file.getParentFile();
                    if (!parent.isDirectory() && !parent.mkdirs()) {
                        throw new IOException(" 创建目录失败" + file);
                    }
                    try (OutputStream out = Files.newOutputStream(file.toPath())) {
                        IOUtils.copy(archive, out);
                        logger.info("---{}\\{}---解压成功", file.getAbsolutePath(),file.getName());
                    }
                }


            }
            logger.info("---{}---已解压到---{}----",sourceFilePath,targetDir);

            return sourceFilePath;

        } catch (IOException e) {
            logger.error("IO异常:{}", e.getMessage());
        } catch (ArchiveException e) {
            logger.error("获取压缩文件流异常:{}", e.getMessage());
        }

        return null;

    }

    public static void main(String[] args) {
        unZip("F:/settings.zip");
    }

}
