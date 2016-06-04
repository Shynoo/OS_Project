package controller;

import java.io.IOException;

/**
 * Created by dengshougang on 16/5/9.
 */
public interface DeviceRotater {
    public int leftRotate(int angle) throws IOException;
    public int rightRotate(int angle) throws IOException;
    public int upRotate(int angle) throws IOException;
    public int downRotate(int angle) throws IOException;
    public int forwardRotate(int angle) throws IOException;
    public int backRotate(int angle) throws IOException;
}
