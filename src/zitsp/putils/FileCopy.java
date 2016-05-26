package zitsp.putils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.ByteChannel;
import java.nio.channels.FileChannel;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileCopy {

	private FileCopy() {
	}

    public static void copyBinaryFile(Path sourceFile, Path destinationFile) throws IOException {
    	if (sourceFile == null || destinationFile == null) {
    		throw new NullPointerException();
    	} else if (Files.notExists(sourceFile)) {
    		throw new FileNotFoundException(sourceFile.toString());
    	} else if (Files.exists(destinationFile)) {
    		throw new FileAlreadyExistsException(destinationFile.toString());
    	}
    	try (FileChannel sourceChannel = (FileChannel) Files.newByteChannel(sourceFile, StandardOpenOption.READ);
    			ByteChannel destinationChannel = Files.newByteChannel(destinationFile, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
    		sourceChannel.transferTo(0, sourceChannel.size() ,destinationChannel);
    	} catch (IOException exception) {
            exception.printStackTrace();
    	}
    }   
}
