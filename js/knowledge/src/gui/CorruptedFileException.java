package gui;

/**
 * <p>This exception is used for corrupted files (e.g. comma separated user file, question file, info file etc.) </p>
 */
public class CorruptedFileException extends Exception{
	public CorruptedFileException(String msg) {
		super(msg);
	}
}
