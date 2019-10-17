package com.lsw.speedlib.listener;

/**
 * 上传和下载速度
 */
public interface SpeedListener {
    void speeding(long downSpeed, long upSpeed);

    void finishSpeed(long finalDownSpeed, long finalUpSpeed);
}
