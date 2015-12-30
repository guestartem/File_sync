import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by Artem on 30.12.2015.
 */
public class MyFileVisitor extends SimpleFileVisitor<Path> {
    private Path srcPath;
    private Path destPath;

    public MyFileVisitor(Path srcPath, Path destPath) {
        this.srcPath = srcPath;
        this.destPath = destPath;
    }

        @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Path newfile = destPath.resolve(file.getFileName());
//        try {
        Files.copy(file, newfile, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        Path newdest = destPath.resolve(dir.getFileName());
//        try {
            Files.copy(dir, newdest, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return FileVisitResult.CONTINUE;
    }


    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        Path newdest = destPath.resolve(srcPath.relativize(file));
//        try {
        Files.copy(file, newdest, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return FileVisitResult.CONTINUE;
    }

}
