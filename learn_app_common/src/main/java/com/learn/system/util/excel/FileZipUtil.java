package com.learn.system.util.excel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @Description 文件的压缩
 * @Author windpursuer
 * @Date 2017/5/16 14:18
 */
public class FileZipUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileZipUtil.class);


    /**
     * @Description: 将压缩后的文件流写入outPutStream中
     * @Author: WindPursuer
     * @Date 2018/7/23 下午2:11
     */
    public static boolean zipMultiFile(String srcFilePath, OutputStream outputStream) {
        try {
            File srcFile = new File(srcFilePath);
            if (srcFile.exists()) {
                InputStream in = null;
                int index = srcFilePath.indexOf(srcFile.getName());
                ZipOutputStream zipOut = new ZipOutputStream(new BufferedOutputStream(outputStream));
                zipDirectory(in, srcFile, zipOut, index);
                zipOut.close();
                deleteDirectory(srcFile);
                return true;
            }
        } catch (Exception e) {
            logger.error("ZIP", "压缩失败，原因:"+e.getMessage());
        }
        return false;
    }


    /**
     * @Description 压缩文件夹
     * @Author windpursuer
     * @Date 2017/5/16 19:45
     */
    public static boolean zipMultiFile(String srcFilePath) {
        try {
            File srcFile = new File(srcFilePath);
            if (srcFile.exists()) {
                File zipFile = new File(srcFilePath+".zip");
                InputStream in = null;
                int index = srcFilePath.indexOf(srcFile.getName());
                ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
                zipDirectory(in, srcFile, zipOut, index);
                zipOut.close();
                deleteDirectory(srcFile);
                return true;
            }
        } catch (Exception e) {
            logger.error("ZIP", "压缩失败，原因:"+e.getMessage());
        }
        return false;
    }

    /**
     * @Description 解压缩文件
     * @Author windpursuer
     * @Date 2017/5/16 20:01
     */
    public static boolean zipContraMultiFile(String zipFilePath) {
        try {
            File srcFile = new File(zipFilePath);
            String dstFilePath = srcFile.getParentFile().getAbsolutePath();
            File outFile;
            ZipFile zipFile = new ZipFile(srcFile);
            ZipInputStream zipIn = new ZipInputStream(new FileInputStream(srcFile));
            ZipEntry entry;
            InputStream in;
            OutputStream out;
            while ((entry = zipIn.getNextEntry()) != null) {
                outFile = new File(dstFilePath + File.separator + entry.getName());
                if (!outFile.getParentFile().exists()) {
                    outFile.getParentFile().mkdirs();
                }
                in = zipFile.getInputStream(entry);
                out = new FileOutputStream(outFile);
                byte[] cache = new byte[1024];
                int nRead;
                while ((nRead = in.read(cache)) != -1) {
                    out.write(cache, 0, nRead);
                }
                out.close();
                in.close();
            }
            zipIn.close();
            zipFile.close();
            srcFile.delete();
            return true;
        } catch (Exception e) {
            logger.error("ZIP", "解压失败，原因:"+e.getMessage());
        }
        return false;
    }
    /**
     * @Description 删除文件夹
     * @Author windpursuer
     * @Date 2017/5/17 14:24
     */
    public static void deleteDirectory(File files){
        if (files.exists() && files.isDirectory()) {
            File[] fileList = files.listFiles();
            if (null == fileList || fileList.length == 0) {
                logger.error("文件为空");
                return;
            }
            for (File file : fileList) {
                if (file.isFile()) {
                    file.delete();
                } else if (file.isDirectory()) {
                    deleteDirectory(file);
                }
            }
            files.delete();
        }
    }

    /**
     * @Description 压缩文件夹
     * @Author windpursuer
     * @Date 2017/5/18 16:40
     */
    private static void zipDirectory(InputStream in, File f, ZipOutputStream zipOut, int index){
        try{
            /** 设置压缩方式 */
            zipOut.setMethod(ZipOutputStream.DEFLATED);
            if (f.isDirectory()) {
                File[] files = f.listFiles();
                for (File f1 : files) {
                    if (f1.isDirectory()) {
                        zipDirectory(in, f1, zipOut, index);
                    } else {
                        in = new FileInputStream(f1);
                        zipOut.putNextEntry(new ZipEntry(f1.getName()));
                        byte[] cache = new byte[1024];
                        int nRead = 0;
                        while ((nRead = in.read(cache)) != -1) {
                            zipOut.write(cache, 0 ,nRead);
                        }
                        in.close();
                    }
                }
            }
        } catch (Exception e) {
            logger.error("压缩失败，原因:"+e.getMessage());
        }

    }
}
