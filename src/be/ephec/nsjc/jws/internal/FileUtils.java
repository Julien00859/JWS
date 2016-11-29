package be.ephec.nsjc.jws.internal;

import java.io.File;
import java.nio.file.Files;

public class FileUtils {

    public static String baseDir = "www" + File.separator;

    /** For a filename given give the content
     * @param uri the relative path from the base dir
     * @return le content of the file
     */
    public static byte[] getFile(String uri) {
        return null;
    }

    /** Save on the disk a file at the uri given with the content
     * @param uri the relative path from the base dir
     * @param content the content of the file
     */
    public static void postFile(String uri, byte[] content) {

    }

    /** Check if the given uri is an existing file
     * @param uri the relative path from the base dir
     * @return wheter the file exists or not
     */
    public static boolean fileExists(String uri) {
        return true;
    }

}
