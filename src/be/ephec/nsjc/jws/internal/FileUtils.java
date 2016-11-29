package be.ephec.nsjc.jws.internal;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileUtils {

    private static String baseDir = "www";
    public static void setBaseDir(String baseDir) {
        FileUtils.baseDir = baseDir;
    }
    public static String getBaseDir() {
        return FileUtils.baseDir;
    }

    /** For a filename given give the content
     * @param uri the relative path from the base dir
     * @return le content of the file
     * @throws IOException if the reading failed
     */
    public static byte[] getFileContent(String uri) throws IOException {
        File f = getFile(uri);
        return Files.readAllBytes(f.toPath());
    }

    /** Save on the disk a file at the uri given with the content
     * @param uri the relative path from the base dir
     * @param content the content of the file
     * @throws IOException if the writing failed
     */
    public static void postFile(String uri, byte[] content) throws IOException {
        File f = getFile(uri);
        Files.write(f.toPath(), content);
    }

    /** Remove a file from the filesystem
     * @param uri the relative path from the base dir
     * @throws IOException if the delete failed
     */
    public static void deleteFile(String uri) throws IOException {
        File f = getFile(uri);
        Files.delete(f.toPath());
    }

    /** Check if the given uri is an existing file
     * @param uri the relative path from the base dir
     * @return wheter the file exists or not
     */
    public static boolean fileExists(String uri) {
        File f = getFile(uri);
        return f.exists() && !f.isDirectory();
    }

    /** Convert an URI to an OS path
     * @param uri the relative path fromthe base dir
     * @return the file at the given location
     */
    private static File getFile(String uri) {
        return new File(baseDir + uri.replaceAll("/", File.separator));
    }
}
