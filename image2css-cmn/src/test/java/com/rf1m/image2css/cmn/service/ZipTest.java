package com.rf1m.image2css.cmn.service;

import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipTest {
    private static final int BUFFER_SIZE = 4096;

    @Test
    public void should() throws Exception {
        final File sample1 = this.getFile("sample1.txt");
        final File sample2 = this.getFile("sample2.txt");

        final ZipOutputStream zipOutputStream = this.getZipOut("sample.zip");

        this.addFileToZip(sample1, zipOutputStream);
        this.addFileToZip(sample2, zipOutputStream);

        zipOutputStream.flush();
        zipOutputStream.close();

        final ZipInputStream zipInputStream = getZipIn("sample.zip");

        ZipEntry entry = zipInputStream.getNextEntry();

        while(entry != null) {
            System.out.println(entry.getName());
            entry = zipInputStream.getNextEntry();
        }
    }

    protected ZipOutputStream getZipOut(final String zipFilename) throws FileNotFoundException {
        final String tempDir = System.getProperty("java.io.tmpdir");
        final String zipPath = tempDir + zipFilename;
        final File zipFile = new File(zipPath);
        final FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
        final ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);

        return zipOutputStream;
    }

    protected ZipInputStream getZipIn(final String zipFilename) throws IOException {
        final String tempDir = System.getProperty("java.io.tmpdir");
        final String zipPath = tempDir + zipFilename;
        final File zipFile = new File(zipPath);
        final FileInputStream fileInputStream = new FileInputStream(zipFile);
        final ZipInputStream zipIn = new ZipInputStream(fileInputStream);

        return zipIn;
    }

    protected File getFile(final String filename) throws Exception {
        final URL url = this.getClass().getClassLoader().getResource(filename);
        final File file = new File(url.getFile());

        return file;
    }

    protected void addFileToZip(final File file, final ZipOutputStream zos) throws IOException {
        zos.putNextEntry(new ZipEntry(file.getName()));

        final BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));

//        long bytesRead = 0;
        final byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;

        while((read = bis.read(bytesIn)) != -1) {
            zos.write(bytesIn, 0, read);
//            bytesRead += read;
        }

        zos.closeEntry();
    }

}
