package controller;

import java.io.IOException;

/**
 * Created by dengshougang on 16/5/9.
 */
public interface DeviceRotater {
    public int leftRotate(String angle) throws IOException;
    public int rightRotate(String angle) throws IOException;
    public int upRotate(String angle) throws IOException;
    public int downRotate(String angle) throws IOException;
    public int forwardRotate(String angle) throws IOException;
    public int backRotate(String angle) throws IOException;
}
