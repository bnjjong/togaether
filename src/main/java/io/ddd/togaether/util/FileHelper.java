package io.ddd.togaether.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.web.multipart.MultipartFile;

/**
 * create on 2022/12/22. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Jongsang Han(henry)
 * @version 1.0
 * @see
 * @since 1.0
 */
public class FileHelper {

  public static void main(String[] args) throws FileReadException {
    Path source = Paths.get(FileHelper.class.getResource("/").getPath());
    System.out.println(source.toAbsolutePath());

  }

  public static String getResourcePath() throws FileReadException {
    final String path = "images";
    try {
      URL res = FileHelper.class.getClassLoader().getResource("");

      if (res == null) {
        throw new IllegalArgumentException("path is not valid!>>>" + path);
      }
      System.out.println(res.getPath());
      return res.getPath();
    } catch (Exception e) {
      InputStream in = FileHelper.class.getClassLoader().getResourceAsStream(path);
      try {
        if (in != null) {
          File file = convertInputStreamToFile(in);
          return file.toPath().toString();
        }
        throw new IOException("input stream is null");
      } catch (IOException ioException) {
        throw new FileReadException(e);
      }
    }
  }

  /**
   * <p> Convert {@code InputStream} to {@code File}.</p>
   *
   * @param in InputStream
   * @return File Object
   * @throws IOException input stream is not valid.
   */
  public static File convertInputStreamToFile(final InputStream in) throws IOException {

    // https://dev.umejintan.com/9
    File tempFile = File.createTempFile(String.valueOf(in.hashCode()), ".tmp");
    // when exit JVM, this temp file is deleted itself.
    tempFile.deleteOnExit();

    copyInputStreamToFile(in, tempFile);

    return tempFile;
  }

  /**
   * <p> InputStream to make File. </p>
   *
   * @param inputStream inputStream for making file.
   * @param file destination file.
   * @throws java.io.FileNotFoundException see {@link FileOutputStream#FileOutputStream(File)}.
   * @throws SecurityException see {@link FileOutputStream#FileOutputStream(File)}.
   * @throws IOException see {@link FileOutputStream#write(int)}.
   *
   */
  private static void copyInputStreamToFile(final InputStream inputStream, final File file)
      throws IOException {

    try (FileOutputStream outputStream = new FileOutputStream(file)) {
      int read;
      byte[] bytes = new byte[1024];

      while ((read = inputStream.read(bytes)) != -1) {
        outputStream.write(bytes, 0, read);
      }
    }
  }

  public static String getFileNameServer(MultipartFile multipartFile) {
    // 파일 확장자 추출
    int pos = multipartFile.getOriginalFilename().lastIndexOf(".");
    String ext = multipartFile.getOriginalFilename().substring(pos + 1);

    // 서버에 올라갈 파일명 반환
    return FileHelper.makeFileName() + "." + ext;
  }

  private static String makeFileName() {
    Date now = new Date();
    String today = new SimpleDateFormat("yyyyMMddHHmmss").format(now);

    String random = "";
    for (int i = 1; i <= 10; i++) {
      char ch = (char) ((Math.random() * 26) + 97);
      random += ch;
    }
    String result = today + random;

    return result;
  }

}
